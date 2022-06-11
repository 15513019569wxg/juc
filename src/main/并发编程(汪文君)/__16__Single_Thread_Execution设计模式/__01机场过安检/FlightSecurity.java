package __16__Single_Thread_Execution设计模式.__01机场过安检;

/**
 * @author wxg
 * @date 2022/1/9-12:31
 * @quotes 小不忍则乱大谋
 */
public class FlightSecurity {

    private int count;
    /**
     * 登机牌
     */
    private String boardingPass = "null";

    /**
     * 身份证
     */
    private String idCard = "null";

    public synchronized void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        count++;
        check();
    }

    private void check(){
        // 简单的测试，当登机牌和身份证首字母不相同时表示检查不通过
        if(boardingPass.charAt(0) != idCard.charAt(0)){
            throw new RuntimeException("========Exception====== " + toString());
        }
    }

    @Override
    public String toString() {
        return "FlightSecurity{" + "count=" + count + ", iaCard='" + idCard + ", boardingPass='" + boardingPass + '\'' + '}';
    }

}
