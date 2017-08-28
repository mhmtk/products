package com.mhmt.products.ui.main;

import android.support.annotation.IntRange;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.mhmt.products.R;
import com.mhmt.products.domain.Category;

import java.util.List;

public class ProductListAdapter extends AbstractExpandableItemAdapter<CategoryItemView, ProductItemView> {

  private List<Category> categories;

  public ProductListAdapter(List<Category> categories) {
    this.categories = categories;
    this.setHasStableIds(true);
  }

  @Override public int getGroupCount() {
    return categories.size();
  }

  @Override public int getChildCount(final int groupPosition) {
    return categories.get(groupPosition).getProducts().size();
  }

  @Override public long getGroupId(final int groupPosition) {
    return categories.get(groupPosition).getId();
  }

  @Override public long getChildId(final int groupPosition, final int childPosition) {
    return categories.get(groupPosition).getProducts().get(childPosition).getId();
  }

  @Override public CategoryItemView onCreateGroupViewHolder(final ViewGroup parent,
                                                            @IntRange(from = -8388608L, to = 8388607L) final int viewType) {
    return new CategoryItemView(LayoutInflater.from(parent.getContext())
                                       .inflate(R.layout.item_view_category, parent, false));
  }

  @Override public ProductItemView onCreateChildViewHolder(final ViewGroup parent,
                                                           @IntRange(from = -8388608L, to = 8388607L) final int viewType) {
    return new ProductItemView(LayoutInflater.from(parent.getContext())
                                       .inflate(R.layout.item_view_product, parent, false));
  }

  @Override public void onBindGroupViewHolder(final CategoryItemView holder, final int groupPosition,
                                              @IntRange(from = -8388608L, to = 8388607L) final int viewType) {
    holder.bind(categories.get(groupPosition));
  }

  @Override
  public void onBindChildViewHolder(final ProductItemView holder, final int groupPosition, final int childPosition,
                                    @IntRange(from = -8388608L, to = 8388607L) final int viewType) {
    holder.bind(categories.get(groupPosition).getProducts().get(childPosition));
  }

  @Override
  public boolean onCheckCanExpandOrCollapseGroup(final CategoryItemView holder, final int groupPosition, final int x,
                                                 final int y,
                                                 final boolean expand) {
    return true;
  }

  public void updateData(final List<Category> categories) {
    this.categories = categories;
    notifyDataSetChanged();
  }

}
