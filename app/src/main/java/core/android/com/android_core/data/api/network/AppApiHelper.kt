package core.android.com.android_core.data.api.network

import core.android.com.android_core.data.api.endpoints.FirstTestApi
import core.android.com.android_core.data.dataclass.ApiRequest
import core.android.com.android_core.data.dataclass.model.LoginResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val api: FirstTestApi) : ApiHelper {
    override fun performServerLogin(request: ApiRequest.ServerLoginRequest): Observable<LoginResponse> {
//      return  api.login(request)
//                .map { ResultAsyncState.Completed(it) as ResultAsyncState<LoginResponse> }
//                .onErrorReturn { ResultAsyncState.Failed(it) }
//                .startWith(ResultAsyncState.Started())

        return api.login(request)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .doOnTerminate { view.hideLoading() }
//                .subscribe(
//                        { postList -> view.updatePosts(postList) },
//                        { view.showError(R.string.unknown_error) }
//                )
    }

    override fun getUserList(request: ApiRequest.UserListrequest): List<Observable<LoginResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
