package com.example.youzicarendar.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBProblem extends SQLiteOpenHelper  {

    private static final String DB_NAME = "carendar.db";
    private static final String TBL_NAME = "problem";
    private static final String CREATE_TBL = "create table problem " +
            "(_id integer primary key autoincrement,name text,content text)";

    private SQLiteDatabase db;

    public DBProblem(Context context){
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TBL);
    }

    public void open(){
        db = getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(ContentValues values){
        db.insert(TBL_NAME,null,values);
    }

    public Cursor query(){
        Cursor c= db.query(TBL_NAME,null,null,null,null,null,null);
        return c;
    }

    public void del(int id){
        db.delete(TBL_NAME,"_id=?", new String[]{String.valueOf(id)});
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }
}
