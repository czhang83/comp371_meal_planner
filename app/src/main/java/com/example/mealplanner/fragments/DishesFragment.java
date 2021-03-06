package com.example.mealplanner.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.AddDishActivity;
import com.example.mealplanner.MainActivity;
import com.example.mealplanner.R;
import com.example.mealplanner.database.AppViewModel;
import com.example.mealplanner.recyclerview.DishAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DishesFragment extends Fragment {

    private AppViewModel appViewModel;
    public FloatingActionButton floatingActionButtonAddDish;
    public RecyclerView recyclerView_dish;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dishes, container, false);
        floatingActionButtonAddDish = root.findViewById(R.id.floatingActionButton_add_dish);
        recyclerView_dish = root.findViewById(R.id.recyclerView_dish);

        // create recycler adapter
        DishAdapter adapter = new DishAdapter(appViewModel.getAllDishes().getValue());
        recyclerView_dish.setAdapter(adapter);
        recyclerView_dish.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView_dish.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView_dish.addItemDecoration(itemDecoration);

        appViewModel.getAllDishes().observe(getViewLifecycleOwner(), dishes -> {
            adapter.updateDishes(dishes);
        });

        floatingActionButtonAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root.getContext(), AddDishActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

}