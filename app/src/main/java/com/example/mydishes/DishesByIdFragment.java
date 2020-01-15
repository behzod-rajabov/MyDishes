package com.example.mydishes;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class DishesByIdFragment extends Fragment {


    public DishesByIdFragment() {
        // Required empty public constructor
    }

    private boolean fav = false;
    private int id = 0;
    private Dishes dishes;
    private ImageView image, favAdd;
    private TextView name, description;
    private Button edit, del;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dishes_by_id, container, false);

        favAdd = view.findViewById(R.id.add_fav);
        image = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);
        description = view.findViewById(R.id.description);

        id = getArguments().getInt("id");
        dishes = HomeActivity.myAppDataBaseDishes.myDao().getDishesById(id);

        fav = dishes.isFav();
        if (fav)
            favAdd.setColorFilter(Color.RED);
        else
            favAdd.setColorFilter(Color.BLACK);

        if (!dishes.getDishesImage().equals(""))
            Picasso.with(getContext()).load(dishes.getDishesImage()).into(image);
        else
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        name.setText(dishes.getDishesName());
        description.setText(dishes.getDishesDescription());

        favAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fav) {
                    HomeActivity.myAppDataBaseDishes.myDao().favDelDishes(id);
                    favAdd.setColorFilter(Color.BLACK);
                    fav = false;
                } else {
                    HomeActivity.myAppDataBaseDishes.myDao().favAddDishes(id);
                    favAdd.setColorFilter(Color.RED);
                    fav = true;
                }
            }
        });

        edit = view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Ushbu funksiya hozircha ishlamaydi!", Toast.LENGTH_SHORT).show();
//                stc.setDishesId = id;
//                stc.setDishesName = dishes.getDishesName();
//                stc.setDishesDescription = dishes.getDishesDescription();
//                stc.setDishesImage = dishes.getDishesImage();
//                FragmentTransaction ft = HomeActivity.fm.beginTransaction();
//                //ft.replace(R.id.container , new UpdateCategoryFragment()).addToBackStack(null).commit();
            }
        });

        del = view.findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Ovqatni o`chrish");
                builder.setMessage("Siz rostdan ham ushbu ovqatni o`chirmoqchimisiz?");
                builder.setPositiveButton("O`chirish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dishes d = new Dishes();
                        d.setDishesId(id);
                        HomeActivity.myAppDataBaseDishes.myDao().deleteDishes(d);
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
