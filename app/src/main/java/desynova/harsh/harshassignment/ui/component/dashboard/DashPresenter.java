package desynova.harsh.harshassignment.ui.component.dashboard;

import android.os.Bundle;

import javax.inject.Inject;

import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import desynova.harsh.harshassignment.ui.base.Presenter;
import desynova.harsh.harshassignment.ui.base.listeners.BaseCallback;
import desynova.harsh.harshassignment.usecase.AppUseCase;

import static desynova.harsh.harshassignment.utils.ObjectUtil.isNull;

public class DashPresenter extends Presenter<DashContract.View> {

    private final AppUseCase appUseCase;
    private TabTwo tabTwo;
    private final BaseCallback callback = new BaseCallback() {
        @Override
        public <T> void onSuccess(T type) {
            TabTwo tabTwo = (TabTwo) type;
            DashPresenter.this.tabTwo = tabTwo;
            if (!isNull(tabTwo)) {
                if (tabTwo.getSuccess()) {
                    showList(true);
                    getView().initializeList(tabTwo);
                } else {
                    showList(false);
                    getView().showMessage(tabTwo.getErrorMessage());
                }
            } else {
                showList(false);
            }
            getView().setLoaderVisibility(false);
        }

        @Override
        public void onFail() {
            showList(false);
            getView().setLoaderVisibility(false);
        }
    };

    @Inject
    public DashPresenter(AppUseCase appUseCase) {
        this.appUseCase = appUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getView().startActivity();
        getDataForTabTwo();
    }

    private void getDataForTabTwo() {
        getView().setLoaderVisibility(true);
        getView().setNoDataVisibility(false);
        getView().setListVisibility(false);
        appUseCase.getDataTabTwo(callback);
    }

    private void showList(boolean isVisible) {
        getView().setNoDataVisibility(!isVisible);
        getView().setListVisibility(isVisible);
    }

}
