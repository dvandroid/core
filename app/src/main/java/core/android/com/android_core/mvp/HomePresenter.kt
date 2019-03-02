package core.android.com.android_core.mvp

import core.android.com.corelib.mvp.BaseMvpPresenterImpl
import io.reactivex.Observable
import io.reactivex.internal.util.HalfSerializer.onNext

class HomePresenter: BaseMvpPresenterImpl<HomeContract.View>(), HomeContract.Presenter {
    override fun loadMenu() {

    }
//        Observable.doSomethingOperators()
//                .compose(this.<T>applyBinding())
//                .subscribe(new Observer<T>() {
//                    @Override
//                    public void onNext(final T value) {
//                        mCallback.doSomethingUI(value);
//                    });
//                }
}