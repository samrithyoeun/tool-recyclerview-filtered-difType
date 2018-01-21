package com.fluffy.samrith.recyclerview_filter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<RowItem> RowItemList ;
    private RecyclerView recyclerView;
    private RowAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareRowItemData();
        recyclerView = (RecyclerView)findViewById(R.id.list);

        mAdapter = new RowAdapter(getApplicationContext(),RowItemList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep RowItem_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        // row click listener
        mAdapter.setOnClick(new RowListener() {
            @Override
            public void onRowClick(RowItem row) {
                Toast.makeText(getApplicationContext(), row.getName() +row.getType() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void prepareRowItemData() {
        RowItemList = new ArrayList<>();
        RowItemList.add( new RowItem(1,"View College","zoom","dat"));
        RowItemList.add( new RowItem(2,"Add College","createnew"));
        RowItemList.add( new RowItem(3,"Edit College","pencil"));
        RowItemList.add( new RowItem(4,"Delete College","trash"));

    }
}
