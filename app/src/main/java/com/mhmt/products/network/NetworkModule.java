package com.mhmt.products.network;

import android.app.Application;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class NetworkModule {

  private static final long CACHE_SIZE = 10;
  private static final long TIMEOUT = 120L;
  private static final long WRITE_TIMEOUT = TIMEOUT;
  private static final long READ_TIMEOUT = TIMEOUT;

  String mBaseUrl;

  public NetworkModule(String baseUrl) {
    this.mBaseUrl = baseUrl;
  }


  @Provides
  @Singleton
  Cache provideHttpCache(Application application) {
    final File cacheDir = application.getCacheDir();
    long cacheSize = CACHE_SIZE * 1024L * 1024L;
    return new Cache(cacheDir, cacheSize);
  }

  @Provides
  @Singleton
  ObjectMapper provideObjectMapper() {
    final ObjectMapper jsonObjectMapper = new ObjectMapper();
    jsonObjectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
    jsonObjectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    jsonObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    return jsonObjectMapper;
  }

  @Provides
  @Singleton
  OkHttpClient provideOkhttpClient(Cache cache) {
    final OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                                                                   .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                                                                   .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                                                                   .cache(cache);
    return builder.build();
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(ObjectMapper objectMapper, OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
               .addConverterFactory(JacksonConverterFactory.create(objectMapper))
               .baseUrl(mBaseUrl)
               .client(okHttpClient)
               .build();
  }
}
