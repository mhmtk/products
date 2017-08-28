package com.mhmt.products.ui.productdetail;

import com.mhmt.products.domain.Product;
import com.mhmt.products.helper.PictureUrlBuilder;
import com.mhmt.products.ui.base.BasePresenter;

public class ProductDetailPresenter extends BasePresenter<ProductDetailMvpView> implements
                                                                                ProductDetailMvpPresenter<ProductDetailMvpView> {

  private Product product;

  public ProductDetailPresenter(final Product product) {
    this.product = product;
  }

  @Override public void onCreate(final ProductDetailMvpView mvpView) {
    super.onCreate(mvpView);
    getMvpView().showProduct(product);
    getMvpView().showPicture(PictureUrlBuilder.getUrl(product.getUrl()));
  }

  @Override public void onResume() {

  }

  @Override public void onPause() {

  }

  @Override public void onDestroy() {
  }

}
