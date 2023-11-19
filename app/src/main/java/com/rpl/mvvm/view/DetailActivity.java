package com.rpl.mvvm.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.rpl.mvvm.R;
import com.rpl.mvvm.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity implements Toolbar.OnClickListener {
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        binding = ActivityDetailBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.nameTextView.setText(getIntent().getStringExtra("university_name"));
        initView();
    }

    void initView() {
        binding.toolbar.getRoot().setTitle("Detail University");
        binding.toolbar.getRoot().setTitleTextColor(Color.WHITE);
        binding.toolbar.getRoot().setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}