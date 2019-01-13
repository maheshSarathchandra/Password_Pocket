package com.example.maheshsarathchandra.password;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";
    private Button mSave;
    private Button mDelete;

    private EditText editdataText;

    DataBaseHelper mdatabaseHelper;

    private String selectedPsswaord;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        mSave = (Button) findViewById(R.id.save_button);
        mDelete = (Button) findViewById(R.id.delete_button);
        editdataText = (EditText) findViewById(R.id.editdata_text);
        mdatabaseHelper = new DataBaseHelper(this);

        Intent recievedIntent = getIntent();

        selectedID = recievedIntent.getIntExtra("id", -1);

        selectedPsswaord = recievedIntent.getStringExtra("password");

        editdataText.setText(selectedPsswaord);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editdataText.getText().toString();
                        if(! item.equals("")){
                    mdatabaseHelper.updatePassward(item,selectedID,selectedPsswaord);
                        }else{
                            Toast.makeText(EditDataActivity.this, R.string.password, Toast.LENGTH_SHORT).show();
                        }
            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabaseHelper.deletePassword(selectedID,selectedPsswaord);
                editdataText.setText("");
                Toast.makeText(EditDataActivity.this, R.string.delete, Toast.LENGTH_SHORT).show();
            }
        });
    }
}