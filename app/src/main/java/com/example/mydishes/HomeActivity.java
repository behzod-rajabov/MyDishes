package com.example.mydishes;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static FragmentManager fm;
    public static MyAppDataBase myAppDataBase;
    public static MyAppDataBaseDishes myAppDataBaseDishes;
    private SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myAppDataBase = Room.databaseBuilder(getApplicationContext(), MyAppDataBase.class, "category").allowMainThreadQueries().build();
        myAppDataBaseDishes = Room.databaseBuilder(getApplicationContext(), MyAppDataBaseDishes.class, "dishes").allowMainThreadQueries().build();

        prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);


        fm = getSupportFragmentManager();
        FragmentTransaction ft = HomeActivity.fm.beginTransaction();
        ft.add(R.id.container, new HomeFragment()).addToBackStack(null).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_fav) {
            FragmentTransaction ft = HomeActivity.fm.beginTransaction();
            ft.replace(R.id.container , new FavoriteDishes()).addToBackStack(null).commit();
            return true;
        } else if (id == R.id.action_about) {
            FragmentTransaction ft = HomeActivity.fm.beginTransaction();
            ft.replace(R.id.container , new AboutFragment()).addToBackStack(null).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home)
        {
            FragmentTransaction ft = HomeActivity.fm.beginTransaction();
            ft.replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();
        }
        else if (id == R.id.nav_categories) {

            FragmentTransaction ft = HomeActivity.fm.beginTransaction();
            ft.replace(R.id.container, new AddCategoryFragment()).addToBackStack(null).commit();

        }
        else if(id == R.id.nav_all_categories)
        {
            FragmentTransaction ft = HomeActivity.fm.beginTransaction();
            ft.replace(R.id.container , new ReadCategoryFragment()).addToBackStack(null).commit();

        }
        else if(id == R.id.nav_add_dishes)
        {
            FragmentTransaction ft = HomeActivity.fm.beginTransaction();
            ft.replace(R.id.container , new AddDishesFragment()).addToBackStack(null).commit();
        }

        else if(id == R.id.nav_readCategory_dishes)
        {
            FragmentTransaction ft = HomeActivity.fm.beginTransaction();
            ft.replace(R.id.container , new ReadAllDishesFragment()).addToBackStack(null).commit();
        }

        else if(id == R.id.nav_favs)
        {
            FragmentTransaction ft = HomeActivity.fm.beginTransaction();
            ft.replace(R.id.container , new FavoriteDishes()).addToBackStack(null).commit();

        }
        else if(id == R.id.nav_about)
        {
            FragmentTransaction ft = HomeActivity.fm.beginTransaction();
            ft.replace(R.id.container , new AboutFragment()).addToBackStack(null).commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {

            Category c = new Category();
            c.setName("Quyuq taomlar");
            c.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStS7lQb0wFOrXSOe8w491AKPMkaYEhTu5AATW4QXukTLTpzsq8");
            HomeActivity.myAppDataBase.myDao().addCategory(c);
            c.setName("Suyuq taomlar");
            c.setImage("https://cdn.iconscout.com/icon/premium/png-256-thumb/soup-bowl-dinner-food-light-liquid-plate-3-37531.png");
            HomeActivity.myAppDataBase.myDao().addCategory(c);
            c.setName("Fast food");
            c.setImage("https://ya-webdesign.com/images/hamburger-svg-unhealthy-food-4.png");
            HomeActivity.myAppDataBase.myDao().addCategory(c);
            c.setName("Salatlar");
            c.setImage("http://icons.iconarchive.com/icons/google/noto-emoji-food-drink/1024/32395-green-salad-icon.png");
            HomeActivity.myAppDataBase.myDao().addCategory(c);
            c.setName("Shirinliklar");
            c.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTw1PpgcJhnDeY0Ees9Dz7FLWSGKDftoEmnbse8mksdwMNDumuqag");
            HomeActivity.myAppDataBase.myDao().addCategory(c);

            Dishes d = new Dishes();
            d.setDishesName("Palov");
            d.setDishesDescription("Palov bu o`zbek milliy taomi. Palov bu o`zbek milliy taomi. Palov bu o`zbek milliy taomi. Palov bu o`zbek milliy taomi. Palov bu o`zbek milliy taomi. Palov bu o`zbek milliy taomi. ");
            d.setDishesImage("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Urazmat-Plov.jpg/220px-Urazmat-Plov.jpg");
            d.setDishesCategoryId(1);
            HomeActivity.myAppDataBaseDishes.myDao().addDishes(d);
            d.setDishesName("Somsa");
            d.setDishesDescription("Ko`k somsa, Qovoq somsa, Go`sht somsa, Ismaloq somsa, Tandir somsa, Ko`k somsa, Qovoq somsa, Go`sht somsa, Ismaloq somsa, Tandir somsa, Ko`k somsa, Qovoq somsa, Go`sht somsa, Ismaloq somsa, Tandir somsa.");
            d.setDishesImage("https://upload.wikimedia.org/wikipedia/uz/f/f1/SOMSA.jpg");
            d.setDishesCategoryId(1);
            HomeActivity.myAppDataBaseDishes.myDao().addDishes(d);
            d.setDishesName("Sho`rva");
            d.setDishesDescription("Sho`rva barak, Karam sho`rva, Osma sho`rva, Kadi sho`rva, Sho`rva barak, Karam sho`rva, Osma sho`rva, Kadi sho`rva, Sho`rva barak, Karam sho`rva, Osma sho`rva, Kadi sho`rva.");
            d.setDishesImage("http://www.tomchi.ru/wp-content/uploads/2017/01/Bulgor-qalampir-500x330.jpg");
            d.setDishesCategoryId(2);
            HomeActivity.myAppDataBaseDishes.myDao().addDishes(d);

            prefs.edit().putBoolean("firstrun", false).commit();
            FragmentTransaction ft = HomeActivity.fm.beginTransaction();
            ft.replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();
        }
    }
}
