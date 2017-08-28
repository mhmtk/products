package com.mhmt.products.ui.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.mhmt.products.R;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ContentLoadingProgressDialog extends ProgressDialog {

  private static final long MIN_SHOW_TIME = 500L; // ms
  private static final long MIN_DELAY = 500L; // ms
  private final Handler handler = new Handler(Looper.getMainLooper());
  private final AtomicBoolean showPosted = new AtomicBoolean(false);
  private final AtomicBoolean dismissPosted = new AtomicBoolean(false);
  private final AtomicBoolean dismissed = new AtomicBoolean(false);
  private volatile long startTime;

  public ContentLoadingProgressDialog(final Context context) {
    super(context);
    setTitle(null);
    setMessage(context.getString(R.string.progress_dialog_message));
    setIndeterminate(true);
    setProgressStyle(ProgressDialog.STYLE_SPINNER);
    setCancelable(true);
  }

  public ContentLoadingProgressDialog(final Context context, final String progressMessage) {
    this(context);
    if (!TextUtils.isEmpty(progressMessage)) {
      setMessage(progressMessage);
    }
  }

  @Override
  public synchronized void show() {
    dismissed.set(false);
    if (!showPosted.get()) {
      handler.postDelayed(new ShowRunnable(), MIN_DELAY);
      showPosted.set(true);
    }
  }

  @Override
  public synchronized void dismiss() {
    dismissed.set(true);
    final long diff = System.currentTimeMillis() - startTime;
    if ((diff >= MIN_SHOW_TIME)) {
      dismissImmediately();
    } else {
      if (!dismissPosted.get()) {
        handler.postDelayed(new DismissRunnable(), MIN_SHOW_TIME - diff);
        dismissPosted.set(true);
      }
    }
  }

  public void showImmediately() {
    dismissed.set(false);
    startTime = System.currentTimeMillis();
    try {
      if (!isShowing()) {
        super.show();
      }
    } catch (IllegalArgumentException exception) {

    }
  }

  public void dismissImmediately() {
    dismissed.set(true);
    startTime = 0L;
    try {
      if (isShowing()) {
        super.dismiss();
      }
    } catch (IllegalArgumentException exception) {

    }
  }

  @Override
  public void onDetachedFromWindow() {
    dismissImmediately();
    handler.removeCallbacksAndMessages(null);
    super.onDetachedFromWindow();
  }

  //region Inner Classes

  private class DismissRunnable implements Runnable {

    @Override
    public void run() {
      dismissImmediately();
      dismissPosted.set(false);
    }
  }

  private class ShowRunnable implements Runnable {

    @Override
    public void run() {
      if (!dismissed.get()) {
        showImmediately();
      }
      showPosted.set(false);
    }
  }

  //endregion
}
