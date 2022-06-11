package __25__Thread_Per_message设计模式.chat;

import java.io.*;
import java.net.Socket;

/**
 * ChatHandler同样是一个Runnable接口的实现
 * @author wxg
 * @date 2022/2/1-16:26
 * @quotes 小不忍则乱大谋
 */
public class ClientHandler implements Runnable {
    /**
     * 客户端的socket连接
     */
    private final Socket socket;

    /**
     * 客户端的identify
     */
    private final String clientIdentify;

    /**
     * 通过构造函数传入客户端连接
     * @param socket 网络通信
     */
    public ClientHandler(final Socket socket) {
        this.socket = socket;
        this.clientIdentify = socket.getInetAddress().getHostAddress() + " : " + socket.getPort();
    }

    @Override
    public void run() {
        try {
            chat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chat() throws IOException {
        BufferedReader bufferedReader = wrap2Reader(socket.getInputStream());
        PrintStream printStream = wrap2Print(socket.getOutputStream());
        String received;
        while((received = bufferedReader.readLine()) != null){
            //  将客户端发送的消息输出到控制台
            System.out.printf("client: %s-message: %s\n", clientIdentify, received);
            if("quit".equals(received)){
                // 如果客户端发送了quit指令, 则断开与客户端的连接
                write2Client(printStream, "client will close");
                socket.close();
                break;
            }
            write2Client(printStream,"server: " + received);
        }
    }

    /**
     * 该方法主要用于向客户端发送消息
     * @param print 打印流
     * @param message   消息
     */
    private void write2Client(PrintStream print, String message) {
        print.println(message);
        print.flush();
    }

    /**
     * 将输出字节流封装成PrintStream
     * @param outputStream 字节流
     * @return  打印流
     */
    private PrintStream wrap2Print(OutputStream outputStream) {
        return new PrintStream(outputStream);
    }

    /**
     * 将输入字节流封装成BufferedReader缓冲字符流
     * @param inputStream   字节流
     * @return  字符流
     */
    private BufferedReader wrap2Reader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }


}
