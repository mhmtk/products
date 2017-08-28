package com.mhmt.products;

import android.app.Application;

import com.mhmt.products.network.NetworkModule;
import com.mhmt.products.ui.ActivityComponent;
import com.mhmt.products.ui.DaggerActivityComponent;

public class Products extends Application {


  private ActivityComponent activityComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    activityComponent = DaggerActivityComponent.builder()
                                               .appModule(new AppModule(this))
                                               .networkModule(new NetworkModule(Constant.Network.BASEURL))
                                               .build();
  }

  public ActivityComponent getActivityComponent() {
    return activityComponent;
  }

}
