package desynova.harsh.harshassignment.ui.component.details;


import desynova.harsh.harshassignment.ui.base.listeners.BaseView;

abstract class DetailsContract {

    public interface View extends BaseView {
        void startActivity();
    }


    public interface Presenter {
        void unSubscribe();
    }
}
