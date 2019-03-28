package cn.dbboy.generallib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DB_BOY on 2019/3/27.
 *
 * 基础Activity封装，不涉及到mvp的实现</br>
 * 如果用到mvp
 * @see BaseViewActivity
 *
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        beforeView();
        initView();
        afterView();
    }
    /**
     * view后操作，初始化数据等，一般用到view的操作
     */
    protected void afterView() {
        
    }

    /**
     * view前操作，初始化数据,presenter
     */
    protected void beforeView() {

    }
    
    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置layout id
     *
     * @return
     */
    protected abstract int getLayoutId();

}
