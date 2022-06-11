package __20__Guarded_Suspension设计模式.请求队列;

/**
 * @author wxg
 * @date 2022/1/11-11:30
 * @quotes 小不忍则乱大谋
 */
public class Request {
    private final String value;

    public Request(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
