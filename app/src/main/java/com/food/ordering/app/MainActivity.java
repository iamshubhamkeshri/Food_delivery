package com.food.ordering.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.food.ordering.app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int selected_category_id;

    private int[] image = {R.drawable.indianbuttermasala, R.drawable.indiandumbiryani, R.drawable.indiankadhipakoda, R.drawable.indiankeema, R.drawable.indianrajma, R.drawable.indiansamosa,
            R.drawable.chinesechillichicken, R.drawable.chinesechowmein, R.drawable.chinesedimsun, R.drawable.chinesehakkanoodles, R.drawable.chinesemanchurian, R.drawable.chinesespringroll,
            R.drawable.mexicanburittos, R.drawable.mexicanchickencurry, R.drawable.mexicanchilliconcarne, R.drawable.mexicantacos, R.drawable.mexicantomatosalsa,
            R.drawable.southindianchickenishtu, R.drawable.southindianhydrabadibiryani, R.drawable.southindianidli, R.drawable.southindianmalabarparottha,
            R.drawable.southindianonionravadosa, R.drawable.southindianravaupma, R.drawable.southindianuttpam, R.drawable.southindianvada,
            R.drawable.italianlasagne, R.drawable.italianmargheritapizza, R.drawable.italianmashroomrisotto, R.drawable.italianravioli, R.drawable.italiantiramisu, R.drawable.italianwhitesaucepasta};
    private double[] rating = {3.5, 4, 4.3, 4.1, 5, 4.3, 4.1, 3.9, 4.7, 4.9, 4.2, 4.1, 3.2, 3.7, 4.1, 2.8, 4.3, 4.7, 3.7, 4.0, 5.0, 4.7, 5.0, 4.3, 4.9, 5.0, 3.4, 4.2, 4.8, 3.8, 4.9,};
    private String[] dish_name;
    private int[] price = {225, 120, 110, 200, 150, 50, 180, 80, 50, 100, 80, 60, 100, 220, 80, 110, 90, 100, 220, 50, 80, 175, 75, 110, 80, 125, 225, 150, 120, 100, 70};
    private int[] category_id = {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5};
    private int[] id = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private String[] cat_name;


    private List<DishModel> dishModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();

        dish_name = res.getStringArray(R.array.dish_names);
        cat_name = res.getStringArray(R.array.category_names);


        Bundle extras = getIntent().getExtras();
        String value;
        textView = findViewById(R.id.category_name);
        if (extras != null) {
            value = extras.getString("name");
            selected_category_id = extras.getInt("id");
            textView.setText(value);
            //The key argument here must match that used in the other activity
        }


        ImageView backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        recyclerView = findViewById(R.id.dish_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        prepareTheList();
        dishAdapter adapter = new dishAdapter(dishModelList);
        recyclerView.setAdapter(adapter);


        //cart button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.toCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), cart.class);
                startActivity(i);
            }
        });
    }

    private void prepareTheList() {
        dishModelList.clear();
        int count = 0;
        for (int p_id : id) {
            if (category_id[count] == selected_category_id) {
                DishModel dishModel = new DishModel(image[count], rating[count], price[count], dish_name[count], id[count], cat_name[selected_category_id - 1]);
                dishModelList.add(dishModel);
            }
            count++;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}