package com.mhmt.products;

import com.mhmt.products.domain.Category;
import com.mhmt.products.domain.Product;
import com.mhmt.products.events.DownloadError;
import com.mhmt.products.network.DataService;
import com.mhmt.products.ui.main.MainActivityPresenter;
import com.mhmt.products.ui.main.MainMvpView;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTests {

  @Mock private MainMvpView view;

  @Mock private DataService dataService;
  @Mock private EventBus eventBus;
  @Mock private Executor executor;

  @Mock private Category category;
  @Mock private Product product;

  @Mock DownloadError downloadError;

  private MainActivityPresenter presenter;

  @Before
  public void setupMocksAndView() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void noInternetConnection() {
    getPresenter(false);
    verify(view).displayError(R.string.error_message_connection);
  }

  @Test
  public void productSelect_startsDetailActivity(){
    presenter = getPresenter(true);
    presenter.productSelected(product);
    verify(view).startProductDetailActivity(product);
  }

  @Test
  public void downloadError_displaysError(){
    presenter = getPresenter(true);
    presenter.downloadError(downloadError);
    verify(view).displayError(R.string.error_message_download);
  }

  @Test
  public void downloadError_dismissesDialog(){
    presenter = getPresenter(true);
    presenter.downloadError(downloadError);
    verify(view).dismissProgressDialog();
  }

  @Test
  public void downloadSuccess_dismissesDialog(){
    presenter = getPresenter(true);

    final List<Category> categories = new ArrayList<>();
    categories.add(category);

    presenter.categoriesDownloaded(categories);
    verify(view).dismissProgressDialog();
  }

  @Test
  public void downloadSuccess_updatesData(){
    presenter = getPresenter(true);

    final List<Category> categories = new ArrayList<>();
    categories.add(category);

    presenter.categoriesDownloaded(categories);
    verify(view).updateData(categories);
  }

  @Test
  public void downloadSuccess_emptyDataDisplaysError(){
    presenter = getPresenter(true);

    final List<Category> categories = Collections.emptyList();

    presenter.categoriesDownloaded(categories);
    verify(view).displayError(R.string.error_message_no_result);
  }

  @Test
  public void downloadSuccess_nullDataDisplaysError(){
    presenter = getPresenter(true);
    presenter.categoriesDownloaded(null);
    verify(view).displayError(R.string.error_message_no_result);
  }

  private MainActivityPresenter getPresenter(boolean online) {
    final MainActivityPresenter presenter = new MainActivityPresenter(dataService, eventBus, executor);
    when(view.isConnectedToNetwork()).thenReturn(online);
    presenter.onCreate(view);
    return presenter;
  }

}