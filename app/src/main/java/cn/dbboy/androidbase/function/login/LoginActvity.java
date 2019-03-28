package cn.dbboy.androidbase.function.login;

import cn.dbboy.generallib.ui.BaseViewActivity;

/**
 * Created by DB_BOY on 2019/3/28.
 */
public class LoginActvity extends BaseViewActivity<LoginPresenterImpl> {


    @Override
    protected void attachPresenter() {
        mPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onError() {
    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
