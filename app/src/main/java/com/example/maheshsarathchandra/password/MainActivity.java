package com.example.maheshsarathchandra.password;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    DataBaseHelper mDatabaseHelper;
    private Button mAddButton;
    private Button mShowButton;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddButton = (Button) findViewById(R.id.add_button);
        mShowButton = (Button) findViewById(R.id.show_button);
        editText = (EditText) findViewById(R.id.edit_text);
        mDatabaseHelper = new DataBaseHelper(this);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(editText.length() != 0){
                    AddData(newEntry);
                    editText.setText("");
                }else {
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                }
                }
        });


        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListDataActivity.class);
                startActivity(intent);
            }
        });



    }
    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);
        if(insertData){
          Toast.makeText(MainActivity.this,R.string.add_new,Toast.LENGTH_SHORT);
        }else{
            Toast.makeText(MainActivity.this,R.string.add_old,Toast.LENGTH_SHORT);
        }
    }
}
