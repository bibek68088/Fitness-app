package com.fitnessapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DietApiService {
    @GET("http://10.0.2.2/fitness/admin/get_diet_items.php")
    Call<List<DietItem>> getDietItems();
}