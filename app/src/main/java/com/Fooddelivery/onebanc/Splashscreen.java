package com.Fooddelivery.onebanc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Splashscreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Animation topAnim, bottomAnim, frombottomAnim, leftAnim;
    ImageView logo, appName, backgroundImage, logo1;
    ConstraintLayout splashData, splashdata2, main;

    Resources res;

    private int[] category_id = {1, 2, 3, 4, 5};
    private int[] cat_image = {R.drawable.northindian, R.drawable.chinese, R.drawable.mexican, R.drawable.southindian, R.drawable.italian};
    private String[] cat_name;

    List<CategoryModel> categoryModelList = new ArrayList<>();
    RecyclerView recyclerViewCircular, recyclerView;
    LinearLayoutManager layoutManagerStoreFilter;


    //top dishes
    private int[] image = {R.drawable.italianmargheritapizza, R.drawable.indianbuttermasala, R.drawable.southindianonionravadosa};
    private double[] rating = {3.5, 4, 4.3};
    private String[] top_dish_name;
    private int[] price = {120, 225, 175};
    private int[] id = {27, 1, 22};
    private String[] top_category_name;
    private List<DishModel> topDishModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //To Hide Status Bar getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);
        res = getResources();
        cat_name=res.getStringArray( R.array.category_names ) ;
        top_dish_name=res.getStringArray( R.array.top_dish_names ) ;
        top_category_name=res.getStringArray( R.array.top_category_names ) ;



        recyclerViewCircular = findViewById(R.id.category_recycler);
        recyclerViewCircular.setPadding(80, 0, 80, 0);
//        recyclerViewCircular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        prepareTheList();
//
//        catAdapter adapter= new catAdapter(categoryModelList,this);
//        recyclerViewCircular.setAdapter(adapter);


        layoutManagerStoreFilter = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCircular.setLayoutManager(layoutManagerStoreFilter);


        prepareTheList();
        categoryAdapter adapter = new categoryAdapter(categoryModelList, this);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewCircular);

        recyclerViewCircular.setAdapter(adapter);

        layoutManagerStoreFilter.scrollToPosition(((Integer.MAX_VALUE / 2) - ((Integer.MAX_VALUE / 2) % categoryModelList.size())));


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        frombottomAnim = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        leftAnim = AnimationUtils.loadAnimation(this, R.anim.left_right);

        main = findViewById(R.id.main);
        splashData = findViewById(R.id.splashData);
        splashdata2 = findViewById(R.id.splashData2);

        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.appname);
        logo1 = findViewById(R.id.imageview);
        backgroundImage = findViewById(R.id.background_image);

        logo.setAnimation(leftAnim);
        appName.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backgroundImage.animate().translationY(-1330).setDuration(800).setStartDelay(300);
                splashData.animate().translationY(-1700).setDuration(800).setStartDelay(300);
                splashdata2.setVisibility(View.VISIBLE);
                splashdata2.startAnimation(frombottomAnim);

            }
        }, 2000);


        //language spinner
        Spinner spinner = findViewById(R.id.lang_spinner);
        ArrayAdapter<CharSequence> adapter_lang = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_item);
        adapter_lang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_lang);
        spinner.setOnItemSelectedListener(this);

        //cart button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.toCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), cart.class);
                startActivity(i);
            }
        });


        main.setBackgroundColor(getResources().getColor(R.color.skyBlue));




    }

    private void prepareTheList() {
        int count = 0;
        for (String catName : cat_name) {
            CategoryModel categoryModel = new CategoryModel(cat_image[count], catName, category_id[count]);
            categoryModelList.add(categoryModel);
            count++;
        }
    }

    private void prepareTheTopDishList() {
        int count = 0;
        for (String dishName : top_dish_name) {
            DishModel dishModel = new DishModel(image[count], rating[count], price[count], dishName, id[count], top_category_name[count]);
            topDishModelList.add(dishModel);
            count++;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        if(text.equals("English")||text.equals("अंग्रेजी")){
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }else{
            Locale locale = new Locale("hi");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    public void openLinkedin(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/keshrishubham/"));
        startActivity(browserIntent);
    }

    public void openGithub(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/iamshubhamkeshri"));
        startActivity(browserIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Top Dishes

        topDishModelList.clear();
        recyclerView = findViewById(R.id.topDishes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        prepareTheTopDishList();
        topDishAdapter adapter2 = new topDishAdapter(topDishModelList);
        recyclerView.setAdapter(adapter2);

    }
}