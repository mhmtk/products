package com.mhmt.products.network;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = { NetworkModule.class })
public class NetworkServiceModule {

  @Provides static DataService dataService(final Retrofit retrofit) {
    return retrofit.create(DataService.class);
  }
}
