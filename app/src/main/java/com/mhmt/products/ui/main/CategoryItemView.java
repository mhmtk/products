package com.mhmt.products.ui.main;

import android.view.View;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;
import com.mhmt.products.R;
import com.mhmt.products.domain.Category;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryItemView extends AbstractExpandableItemViewHolder {

  @BindView(R.id.text_view_title)
  protected TextView textViewTitle;

  public CategoryItemView(final View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bind(final Category category) {
    textViewTitle.setText(category.getName());
  }
}
