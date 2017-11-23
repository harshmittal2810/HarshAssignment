package desynova.harsh.harshassignment.ui.component.notification;

import desynova.harsh.harshassignment.data.remote.dto.TabThree;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import desynova.harsh.harshassignment.ui.base.listeners.BaseView;

interface NotificationContract {

    interface View extends BaseView {
        void startActivity();

        void initializeList(TabThree tabThree);

        void setLoaderVisibility(boolean isVisible);

        void setNoDataVisibility(boolean isVisible);

        void setListVisibility(boolean isVisible);

        void showMessage(String msg);

    }
}
