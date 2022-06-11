package __25__Thread_Per_message设计模式.chat;

import java.io.IOException;

/**
 * @author wxg
 * @date 2022/2/1-16:52
 * @quotes 小不忍则乱大谋
 */
public class ChatTest {
    public static void main(String[] args) throws IOException {
        new ChatServer().startServer();
    }
}
