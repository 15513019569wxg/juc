package __16__Single_Thread_Execution设计模式.__01机场过安检;

/**
 * @author wxg
 * @date 2022/1/9-15:24
 * @quotes 小不忍则乱大谋
 */
public class FlightSecurityTest {
    static class Passengers extends Thread{
        // 机场安检类
        private final FlightSecurity flightSecurity;
        // 旅客的身份证
        private final String idCard;
        // 旅客的登记牌
        private final String boardingPass;

        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while(true){
                // 旅客不断地过安检
                flightSecurity.pass(boardingPass,idCard);
            }
        }
    }

    public static void main(String[] args) {
        // 定义三个旅客,身份证和登机牌首字母均相同
        final FlightSecurity flightSecurity = new FlightSecurity();
        //  注意，传进来同一个实例对象
        new Passengers(flightSecurity, "A123456","AF123456").start();
        new Passengers(flightSecurity, "B123456","BF123456").start();
        new Passengers(flightSecurity, "C123456","CF123456").start();
    }
}
