package desynova.harsh.harshassignment.ui.component.dashboard;

import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import desynova.harsh.harshassignment.ui.base.listeners.BaseView;

interface DashContract {

    interface View extends BaseView {
        void startActivity();

        void initializeList(TabTwo tabOne);

        void setLoaderVisibility(boolean isVisible);

        void setNoDataVisibility(boolean isVisible);

        void setListVisibility(boolean isVisible);

        void showMessage(String msg);

    }
}
