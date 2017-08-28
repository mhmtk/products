package com.mhmt.products.ui.main;

import com.mhmt.products.R;
import com.mhmt.products.domain.Category;
import com.mhmt.products.domain.Product;
import com.mhmt.products.events.DownloadError;
import com.mhmt.products.jobs.DownloadProducts;
import com.mhmt.products.network.DataService;
import com.mhmt.products.ui.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class MainActivityPresenter extends BasePresenter<MainMvpView> implements MainMvpPresenter<MainMvpView>{

  private final DataService dataService;
  private EventBus eventBus;
  private Executor executor;

  @Inject
  public MainActivityPresenter(DataService dataService, EventBus eventBus, Executor executor) {
    this.dataService = dataService;
    this.eventBus = eventBus;
    this.executor = executor;
  }

  @Override public void onCreate(final MainMvpView mvpView) {
    super.onCreate(mvpView);
    getMvpView().initiateUI();
    if (getMvpView().isConnectedToNetwork()) {
      getMvpView().showProgressDialog();
      executor.execute(new DownloadProducts(dataService, eventBus));
    } else {
      getMvpView().displayError(R.string.error_message_connection);
    }
  }

  @Override public void onResume() {
    eventBus.register(this);
  }

  @Override public void onPause() {
    eventBus.unregister(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void productSelected(final Product product) {
    getMvpView().startProductDetailActivity(product);
  }

  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  public void downloadError(final DownloadError downloadError) {
    getMvpView().displayError(R.string.error_message_download);
    getMvpView().dismissProgressDialog();
  }

  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  public void categoriesDownloaded(final List<Category> categories) {
    getMvpView().dismissProgressDialog();
    if (categories != null && categories.size() != 0) {
      getMvpView().updateData(categories);
    } else {
      getMvpView().displayError(R.string.error_message_no_result);
    }
  }
}
