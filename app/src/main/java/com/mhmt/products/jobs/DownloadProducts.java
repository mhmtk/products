package com.mhmt.products.jobs;

import com.mhmt.products.domain.Category;
import com.mhmt.products.events.DownloadError;
import com.mhmt.products.network.DataService;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public class DownloadProducts implements Runnable {

  private final EventBus eventBus;
  private final DataService dataService;

  public DownloadProducts(final DataService dataService, final EventBus eventBus) {
    this.dataService = dataService;
    this.eventBus = eventBus;
  }

  @Override public void run() {
    try {
      final Response<List<Category>> categoryResponse = dataService.fetchCategories().execute();
      if (!categoryResponse.isSuccessful()) {
        eventBus.postSticky(new DownloadError());
      } else {
        final List<Category> categories = categoryResponse.body();
        eventBus.postSticky(categories == null ? Collections.<Category>emptyList() : categories);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
