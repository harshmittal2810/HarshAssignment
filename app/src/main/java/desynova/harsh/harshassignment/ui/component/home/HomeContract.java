package desynova.harsh.harshassignment.ui.component.home;

import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.ui.base.listeners.BaseView;

interface HomeContract {

    interface View extends BaseView {
        void startActivity();

        void initializeList1(TabOne tabOne);

        void initializeList2(TabOne tabOne);

        void setLoaderVisibility(boolean isVisible);

        void setNoDataVisibility(boolean isVisible);

        void setListVisibility(boolean isVisible);

        void showMessage(String msg);

    }
}
