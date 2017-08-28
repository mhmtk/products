package com.mhmt.products.ui.productdetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.mhmt.products.Constant;
import com.mhmt.products.R;
import com.mhmt.products.databinding.ActivityProductDetailBinding;
import com.mhmt.products.domain.Product;
import com.mhmt.products.ui.base.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends BaseActivity implements ProductDetailMvpView {

  private ProductDetailPresenter presenter;

  @BindView(R.id.image_view)
  protected ImageView imageView;
  private ActivityProductDetailBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
    ButterKnife.bind(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    presenter = new ProductDetailPresenter((Product) getIntent().getParcelableExtra(Constant.Navigation.PRODUCT));
    presenter.onCreate(this);
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override public boolean onOptionsItemSelected(final MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override protected void onPause() {
    super.onPause();
    presenter.onPause();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  @Override public void showProduct(final Product product) {
    binding.setProduct(product);
  }

  @Override public void showPicture(final String url) {
    Picasso.with(this)
           .load(url)
           .fit()
           .centerInside()
           .error(R.drawable.ic_placeholder_picture)
           .into(imageView);
  }

}
