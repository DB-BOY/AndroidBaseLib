package cn.dbboy.generallib.ui;

import android.content.Context;

import cn.dbboy.generallib.mvp.BasePresenterImpl;
import cn.dbboy.generallib.mvp.BaseView;

/**
 * Created by DB_BOY on 2019/3/27.
 */
public abstract class BaseViewActivity<T extends BasePresenterImpl> extends BaseActivity implements BaseView<T> {
    protected T mPresenter;

    @Override
    public void setPresenter(T presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void beforeView() {
        attachPresenter();
    }

    protected abstract void attachPresenter();

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

}
