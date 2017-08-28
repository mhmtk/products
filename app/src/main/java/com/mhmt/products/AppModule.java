package com.mhmt.products;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Application provideApplication() {
    return application;
  }

  @Provides
  @Singleton
  EventBus provideEventBus() {
    return EventBus.getDefault();
  }

  @Provides
  @Singleton
  Executor provideExecutor() {
    return Executors.newCachedThreadPool();
  }
}
