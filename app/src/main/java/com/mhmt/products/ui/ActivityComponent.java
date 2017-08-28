package com.mhmt.products.ui;

import com.mhmt.products.AppModule;
import com.mhmt.products.network.NetworkServiceModule;
import com.mhmt.products.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class, NetworkServiceModule.class})
public interface ActivityComponent {

  void inject(MainActivity activity);

}
