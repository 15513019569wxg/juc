package __22__balking设计模式;

import java.io.IOException;
import java.util.Scanner;

/**
 * 该线程代表的是主动文档编辑的线程, 为了增加交互性, 使用Scanner
 * @author wxg
 * @date 2022/1/13-1:20
 * @quotes 小不忍则乱大谋
 */
public class DocumentEditThread extends Thread{
    private final String documentPath;

    private final String documentName;

    private final Scanner scanner = new Scanner(System.in);

    public DocumentEditThread(String documentPath, String documentName) {
        this.documentPath = documentPath;
        this.documentName = documentName;
    }

    @Override
    public void run() {
        int times = 0;
        try {
            // 在这一步，文档自动保存线程也启动了
            Document document = Document.create(documentPath, documentName);
            while(true){
                // 获取用户的键盘输入
                String text = scanner.next();
                if("quit".equals(text)){
                    document.close();
                    break;
                }
                // 将内容编辑到document中
                document.edit(text);
                if(times == 5){
                    // 用户在输入了5次之后进行文档保存
                    document.save();
                    times = 0;
                }
                times++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
