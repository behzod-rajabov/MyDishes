package com.example.mydishes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydishes.Adapter.AdapterCategory;
import com.example.mydishes.Adapter.AdapterDishes;
import com.example.mydishes.Interface.ItemClickListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewCategory;
    private List<Category> categories;
    private AdapterCategory adapterCategory;

    private RecyclerView recyclerViewDishes;
    private List<Dishes> dishes;
    private AdapterDishes adapterDishes;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categories = HomeActivity.myAppDataBase.myDao().getCategory();
        adapterCategory = new AdapterCategory(getContext(), categories, new ItemClickListener() {
            @Override
            public void onClick(View view, int positon) {
                FragmentTransaction ft = HomeActivity.fm.beginTransaction();
                ReadCategoryDishesFragment fragment = new ReadCategoryDishesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("catId", categories.get(positon).getId());
                stc.setCatId = categories.get(positon).getId();
                stc.setCatName = categories.get(positon).getName();
                stc.setCatImage = categories.get(positon).getImage();
                fragment.setArguments(bundle);
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        recyclerViewCategory = view.findViewById(R.id.list_category);
        recyclerViewCategory.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(), 2, 1, false));
        recyclerViewCategory.setAdapter(adapterCategory);

        dishes = HomeActivity.myAppDataBaseDishes.myDao().getDishes();

        adapterDishes = new AdapterDishes(getContext(), dishes, new ItemClickListener() {
            @Override
            public void onClick(View view, int positon) {
                FragmentTransaction ft = HomeActivity.fm.beginTransaction();
                DishesByIdFragment fragment = new DishesByIdFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", dishes.get(positon).getDishesId());
                fragment.setArguments(bundle);
                ft.replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        recyclerViewDishes = view.findViewById(R.id.list_dishes);

        recyclerViewDishes.setHasFixedSize(true);

        recyclerViewDishes.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));

        recyclerViewDishes.setAdapter(adapterDishes);

        return view;
    }

}
