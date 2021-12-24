package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ArrayList<Data> dataArrayList;
    RecyclerViewAdapter adapter;
    String[] amount;
    String[] description;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        topAppBar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        fab=findViewById(R.id.floatingActionButton);
        pieChart = findViewById(R.id.piechart);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        dataArrayList = new ArrayList<Data>();

        adapter = new RecyclerViewAdapter(this,dataArrayList);
        recyclerView.setAdapter(adapter);

        amount = new String[]{
          "100","200","300","400","500","600","700","800","900","1000"
        };

        description = new String[]{
                "One hundred","Two hundred","Three hundred","Four hundred","Five hundred","Six hundred","Seven hundred","Eight hundred","Nine hundred","One thousand"
        };

        getData();

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id= item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch(id) {
                    case R.id.item_settings: {
                        //Write Code here to navigate to settings page
                        break;
                    }
                    case R.id.item_help_feedback: {
                        //Write code here for help and feedback
                        break;
                    }
                    case R.id.item_logout:{
                        //Write code here for logout
                        break;
                    }
                    default:
                        return true;
                }
                return true;
            }
        });

        fab.setOnClickListener((view -> {
            startActivity(new Intent(DashboardActivity.this,NextActivity.class));
        }));
        setupPieChart();
        loadPieChartData();
    }

    private void getData(){

        for(int i=0;i<amount.length;i++){
            Data data = new Data(amount[i],description[i]);
            dataArrayList.add(data);
        }

        adapter.notifyDataSetChanged();
    }
    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Expenses");
        pieChart.setCenterTextSize(20);
        pieChart.getDescription().setEnabled(false);


    }
    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.2f, R.drawable.ic_baseline_restaurant_24));

        entries.add(new PieEntry(0.10f));
        entries.add(new PieEntry(0.25f));


        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}