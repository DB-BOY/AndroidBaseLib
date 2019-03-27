package cn.dbboy.generallib.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import cn.dbboy.generallib.GeneralLibManager;

/**
 * 单例Toast
 * <p>
 * Created by DB_BOY on 2019/3/27.
 */
public class ToastUtil {

    private static Toast toast;

    /**
     * @param text
     */
    public static void show(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        custom(text, Toast.LENGTH_SHORT);
    }


    /**
     * @param resid
     */
    public static void show(int resid) {
        show(GeneralLibManager.getInstance().getResource().getString(resid));
    }

    public static void show(int resid, int duration) {
        show(GeneralLibManager.getInstance().getResource().getString(resid), duration);
    }

    private static void initToast() {
        Context context = GeneralLibManager.getInstance().getContext();
        if (context == null) {
            throw new IllegalArgumentException("Context can not be null, initialize it in GeneralManager.");
        }
        if (toast == null) {
            toast = new Toast(context);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
    }

    public static void show(CharSequence text, int duration) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        custom(text, duration);
    }

    private static void custom(CharSequence text, int duration) {
        initToast();
        if (!TextUtils.isEmpty(text.toString())) {
            toast.setText(text);
            toast.setDuration(duration);
            toast.show();
        }
    }
}
