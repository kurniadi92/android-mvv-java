package com.rpl.mvvm.viewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.rpl.mvvm.model.University;
import com.rpl.mvvm.model.UniversityRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private UniversityRepository repository;
    private List<University> items;
    public MutableLiveData<University> openDetail = new MutableLiveData<>();
    public MutableLiveData<List<University>> universityListUpdate = new MutableLiveData<>();
    public MutableLiveData<String> showToast = new MutableLiveData<>();

    public MainViewModel(UniversityRepository repository) {
        this.repository = repository;
    }

    void onCreate() {
        items = repository.fetchUniversity();
        universityListUpdate.postValue(items);
    }

    public void onLongTap(Integer index) {
        String warning = String.format("%s removed", items.get(index).name);
        showToast.postValue(warning);
        items.remove(index);
        universityListUpdate.postValue(items);
    }

    public void onTap(Integer index) {
        openDetail.postValue(items.get(index));
    }
}