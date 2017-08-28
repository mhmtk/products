package com.mhmt.products.network;

import com.mhmt.products.domain.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

  @GET(".") Call<List<Category>> fetchCategories();
}
