package cn.dbboy.generallib.mvp;


import cn.dbboy.generallib.GeneralLibManager;
import cn.dbboy.generallib.utils.NetworkUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wang.lichen on 2017/11/14.</br>
 */
public abstract class BasePresenterImpl implements BasePresenter {

    protected CompositeDisposable mDisposable;
    protected BaseView mView;

    public BasePresenterImpl(BaseView mView) {
        this.mView = mView;
        mDisposable = new CompositeDisposable();
        initPresenter();
    }

    protected void initPresenter() {
        mView.setPresenter(this);
    }

    protected CompositeDisposable getComposite() {
        return mDisposable;
    }


    protected <T> void addSubscribtion(Observable observable, Consumer onNext) {
        if (NetworkUtils.isNetworkAvailable(GeneralLibManager.getInstance().getContext())) {
            getComposite().add(observable.subscribeOn(Schedulers.io())//
                    .observeOn(AndroidSchedulers.mainThread())//
                    .subscribe(onNext, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            if (mView != null) {
                                mView.onError();
                            }
                        }
                    }));
        } else {
            mView.onError();
        }

    }

    @Override
    public void unsubscribe() {
        mDisposable.clear();
    }
}