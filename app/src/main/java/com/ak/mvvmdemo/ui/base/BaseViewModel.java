package com.ak.mvvmdemo.ui.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;


import com.ak.mvvmdemo.data.DataManager;
import com.ak.mvvmdemo.data.remote.ErrorHelper;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<N extends BaseNavigator> extends AndroidViewModel implements Observable {

    private DataManager dataManager;

    private N navigatior;

    public ObservableBoolean isLoading() {
        return isLoading;
    }
    public ObservableBoolean isNoData() {
        return isNoData;
    }

    private final ObservableBoolean isLoading = new ObservableBoolean(false);
    private final ObservableBoolean isNoData = new ObservableBoolean(false);
    private ErrorHelper<N> errorHelper;

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    private CompositeDisposable compositeDisposable;


    public BaseViewModel(@NonNull Application application, DataManager dataManager) {
        super(application);
        this.dataManager = dataManager;
        compositeDisposable =new CompositeDisposable();

    }

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public N getNavigatior() {
        return navigatior;
    }

    public void setNavigatior(N navigatior) {
        this.navigatior = navigatior;
        errorHelper=new ErrorHelper<>(this.navigatior);
    }

    public void setLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
    }
    public void setNoData(boolean isNodata) {
        this.isNoData.set(isNodata);
    }
    private transient PropertyChangeRegistry mCallbacks;

    @Override
    public void addOnPropertyChangedCallback(@NonNull OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                mCallbacks = new PropertyChangeRegistry();
            }
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(@NonNull OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.remove(callback);
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public void notifyChange() {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, fieldId, null);
    }


    public ErrorHelper<N> getErrorHelper() {
        return errorHelper;
    }
}
