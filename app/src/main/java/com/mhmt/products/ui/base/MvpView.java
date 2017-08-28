package com.mhmt.products.ui.base;

import android.support.annotation.StringRes;

public interface MvpView {

  void showProgressDialog();

  void dismissProgressDialog();

  void hideSoftKeyboard();

  void displayError(@StringRes int message);

  boolean isConnectedToNetwork();
}
