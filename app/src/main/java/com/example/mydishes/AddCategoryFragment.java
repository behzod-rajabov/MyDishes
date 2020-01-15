package com.example.mydishes;
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
public class AddCategoryFragment extends Fragment {

    private EditText add_category_name, add_category_image;
    private Button add_category_button;

    public AddCategoryFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        add_category_button = view.findViewById(R.id.add_category_button);
        add_category_name = view.findViewById(R.id.add_category_name);
        add_category_image = view.findViewById(R.id.add_category_image);

        add_category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!add_category_name.getText().toString().equals("")) {
                    Category c = new Category();
                    c.setName(add_category_name.getText().toString());
                    c.setImage(add_category_image.getText().toString());

                    HomeActivity.myAppDataBase.myDao().addCategory(c);
                    Toast.makeText(getContext(), "Kategorya muvaffaqiyatli qo'shildi", Toast.LENGTH_LONG).show();

                    add_category_name.setText("");
                    add_category_image.setText("");
                    FragmentTransaction ft = HomeActivity.fm.beginTransaction();
                    ft.replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();
                } else {
                    Toast.makeText(getContext(), "Barchasini to'ldiring", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}