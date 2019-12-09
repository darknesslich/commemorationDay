package com.example.asus.memorial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

/**
 * Created by asus on 2018/1/31.
 */

public class mysqlite extends SQLiteOpenHelper {

    public mysqlite(Context context) {
        super(context,constant_db.DATABASE_NAME,null,constant_db.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+constant_db.TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,year INTEGER,month INTEGER,day INTEGER)";
        //id号，纪念日名字，年，月，日
        db.execSQL(sql);
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH)+1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        String first = "INSERT INTO "+constant_db.TABLE_NAME+"(name,year,month,day)"+" VALUES('纪念日的使用',"+String.valueOf(y)+","+String.valueOf(m)+","+String.valueOf(d)+")";
        db.execSQL(first);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
