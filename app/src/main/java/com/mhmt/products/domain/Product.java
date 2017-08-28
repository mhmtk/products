package com.mhmt.products.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

  private int id;
  private String categoryId;
  private String name;
  private String url;
  private String description;
  private SalePrice salePrice;

  public Product() {
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(final String categoryId) {
    this.categoryId = categoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public SalePrice getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(final SalePrice salePrice) {
    this.salePrice = salePrice;
  }


  @Override public int describeContents() { return 0; }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.categoryId);
    dest.writeString(this.name);
    dest.writeString(this.url);
    dest.writeString(this.description);
    dest.writeParcelable(this.salePrice, flags);
  }

  protected Product(Parcel in) {
    this.id = in.readInt();
    this.categoryId = in.readString();
    this.name = in.readString();
    this.url = in.readString();
    this.description = in.readString();
    this.salePrice = in.readParcelable(SalePrice.class.getClassLoader());
  }

  public static final Creator<Product> CREATOR = new Creator<Product>() {
    @Override public Product createFromParcel(Parcel source) {return new Product(source);}

    @Override public Product[] newArray(int size) {return new Product[size];}
  };

}
