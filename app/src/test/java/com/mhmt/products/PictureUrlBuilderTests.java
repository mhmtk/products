package com.mhmt.products;

import com.mhmt.products.helper.PictureUrlBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertTrue;

@RunWith(JUnit4.class)
public class PictureUrlBuilderTests {

  @Test
  public void buildsCorrectly() {
    assertTrue("Should be true", PictureUrlBuilder.getUrl("/Food.jpg").equals("http://mobcategories.s3-website-eu-west-1.amazonaws.com/Food.jpg"));
  }
}
