package com.example.mydishes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydishes.Adapter.AdapterCategory;
import com.example.mydishes.Interface.ItemClickListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadCategoryFragment extends Fragment {


    public ReadCategoryFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private List<Category> categories;
    private AdapterCategory adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_read_category, container, false);

        categories = HomeActivity.myAppDataBase.myDao().getCategory();

        adapter = new AdapterCategory(getContext(), categories, new ItemClickListener() {
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

//                Intent intent = new Intent(getContext(), ReadCatDishesActivity.class);
//                intent.putExtra("catId", categories.get(positon).getId());
//                stc.setCatId = categories.get(positon).getId();
//                stc.setCatName = categories.get(positon).getName();
//                startActivity(intent);
            }
        });

        recyclerView = view.findViewById(R.id.list_categories);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, 1, false));
        recyclerView.setAdapter(adapter);

        return view;
    }

}
