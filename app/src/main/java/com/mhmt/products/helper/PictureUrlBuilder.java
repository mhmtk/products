package com.mhmt.products.helper;

import com.mhmt.products.Constant;

public class PictureUrlBuilder {

  public static String getUrl(final String url) {
    return Constant.Network.BASEURL.concat(url);
  }
}
