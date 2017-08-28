package com.mhmt.products.ui.base;

public interface MvpPresenter<V extends MvpView> {

  void onCreate(V mvpView);

  void onResume();

  void onPause();

  void onDestroy();

}
