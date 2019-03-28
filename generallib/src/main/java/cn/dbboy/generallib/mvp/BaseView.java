package cn.dbboy.generallib.mvp;

import android.content.Context;

/**
 * Created by wang.lichen on 2017/11/14.</br>
 */
public interface BaseView<T> {

    void setPresenter(T presenter);

    Context getContext();

    void onError();

    void showMsg(String msg);

    //显示loading
    void showLoading();

    //关闭loading
    void closeLoading();

}