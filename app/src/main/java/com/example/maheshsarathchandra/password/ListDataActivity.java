package com.example.maheshsarathchandra.password;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    public static final String TAG = "ListDataActivity";
    DataBaseHelper mDatabaseHelper;
    private ListView mlistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mlistView = (ListView) findViewById(R.id.list_view);
        mDatabaseHelper = new DataBaseHelper(this);

        populateListView();

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id =1;
                String password = parent.getItemAtPosition(position).toString();
                Log.d(TAG,"onItemClick: You click on"+ password);

                Cursor data = mDatabaseHelper.getItemID(password);
                int itemID = -1;
                while (data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG,"onItemClick: the ID is "+ itemID);
                    Intent editScreenIntent =  new Intent(ListDataActivity.this,EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("password",password);
                    startActivity(editScreenIntent);
                }else{
                    Toast.makeText(ListDataActivity.this,R.string.not_name,Toast.LENGTH_SHORT);
                }
            }
        });

    }
    private void populateListView(){
        Log.d(TAG,"populateListView: Display Data in the ListView");

        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mlistView.setAdapter(adapter);



    }

}
