//package com.fitnessapp.viewModel;
//import android.app.Application;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import com.fitnessapp.Food; // Assuming Food is in the com.fitnessapp package
//import com.fitnessapp.FoodRepository; // Assuming FoodRepository is in the com.fitnessapp package
//import java.util.List;
//public class DietViewModel extends AndroidViewModel {
//    private FoodRepository repository;
//    private MutableLiveData<List<Food>> foodsLiveData;
//    public DietViewModel(Application application) {
//        super(application);
//        repository = new FoodRepository(application);
//        foodsLiveData = new MutableLiveData<>();
//    }
//    public LiveData<List<Food>> getFoodsLiveData() {
//        return foodsLiveData;
//    }
//    public void searchFoods(String query) {
//        repository.searchFoods(query, foodsLiveData);
//    }
//}