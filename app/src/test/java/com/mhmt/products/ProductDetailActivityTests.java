package com.mhmt.products;

import com.mhmt.products.domain.Product;
import com.mhmt.products.helper.PictureUrlBuilder;
import com.mhmt.products.ui.productdetail.ProductDetailMvpView;
import com.mhmt.products.ui.productdetail.ProductDetailPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductDetailActivityTests {

  @Mock private ProductDetailMvpView view;

  @Mock private Product product;

  @Before
  public void setupMocksAndView() {
    MockitoAnnotations.initMocks(this);
    when(product.getUrl()).thenReturn("Pic.jpg");
  }

  @Test
  public void showsProduct() {
    ProductDetailPresenter presenter = new ProductDetailPresenter(product);
    presenter.onCreate(view);
    verify(view).showProduct(product);
  }

  @Test
  public void showsPicture() {
    ProductDetailPresenter presenter = new ProductDetailPresenter(product);
    presenter.onCreate(view);
    verify(view).showPicture(PictureUrlBuilder.getUrl(product.getUrl()));
  }

}
