package __21__线程上下文设计模式.__03ThreadLocal.improve;

/**
 * @author wxg
 * @date 2022/1/12-17:14
 * @quotes 小不忍则乱大谋
 */
public final class ActionContext {
    private final static ThreadLocal<Context> THREAD_LOCAL = ThreadLocal.withInitial(Context::new);

    private ActionContext(){
    }

    private static class ContextHolder{
        private final static ActionContext ACTION_CONTEXT = new ActionContext();
    }

    public static ActionContext getActionContext(){
        return ContextHolder.ACTION_CONTEXT;
    }

    public Context getContext(){
        return THREAD_LOCAL.get();
    }

}
