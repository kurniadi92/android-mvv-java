package com.rpl.mvvm.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.rpl.mvvm.model.UniversityRepository;
import com.rpl.mvvm.viewModel.MainViewModel;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private UniversityRepository repository;

    MainViewModelFactory(UniversityRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
