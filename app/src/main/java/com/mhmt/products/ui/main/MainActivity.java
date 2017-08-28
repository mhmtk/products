package com.mhmt.products.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.mhmt.products.Constant;
import com.mhmt.products.Products;
import com.mhmt.products.R;
import com.mhmt.products.domain.Category;
import com.mhmt.products.domain.Product;
import com.mhmt.products.ui.base.BaseActivity;
import com.mhmt.products.ui.productdetail.ProductDetailActivity;
import com.mhmt.products.ui.view.ProductListRecyclerView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mhmt.products.Constant.UI.SAVED_STATE_EXPANDABLE_ITEM_MANAGER;

public class MainActivity extends BaseActivity implements MainMvpView {

  @Inject
  protected MainActivityPresenter mainActivityPresenter;

  @BindView(R.id.recycler_view_products)
  protected ProductListRecyclerView recyclerView;
  private ProductListAdapter productListAdapter;
  @BindView(R.id.empty_view)
  protected View emptyView;
  @BindView(R.id.progress_circle)
  protected ProgressBar progressCircle;

  private RecyclerViewExpandableItemManager itemManager;

  private Bundle savedInstanceState;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ButterKnife.bind(this);
    this.savedInstanceState = savedInstanceState;

    ((Products) getApplication()).getActivityComponent().inject(this);

    productListAdapter = new ProductListAdapter(Collections.<Category>emptyList());

    mainActivityPresenter.onCreate(this);
  }

  @Override protected void onSaveInstanceState(final Bundle outState) {
    outState.putParcelable(SAVED_STATE_EXPANDABLE_ITEM_MANAGER, itemManager.getSavedState());
    super.onSaveInstanceState(outState);
  }

  @Override protected void onRestoreInstanceState(final Bundle savedInstanceState) {
    this.savedInstanceState = savedInstanceState;
    super.onRestoreInstanceState(savedInstanceState);
  }

  @Override protected void onResume() {
    super.onResume();
    mainActivityPresenter.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
    mainActivityPresenter.onPause();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mainActivityPresenter.onDestroy();
  }

  @Override public void initiateUI() {
    final Parcelable eimSavedState = (savedInstanceState != null) ? savedInstanceState.getParcelable(SAVED_STATE_EXPANDABLE_ITEM_MANAGER) : null;
    itemManager = new RecyclerViewExpandableItemManager(eimSavedState);
    final RecyclerView.Adapter wrappedAdapter = itemManager.createWrappedAdapter(productListAdapter);
    final GeneralItemAnimator animator = new RefactoredDefaultItemAnimator();
    animator.setSupportsChangeAnimations(false);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(wrappedAdapter);
    recyclerView.setItemAnimator(animator);
    recyclerView.setHasFixedSize(false);
    recyclerView.setEmptyView(emptyView);
    itemManager.attachRecyclerView(recyclerView);
  }

  public void startProductDetailActivity(final Product product) {
    Intent intent = new Intent(this, ProductDetailActivity.class);
    intent.putExtra(Constant.Navigation.PRODUCT, product);
    startActivity(intent);
  }

  @Override public void updateData(final List<Category> categories) {
    productListAdapter.updateData(categories);
  }

  @Override public void showProgressDialog() {
    progressCircle.setVisibility(View.VISIBLE);
  }

  @Override public void dismissProgressDialog() {
    progressCircle.setVisibility(View.GONE);
  }
}
