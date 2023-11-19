package com.rpl.mvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.rpl.mvvm.R;
import com.rpl.mvvm.databinding.ActivityMainBinding;
import com.rpl.mvvm.model.University;
import com.rpl.mvvm.model.UniversityRepository;
import com.rpl.mvvm.viewModel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.UniversityListener {
    private ActivityMainBinding binding;
    MainViewModel viewModel;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        adapter = new MainAdapter(this);
        MainViewModelFactory factory = new MainViewModelFactory(new UniversityRepository(this));
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        // Create the observer which updates the UI.
        final Observer<University> openDetailObserver = new Observer<University>() {
            @Override
            public void onChanged(University university) {
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                intent.putExtra("university_name", university.name);

                startActivity(intent);
            }
        };

        viewModel.openDetail.observe(this, openDetailObserver);

        // Create the observer which updates the UI.
        final Observer<List<University>> updateList = new Observer<List<University>>() {
            @Override
            public void onChanged(List<University> universities) {
                adapter.setList(universities);
            }
        };

        viewModel.universityListUpdate.observe(this, updateList);

        final Observer<String> showToast = new Observer<String>() {
            @Override
            public void onChanged(String text) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        };

        viewModel.showToast.observe(this, showToast);
    }

    @Override
    public void onUniversityClicked(Integer position) {
        viewModel.onTap(position);
    }

    @Override
    public void onUniversityLongTap(Integer position) {
        viewModel.onLongTap(position);
    }
}

