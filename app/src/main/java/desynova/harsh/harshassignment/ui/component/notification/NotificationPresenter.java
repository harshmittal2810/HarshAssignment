package desynova.harsh.harshassignment.ui.component.notification;

import android.os.Bundle;

import javax.inject.Inject;

import desynova.harsh.harshassignment.data.remote.dto.TabThree;
import desynova.harsh.harshassignment.ui.base.Presenter;
import desynova.harsh.harshassignment.ui.base.listeners.BaseCallback;
import desynova.harsh.harshassignment.usecase.AppUseCase;

import static desynova.harsh.harshassignment.utils.ObjectUtil.isNull;

public class NotificationPresenter extends Presenter<NotificationContract.View> {

    private final AppUseCase appUseCase;
    private TabThree tabThree;
    private final BaseCallback callback = new BaseCallback() {
        @Override
        public <T> void onSuccess(T type) {
            TabThree tabThree = (TabThree) type;
            if (getView() != null) {
                NotificationPresenter.this.tabThree = tabThree;
                if (!isNull(tabThree)) {
                    if (tabThree.getSuccess()) {
                        showList(true);
                        getView().initializeList(tabThree);
                    } else {
                        showList(false);
                        getView().showMessage(tabThree.getErrorMessage());
                    }
                } else {
                    showList(false);
                }
                getView().setLoaderVisibility(false);
            }
        }

        @Override
        public void onFail() {
            if (getView() != null) {
                showList(false);
                getView().setLoaderVisibility(false);
            }
        }
    };

    @Inject
    public NotificationPresenter(AppUseCase appUseCase) {
        this.appUseCase = appUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getView().startActivity();
        getDataForTabThree();
    }

    private void getDataForTabThree() {
        if (getView() != null) {
            getView().setLoaderVisibility(true);
            getView().setNoDataVisibility(false);
            getView().setListVisibility(false);
            appUseCase.getDataTabThree(callback);
        }
    }

    private void showList(boolean isVisible) {
        if (getView() != null) {
            getView().setNoDataVisibility(!isVisible);
            getView().setListVisibility(isVisible);
        }
    }

}
