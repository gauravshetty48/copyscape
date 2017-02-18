package com.zoidify.copyscape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CopyAdapter adapter;
    ArrayList<CopyData> myDataset;
    CopyDBHelper copyDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        setContentViews();
        Log.d("MAIN", "Content Views Set");
        Intent intent = new Intent(this, ClipboardCopyService.class);
        startService(intent);
    }


    void setContentViews() {

        copyDBHelper = new CopyDBHelper(this);
        myDataset = copyDBHelper.getHistory();
        recyclerView = (RecyclerView) findViewById(R.id.listview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CopyAdapter(this, myDataset);
        recyclerView.setAdapter(adapter);
        Log.d("MAIN", "Adapter Set");
        addDummyContent();

    }

    public void addDummyContent() {
        CopyData copyData = new CopyData();
        copyData.setCategory("Contact");
        copyData.setPinned(false);
        copyData.setCopiedText("This is some copied text");
        myDataset.add(copyData);
        CopyData copyData1 = new CopyData();
        copyData1.setCategory("Generic");
        copyData1.setPinned(true);
        copyData1.setCopiedText("This is some copied text 2");
        myDataset.add(copyData1);
        Log.d("MAIN", "Dummy Set");
        adapter.notifyDataSetChanged();
    }
}
