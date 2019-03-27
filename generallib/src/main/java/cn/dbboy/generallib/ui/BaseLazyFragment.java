package cn.dbboy.generallib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by db.boy on 19/1/24.
 * 重写 onFirstVisible 把初始化网络之类的放进去
 * onVisible和onInvisible替代onResume 和 onPause
 */
public class BaseLazyFragment extends Fragment {
    private boolean isVisible = false;
    //第一次能显示的时候不显示
    private boolean isFirstCanVisible = true;
    private boolean isShowFirst = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isVisible = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstCanVisible) {
            isFirstCanVisible = false;
        } else {
            _onVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        _onInvisible();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstCanVisible) {
                isFirstCanVisible = false;
            } else {
                _onVisible();
            }
        } else {
            _onInvisible();
        }
    }


    private void _onVisible() {
        if (!isVisible && getUserVisibleHint()) {
            if (!isShowFirst) {
                onFirstVisible();
                isShowFirst = true;
            }
            onVisible();
            isVisible = true;
        }
    }

    public void onFirstVisible() {
    }

    public void onVisible() {
    }

    private void _onInvisible() {
        if (isVisible) {
            isVisible = false;
            onInvisible();
        }
    }

    public void onInvisible() {
    }
}