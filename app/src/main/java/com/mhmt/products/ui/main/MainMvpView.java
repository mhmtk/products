package com.mhmt.products.ui.main;

import com.mhmt.products.domain.Category;
import com.mhmt.products.domain.Product;
import com.mhmt.products.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

  void initiateUI();

  void startProductDetailActivity(final Product product);

  void updateData(List<Category> categories);

}
