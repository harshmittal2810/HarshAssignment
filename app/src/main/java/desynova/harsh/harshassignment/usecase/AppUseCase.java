package desynova.harsh.harshassignment.usecase;

import javax.inject.Inject;

import desynova.harsh.harshassignment.data.DataRepository;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.data.remote.dto.TabThree;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import desynova.harsh.harshassignment.ui.base.listeners.BaseCallback;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class AppUseCase implements UseCase {
    Single<TabOne> tabOneSingleOne;
    Single<TabOne> tabOneSingleTwo;
    Single<TabTwo> tabTwoSingle;
    Single<TabThree> tabThreeSingle;
    private DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Disposable newsDisposable;
    private DisposableSingleObserver<TabOne> disposableSingleObserverTabDataOne;
    private DisposableSingleObserver<TabOne> disposableSingleObserverTabDataTwo;
    private DisposableSingleObserver<TabTwo> disposableSingleObserverTabTwo;
    private DisposableSingleObserver<TabThree> disposableSingleObserverTabThree;


    @Inject
    public AppUseCase(DataRepository dataRepository, CompositeDisposable compositeDisposable) {
        this.dataRepository = dataRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void unSubscribe() {
        if (!compositeDisposable.isDisposed()) {
            if (newsDisposable != null)
                compositeDisposable.remove(newsDisposable);
        }
    }

    @Override
    public void getDataTabDataOne(BaseCallback callback) {
        disposableSingleObserverTabDataOne = new DisposableSingleObserver<TabOne>() {
            @Override
            public void onSuccess(TabOne tabOne) {
                callback.onSuccess(tabOne);
            }

            @Override
            public void onError(Throwable e) {
                callback.onFail();
            }
        };
        if (!compositeDisposable.isDisposed()) {
            tabOneSingleOne = dataRepository.requestTabDataOne();
            newsDisposable = tabOneSingleOne.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(disposableSingleObserverTabDataOne);
            compositeDisposable.add(newsDisposable);
        }
    }

    @Override
    public void getDataTabDataTwo(BaseCallback callback) {
        disposableSingleObserverTabDataTwo = new DisposableSingleObserver<TabOne>() {
            @Override
            public void onSuccess(TabOne tabOne) {
                callback.onSuccess(tabOne);
            }

            @Override
            public void onError(Throwable e) {
                callback.onFail();
            }
        };
        if (!compositeDisposable.isDisposed()) {
            tabOneSingleTwo = dataRepository.requestTabDataTwo();
            newsDisposable = tabOneSingleTwo.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(disposableSingleObserverTabDataTwo);
            compositeDisposable.add(newsDisposable);
        }
    }

    @Override
    public void getDataTabTwo(BaseCallback callback) {
        disposableSingleObserverTabTwo = new DisposableSingleObserver<TabTwo>() {
            @Override
            public void onSuccess(TabTwo tabTwo) {
                callback.onSuccess(tabTwo);
            }

            @Override
            public void onError(Throwable e) {
                callback.onFail();
            }
        };
        if (!compositeDisposable.isDisposed()) {
            tabTwoSingle = dataRepository.requestTabTwo();
            newsDisposable = tabTwoSingle.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(disposableSingleObserverTabTwo);
            compositeDisposable.add(newsDisposable);
        }
    }

    @Override
    public void getDataTabThree(BaseCallback callback) {
        disposableSingleObserverTabThree = new DisposableSingleObserver<TabThree>() {
            @Override
            public void onSuccess(TabThree tabThree) {
                callback.onSuccess(tabThree);
            }

            @Override
            public void onError(Throwable e) {
                callback.onFail();
            }
        };
        if (!compositeDisposable.isDisposed()) {
            tabThreeSingle = dataRepository.requestTabThree();
            newsDisposable = tabThreeSingle.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(disposableSingleObserverTabThree);
            compositeDisposable.add(newsDisposable);
        }
    }
}
