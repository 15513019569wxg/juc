package __25__Thread_Per_message设计模式.chat;


import __08__线程池.__26线程池实现.接口和基本类.ThreadPool;
import __08__线程池.__26线程池实现.详细实现.BasicThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wxg
 * @date 2022/1/31-21:53
 * @quotes 小不忍则乱大谋
 */
public class ChatServer {
    /**
     * 服务端端口
     */
    private final int port;

    /**
     * 服务端Socket
     */
    private ServerSocket serverSocket;

    private ThreadPool threadPool;

    public ChatServer(int port) {
        this.port = port;
    }

    /**
     * 默认使用13312端口
     */
    public ChatServer() {
        this(13312);
    }


    public void startServer() throws IOException {
        // 创建线程池, 初始化一个线程, 核心线程数量为2, 最大线程数量为4, 阻塞队列中最大可加入1000个任务
        threadPool = new BasicThreadPool(1, 4, 2, 1000);
        serverSocket = new ServerSocket(port);
        serverSocket.setReuseAddress(true);
        System.out.println("Chat server is started and listen at port: " + port);
        listen();
    }

    private void listen() throws IOException {
        for(; ; ){
            // accept方法是阻塞方法, 当有新的链接进入时才会返回, 并且返回的是客户端的连接
            Socket client = serverSocket.accept();
            // 将客户端连接作为一个Request, 封装成对应的Handler然后提交给线程池
            threadPool.execute(new ClientHandler(client));

        }
    }
}
