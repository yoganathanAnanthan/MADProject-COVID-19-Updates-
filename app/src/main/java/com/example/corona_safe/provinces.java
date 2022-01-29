package com.example.corona_safe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class provinces extends AppCompatActivity {
    private static final String TAG = "provinces";
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinces);
        Log.d(TAG, "onCreate: started");
        initImageBitmaps();
    }
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: started");
        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Sri_Lanka_Central_Province_locator_map.svg/800px-Sri_Lanka_Central_Province_locator_map.svg.png");
        mNames.add("Central");
        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Sri_Lanka_Eastern_Province_locator_map.svg/800px-Sri_Lanka_Eastern_Province_locator_map.svg.png");
        mNames.add("Eastern");
        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Sri_Lanka_North_Central_Province_locator_map.svg/800px-Sri_Lanka_North_Central_Province_locator_map.svg.png");
        mNames.add("North Central");
        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Sri_Lanka_Northern_Province_locator_map.svg/800px-Sri_Lanka_Northern_Province_locator_map.svg.png");
        mNames.add("Northern");
        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Sri_Lanka_North_Western_Province_locator_map.svg/800px-Sri_Lanka_North_Western_Province_locator_map.svg.png");
        mNames.add("North Western");
        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Sri_Lanka_Sabaragamuwa_locator_map.svg/800px-Sri_Lanka_Sabaragamuwa_locator_map.svg.png");
        mNames.add("Sabaragamuwa");
        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/1/19/Sri_Lanka_Southern_Province_locator_map.svg/800px-Sri_Lanka_Southern_Province_locator_map.svg.png");
        mNames.add("Southern");
        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Sri_Lanka_Uva_locator_map.svg/800px-Sri_Lanka_Uva_locator_map.svg.png");
        mNames.add("Uva");
        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Sri_Lanka_Western_Province_locator_map.svg/800px-Sri_Lanka_Western_Province_locator_map.svg.png");
        mNames.add("Western");
        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecycleViewAdapter adapter = new RecycleViewAdapter(mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}