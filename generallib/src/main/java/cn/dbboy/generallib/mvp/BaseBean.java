package cn.dbboy.generallib.mvp;

import java.io.Serializable;

/**
 * Created by wang.lichen on 2017/11/14.
 * <p>
 * base数据
 * 所有返回json数据bean,必须继承
 */

public class BaseBean implements Serializable {
    private int code;
    private String status;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}