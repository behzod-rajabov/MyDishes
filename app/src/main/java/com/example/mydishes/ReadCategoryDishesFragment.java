package com.example.mydishes;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mydishes.Adapter.AdapterDishes;
import com.example.mydishes.Interface.ItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadCategoryDishesFragment extends Fragment {


    public ReadCategoryDishesFragment() {
        // Required empty public constructor
    }

    int catId = 0;

    private List<Dishes> dishesAll, dishes;
    private RecyclerView recyclerView;
    private AdapterDishes adapter;
    private Button edit, del;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragment_read_category_dishes, container, false);
        catId = getArguments().getInt("catId");

        dishesAll = HomeActivity.myAppDataBaseDishes.myDao().getDishes();
        dishes = new ArrayList<>();
        for (int i=0; i<dishesAll.size(); i++)
        {
            Dishes d = dishesAll.get(i);
            if (d.getDishesCategoryId() == catId)
                dishes.add(d);
        }

        adapter = new AdapterDishes(getContext(), dishes, new ItemClickListener() {
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
        recyclerView.setAdapter(adapter);


        edit = view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stc.setCatId = catId;
                FragmentTransaction ft = HomeActivity.fm.beginTransaction();
                ft.replace(R.id.container , new UpdateCategoryFragment()).addToBackStack(null).commit();
            }
        });

        del = view.findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Kategoriyani o`chrish");
                builder.setMessage("Siz rostdan ham ushbu kategoriyani o`chirmoqchimisiz? Kategoriya o`chirilsa undagi barcha ovqatlar ham o`chib ketadi!");
                builder.setPositiveButton("O`chirish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HomeActivity.myAppDataBase.myDao().deleteByIdCategory(catId);
                        for (int i=0; i<dishes.size(); i++)
                            HomeActivity.myAppDataBaseDishes.myDao().deleteDishes(dishes.get(i));
                        FragmentTransaction ft = HomeActivity.fm.beginTransaction();
                        ft.replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();
                    }
                });
                builder.setNegativeButton("Bekor qilish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });

        return view;
    }

}
