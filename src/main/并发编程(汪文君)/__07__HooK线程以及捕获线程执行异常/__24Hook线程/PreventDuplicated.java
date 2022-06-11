package __07__HooK线程以及捕获线程执行异常.__24Hook线程;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * The type Prevent duplicated.
 *
 * @author wxg
 * @date 2022 /1/3-17:24
 * @quotes 小不忍则乱大谋
 */
public class PreventDuplicated {
    private final static String LOCK_PATH = "/home/wxg/locks";
    private final static String LOCK_FILE = ".lock";
    private final static String PERMISSIONS = "rw-------";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        //  ①注入Hook线程，在程序退出时删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The program received kill SIGNAL.");
            final boolean isDeleted = getLockFile().toFile().delete();
            System.out.println(isDeleted);
        }));

        //  ②检查是否存在.lock文件
        checkRunning();

        //  ③简单模拟当前程序正在运行
        for(; ; ){
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("Program is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if(path.toFile().exists()) {
            throw new RuntimeException("The program already running");
        }
        final Set<PosixFilePermission> posixFilePermissions = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path, PosixFilePermissions.asFileAttribute(posixFilePermissions));
    }

    private static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
}
