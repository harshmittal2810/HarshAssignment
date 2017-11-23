package desynova.harsh.harshassignment.usecase;

import desynova.harsh.harshassignment.ui.base.listeners.BaseCallback;

interface UseCase {
    void getDataTabDataOne(final BaseCallback callback);
    void getDataTabDataTwo(final BaseCallback callback);
    void getDataTabTwo(final BaseCallback callback);
    void getDataTabThree(final BaseCallback callback);
}
