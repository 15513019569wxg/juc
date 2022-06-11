package __25__Thread_Per_message设计模式;

/**
 * 客户提交的任何业务受理请求都会被封装成Request对象
 * @author wxg
 * @date 2022/1/31-18:40
 * @quotes 小不忍则乱大谋
 */
public class Request {
    private final String business;

    public Request(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return business;
    }
}
