package __22__balking设计模式;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author wxg
 * @date 2022/1/13-1:15
 * @quotes 小不忍则乱大谋
 */
public class AutoSaveThread extends Thread{
    private final Document document;

    public AutoSaveThread(Document document){
        super("DocumentAutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {
        while(true){
            // 每秒自动保存一次文档
            try {
                document.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (IOException | InterruptedException e) {
               break;
            }
        }
    }
}
