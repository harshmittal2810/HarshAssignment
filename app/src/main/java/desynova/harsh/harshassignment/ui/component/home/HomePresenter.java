package desynova.harsh.harshassignment.ui.component.home;

import android.os.Bundle;

import javax.inject.Inject;

import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.ui.base.Presenter;
import desynova.harsh.harshassignment.ui.base.listeners.BaseCallback;
import desynova.harsh.harshassignment.usecase.AppUseCase;

import static desynova.harsh.harshassignment.utils.ObjectUtil.isNull;

public class HomePresenter extends Presenter<HomeContract.View> {

    private final AppUseCase appUseCase;
    private TabOne tabOneDataOne;
    private final BaseCallback callback1 = new BaseCallback() {
        @Override
        public <T> void onSuccess(T type) {
            TabOne tabOne = (TabOne) type;
            HomePresenter.this.tabOneDataOne = tabOne;
            if (!isNull(tabOne)) {
                if (tabOne.getSuccess()) {
                    showList1(true);
                    getView().initializeList1(tabOne);
                } else {
                    showList1(false);
                    getView().showMessage(tabOne.getErrorMessage());
                }
            } else {
                showList1(false);
            }
            getView().setLoaderVisibility(false);
        }

        @Override
        public void onFail() {
            showList1(false);
            getView().setLoaderVisibility(false);
        }
    };
    private TabOne tabOneDataTwo;
    private final BaseCallback callback2 = new BaseCallback() {
        @Override
        public <T> void onSuccess(T type) {
            TabOne tabOne = (TabOne) type;
            HomePresenter.this.tabOneDataTwo = tabOne;
            if (!isNull(tabOne)) {
                if (tabOne.getSuccess()) {
                    showList2(true);
                    getView().initializeList2(tabOne);
                } else {
                    showList2(false);
                    getView().showMessage(tabOne.getErrorMessage());
                }
            } else {
                showList1(false);
            }
            getView().setLoaderVisibility(false);
        }

        @Override
        public void onFail() {
            showList2(false);
            getView().setLoaderVisibility(false);
        }
    };

    @Inject
    public HomePresenter(AppUseCase appUseCase) {
        this.appUseCase = appUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getView().startActivity();
        getDataFotTabOne();
        getDataFotTabTwo();
    }

    private void getDataFotTabOne() {
        getView().setLoaderVisibility(true);
        getView().setNoDataVisibility(false);
        getView().setListVisibility(false);
        appUseCase.getDataTabDataOne(callback1);
    }

    private void getDataFotTabTwo() {
        getView().setLoaderVisibility(true);
        getView().setNoDataVisibility(false);
        getView().setListVisibility(false);
        appUseCase.getDataTabDataTwo(callback2);
    }

    private void showList1(boolean isVisible) {
        getView().setNoDataVisibility(!isVisible);
        getView().setListVisibility(isVisible);
    }

    private void showList2(boolean isVisible) {
        getView().setNoDataVisibility(!isVisible);
        getView().setListVisibility(isVisible);
    }


}
