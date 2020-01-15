package com.example.mydishes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateCategoryFragment extends Fragment {
    private EditText update_image , update_name,update_id;
    public UpdateCategoryFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_update_category, container, false);
        Button update_button = view.findViewById(R.id.update_category_button);

        update_name = view.findViewById(R.id.update_category_name);
        update_name.setText(stc.setCatName);
        update_image = view.findViewById(R.id.update_category_image);
        update_image.setText(stc.setCatImage);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!update_name.getText().toString().equals(""))
                {
                    Category c = new Category();
                    c.setId(stc.setCatId);
                    c.setName(update_name.getText().toString());
                    c.setImage(update_image.getText().toString());

                    HomeActivity.myAppDataBase.myDao().updateCategory(c);
                    Toast.makeText(getContext(), "Kategoriya tahrirlandi!", Toast.LENGTH_LONG).show();

                    FragmentTransaction ft = HomeActivity.fm.beginTransaction();
                    ReadCategoryDishesFragment fragment = new ReadCategoryDishesFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("catId", stc.setCatId);
                    fragment.setArguments(bundle);
                    ft.replace(R.id.container, fragment).addToBackStack(null).commit();
                }
                else
                    Toast.makeText(getContext(), "Barchasini to'ldiring", Toast.LENGTH_LONG).show();

            }
        });



        return view;
    }

}
