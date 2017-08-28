package com.mhmt.products.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhmt.products.R;
import com.mhmt.products.domain.Product;
import com.mhmt.products.helper.PictureUrlBuilder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductItemView extends RecyclerView.ViewHolder implements View.OnClickListener {

  @BindView(R.id.text_view_title)
  protected TextView titleText;
  @BindView(R.id.imageView)
  protected ImageView imageView;
  private Product product;

  public ProductItemView(final View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
    itemView.setOnClickListener(this);
  }

  public void bind(final Product product) {
    this.product = product;
    titleText.setText(product.getName());
    Picasso.with(itemView.getContext())
           .load(PictureUrlBuilder.getUrl(product.getUrl()))
           .error(R.drawable.ic_placeholder_picture)
           .into(imageView);
  }

  @Override public void onClick(final View v) {
    EventBus.getDefault().post(product);
  }
}