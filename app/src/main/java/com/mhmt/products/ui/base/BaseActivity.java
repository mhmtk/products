package com.mhmt.products.ui.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mhmt.products.R;
import com.mhmt.products.ui.view.ContentLoadingProgressDialog;

public class BaseActivity extends AppCompatActivity {

  private ContentLoadingProgressDialog contentLoadingProgressDialog;
  private InputMethodManager inputMethodManager;

  @Override protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
  }

  public void showProgressDialog() {
    if (contentLoadingProgressDialog == null) {
      contentLoadingProgressDialog = new ContentLoadingProgressDialog(this);
      contentLoadingProgressDialog.setCancelable(false);
    }
    contentLoadingProgressDialog.show();
  }

  public void dismissProgressDialog() {
    if (contentLoadingProgressDialog != null) {
      contentLoadingProgressDialog.dismiss();
    }
  }

  public void hideSoftKeyboard() {
    final View focusedView = getCurrentFocus();
    if (focusedView != null) {
      inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
    }
  }

  public void displayError(@StringRes int message) {
    Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
    toast.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.background));
    toast.show();
  }

  public boolean isConnectedToNetwork() {
    ConnectivityManager cm =
        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    return netInfo != null && netInfo.isConnectedOrConnecting();
  }
}
