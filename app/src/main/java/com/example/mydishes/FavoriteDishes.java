package com.example.mydishes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydishes.Adapter.AdapterDishes;
import com.example.mydishes.Interface.ItemClickListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteDishes extends Fragment {


    private RecyclerView recyclerView;
    private List<Dishes> dishes;
    private AdapterDishes adapterDishes;


    public FavoriteDishes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favorite_dishes, container, false);

        dishes = HomeActivity.myAppDataBaseDishes.myDao().getFavDishes();

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

        recyclerView = view.findViewById(R.id.list_dishes);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),1,false));

        recyclerView.setAdapter(adapterDishes);




        return view;
    }

}
