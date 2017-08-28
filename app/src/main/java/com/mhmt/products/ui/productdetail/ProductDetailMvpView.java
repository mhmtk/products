package com.mhmt.products.ui.productdetail;

import com.mhmt.products.domain.Product;
import com.mhmt.products.ui.base.MvpView;

public interface ProductDetailMvpView extends MvpView {

  void showProduct(Product product);

  void showPicture(String url);
}
