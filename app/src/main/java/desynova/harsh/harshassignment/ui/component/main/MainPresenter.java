package desynova.harsh.harshassignment.ui.component.main;

import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import desynova.harsh.harshassignment.ui.base.Presenter;
import desynova.harsh.harshassignment.usecase.AppUseCase;

public class MainPresenter extends Presenter<MainContract.View> implements MainContract.Presenter {

    private final AppUseCase appUseCase;
    private List<String> categoriesList;

    @Inject
    public MainPresenter(AppUseCase appUseCase) {
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
