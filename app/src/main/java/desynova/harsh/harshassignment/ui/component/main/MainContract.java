package desynova.harsh.harshassignment.ui.component.main;


import desynova.harsh.harshassignment.ui.base.listeners.BaseView;

abstract class MainContract {

    public interface View extends BaseView {
        void startActivity();

        void setToolBarTitle(String title);
    }


    public interface Presenter {
        void unSubscribe();
    }
}
