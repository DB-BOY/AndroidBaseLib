package cn.dbboy.generallib;

import android.content.Context;
import android.content.res.Resources;

/**
 * 基础工具类Manager，只需要初始化一次。</br>
 * 避免耗时操作
 * </>
 * Created by DB_BOY on 2019/3/27.
 */
public class GeneralLibManager {
    private Context context;

    /**
     * 禁止构造
     */
    private GeneralLibManager() {
    }

    /**
     * 获得单例
     *
     * @return
     */
    public static GeneralLibManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * 初始设置Context，只有第一次设置有效
     *
     * @param context
     */
    public void init(Context context) {
        if (this.context != null) {
            this.context = context;
        }
    }

    public Context getContext() {
        if (context == null) {
            throw new IllegalArgumentException("Context can not be null, initialize it in GeneralManager.");
        }
        return context;
    }

    public Resources getResource() {
        return context.getResources();
    }

    /**
     * 单例持有器
     */
    private static final class InstanceHolder {
        private static final GeneralLibManager INSTANCE = new GeneralLibManager();
    }

}
