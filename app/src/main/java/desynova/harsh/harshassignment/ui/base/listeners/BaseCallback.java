package desynova.harsh.harshassignment.ui.base.listeners;

public interface BaseCallback {
    <T> void onSuccess(T type);

    void onFail();
}
