package desynova.harsh.harshassignment.ui.base;


import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

import desynova.harsh.harshassignment.ui.base.listeners.BaseView;


public abstract class Presenter<T extends BaseView> {

    private AtomicBoolean isViewAlive = new AtomicBoolean();
    private WeakReference<T> view;

    public T getView() {
        return view.get();
    }

    public void setView(T view) {
        this.view = new WeakReference<>(view);
    }

    public void initialize(Bundle extras) {
    }

    public void start() {
        isViewAlive.set(true);
    }

    void finalizeView() {
        isViewAlive.set(false);
    }


}
