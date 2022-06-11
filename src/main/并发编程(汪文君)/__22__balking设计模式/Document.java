package __22__balking设计模式;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.currentThread;

/**
 * @author wxg
 * @date 2022/1/13-0:50
 * @quotes 小不忍则乱大谋
 */
public class Document {
    /**
     * 一次需要保存的内容，可以将其理解为内容缓存
     */
    private final List<String> content = new ArrayList<>();

    private final FileWriter writer;

    /**
     * 自动保存文档的线程
     */
    private static AutoSaveThread autoSaveThread;

    /**
     * 如果文档发生改变, changed会被设置为true
     */
    private boolean changed = false;

    /**
     * 构造函数需要传入文档保存的路径和文档名称
     * @param documentPath 文档存储路径
     * @param documentName 文档名称
     * @throws IOException IO异常
     */
    private Document(String documentPath, String documentName) throws IOException {
        // 文件写入工具
        this.writer = new FileWriter(new File(documentPath, documentName), true);
    }

    /**
     * 静态方法, 主要用于创建文档, 顺便启动自动保存文档的线程
     * @param documentPath 文档存储路径
     * @param documentName 文档名称
     * @return 文档对象
     * @throws IOException IO异常
     */
    public static Document create(String documentPath, String documentName) throws IOException {
        Document document = new Document(documentPath, documentName);
        autoSaveThread = new AutoSaveThread(document);
        // 在开始编辑某个文档的同时，顺便启动文档自动保存线程
        autoSaveThread.start();
        return document;
    }

    /**
     * 文档的编辑, 其实就是往content队列中提交字符串
     * @param content 文档内容
     */
    public void edit(String content){
        synchronized(this){
            this.content.add(content);
            // 文档改变, changed会变为true
            this.changed = true;
        }
    }

    /**
     * 文档关闭的时候首先中断自动保存线程, 然后关闭writer释放线程
     * @throws IOException IO异常
     */
    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }


    public void save() throws IOException {
        synchronized(this){
            // balking, 如果文档已经被保存了(changed=false), 则直接返回
            if(!changed){
                return;
            }
            System.out.println( "save() " + currentThread() + " execute the save action");
            for (String cacheLine: content){
                this.writer.write(cacheLine);
                this.writer.write("\r\n");
            }
            this.writer.flush();
            // 将changed修改为false, 表明此刻再没有新的内容编辑
            this.changed = false;
            this.content.clear();
        }
    }
}
