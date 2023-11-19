package com.rpl.mvvm.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rpl.mvvm.databinding.AdapterUniversityBinding;
import com.rpl.mvvm.model.University;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.UniversityViewHolder> {
    private List<University> universityList;
    public final UniversityListener listener;
    public MainAdapter(UniversityListener listener) {
        this.listener = listener;
    }
    public interface UniversityListener {
        void onUniversityClicked(Integer position);
        void onUniversityLongTap(Integer position);
    }

    public class UniversityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private AdapterUniversityBinding binding;
        private Integer position;
        public UniversityViewHolder(@NonNull AdapterUniversityBinding view) {
            super(view.getRoot());
            binding = view;
        }

        void bind(University university, Integer position) {
            this.position = position;
            binding.setP(university);
            itemView.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onUniversityClicked(position);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onUniversityLongTap(position);
            return true;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<University> list) {
        universityList = list;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public UniversityViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int viewType) {
        return new UniversityViewHolder(AdapterUniversityBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NotNull UniversityViewHolder viewHolder, final int position) {
        viewHolder.bind(universityList.get(position), position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return universityList.size();
    }
}