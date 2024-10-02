//package com.fitnessapp;
//
//import com.fitnessapp.DietApiService;
//import com.fitnessapp.DietItem;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import io.reactivex.rxjava3.core.Single;
//
//public class DietRepository {
//    private final DietApiService apiService;
//
//    public DietRepository(DietApiService apiService) {
//        this.apiService = apiService;
//    }
//
//    public Single<List<DietItem>> getAllDietItems() {
//        return Single.zip(
//                apiService.getDietItems("breakfast"),
//                apiService.getDietItems("lunch"),
//                apiService.getDietItems("snack"),
//                apiService.getDietItems("dinner"),
//                (breakfast, lunch, snacks, dinner) -> {
//                    List<DietItem> allItems = new ArrayList<>();
//                    allItems.addAll(breakfast);
//                    allItems.addAll(lunch);
//                    allItems.addAll(snacks);
//                    allItems.addAll(dinner);
//                    return allItems;
//                }
//        );
//    }
//}