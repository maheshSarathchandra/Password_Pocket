package com.example.maheshsarathchandra.password;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseHelper";
    private static final String TABLE_NAME = "customer_Table";
    private static final String col ="id";
    private static final String col1 = "password";
    public DataBaseHelper(Context context){
        super(context,TABLE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+col1+"TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE EXISTS"+ TABLE_NAME);
        onCreate(db);

    }
    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col1,item);
        Log.d(TAG,"addData: Adding"+item+"to"+TABLE_NAME);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(){
       SQLiteDatabase db = this.getWritableDatabase();
       String query = "SELECT * FROM "+ TABLE_NAME;
       Cursor data = db.rawQuery(query,null);
       return  data;
    }

    public Cursor getItemID(String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+col+" FROM "+TABLE_NAME+
        " WHERE "+col1+"='"+password+"'" ;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public  void  updatePassward(String newPassword,int id, String oldPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+col1+
                "= '"+newPassword+"'WHERE"+col+"= '"+id+"'"+
                "AND"+col1+"= '"+oldPassword+"'";

        Log.d(TAG,"updatePassword: query: "+ query);
        Log.d(TAG,"updatePassword: Setting password to"+ newPassword);

        db.execSQL(query);

    }
    public void deletePassword(int id,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+TABLE_NAME+" WHERE "
                +col+" = '"+id+"'"+"AND"+col1+" = '"+password+"'";
        Log.d(TAG,"updatePassword: query: "+ query);
        Log.d(TAG,"updatePassword: Setting password to"+ "from database");
        db.execSQL(query);
    }
}
