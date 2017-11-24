package desynova.harsh.harshassignment.ui.component.details;

import android.os.Bundle;

import javax.inject.Inject;

import desynova.harsh.harshassignment.ui.base.Presenter;
import desynova.harsh.harshassignment.usecase.AppUseCase;

public class DetailsPresenter extends Presenter<DetailsContract.View> implements DetailsContract.Presenter {

    private final AppUseCase appUseCase;

    @Inject
    public DetailsPresenter(AppUseCase appUseCase) {
        this.appUseCase = appUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getView().startActivity();
    }

    @Override
    public void unSubscribe() {
        appUseCase.unSubscribe();
    }


}
