package com.mhmt.products.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class SalePrice implements Parcelable {

  private String amount;
  private String currency;

  public SalePrice() {
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(final String amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(final String currency) {
    this.currency = currency;
  }


  @Override public int describeContents() { return 0; }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.amount);
    dest.writeString(this.currency);
  }

  protected SalePrice(Parcel in) {
    this.amount = in.readString();
    this.currency = in.readString();
  }

  public static final Parcelable.Creator<SalePrice> CREATOR = new Parcelable.Creator<SalePrice>() {
    @Override public SalePrice createFromParcel(Parcel source) {return new SalePrice(source);}

    @Override public SalePrice[] newArray(int size) {return new SalePrice[size];}
  };
}
