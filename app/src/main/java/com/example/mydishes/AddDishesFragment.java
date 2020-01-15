package com.example.mydishes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddDishesFragment extends Fragment {


    public AddDishesFragment() {
        // Required empty public constructor
    }


    private EditText add_dishes_name, add_dishes_descrition, add_dishes_image;
    private Spinner add_dishes_categoryId;
    private Button add_dishes_button;
    private ArrayAdapter<String> adapterCats;
    private List<String> cats;
    private List<Integer> catsId;
    private List<Category> categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_dishes, container, false);


        add_dishes_button = view.findViewById(R.id.add_dishes_button);

        add_dishes_name = view.findViewById(R.id.add_dishes_name);
        add_dishes_descrition = view.findViewById(R.id.add_dishes_description);
        add_dishes_image = view.findViewById(R.id.add_dishes_image2);
        add_dishes_categoryId = view.findViewById(R.id.add_dishes_categoryId);

        cats = new ArrayList<>();
        catsId = new ArrayList<>();
        categories = HomeActivity.myAppDataBase.myDao().getCategory();
        for (int i = 0; i < categories.size(); i++) {
            cats.add(categories.get(i).getName());
            catsId.add(categories.get(i).getId());
        }
        adapterCats = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cats);
        add_dishes_categoryId.setAdapter(adapterCats);
        add_dishes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!add_dishes_name.getText().equals("") && !add_dishes_descrition.getText().equals("")) {
                        Dishes d = new Dishes();
                        d.setDishesName(add_dishes_name.getText().toString());
                        d.setDishesDescription(add_dishes_descrition.getText().toString());
                        d.setDishesImage(add_dishes_image.getText().toString());
                        d.setDishesCategoryId(catsId.get(add_dishes_categoryId.getSelectedItemPosition()));
                        HomeActivity.myAppDataBaseDishes.myDao().addDishes(d);
                        FragmentTransaction ft = HomeActivity.fm.beginTransaction();
                        ft.replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();
                        Toast.makeText(getContext(), "Ovqat qo`shildi", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Xatolik: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
