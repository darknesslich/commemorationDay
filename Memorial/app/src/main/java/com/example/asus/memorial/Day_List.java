package com.example.asus.memorial;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.Vector;

/**
 * Created by asus on 2018/1/31.
 */

public class Day_List extends AppCompatActivity implements View.OnClickListener{

    protected Button add_d,use_app;
    protected ScrollView d_l;
    protected mysqlite mysql;
    protected LinearLayout day_linear;
    protected date use_d = new date();
    protected int i = 0;
    protected Vector<date> vd = new Vector<date>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_list);
        mysql = new mysqlite(this);
        d_l = (ScrollView) findViewById(R.id.show_day);
        add_d = (Button) findViewById(R.id.add_date);
        add_d.setOnClickListener(this);
        use_app = (Button) findViewById(R.id.use_app);
        use_app.setOnClickListener(this);
        day_linear = (LinearLayout) findViewById(R.id.day_linear);
        SQLiteDatabase db = mysql.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+constant_db.TABLE_NAME,null);
        cursor.moveToNext();
        use_d.id = Integer.valueOf(cursor.getString(0));
        use_d.name = cursor.getString(1);
        use_d.year = Integer.valueOf(cursor.getString(2));
        use_d.month = Integer.valueOf(cursor.getString(3));
        use_d.day = Integer.valueOf(cursor.getString(4));
        use_app.setText(use_d.name+"            "+use_d.Days()+"天");

        while (cursor.moveToNext()){
            i = i + 1;
            final date d = new date();
            d.id = Integer.valueOf(cursor.getString(0));
            d.name = cursor.getString(1);
            d.year = Integer.valueOf(cursor.getString(2));
            d.month = Integer.valueOf(cursor.getString(3));
            d.day = Integer.valueOf(cursor.getString(4));

            Button day_l = new Button(this);
            day_l.setBackgroundColor(Color.parseColor("#006666"));
            day_l.setTextColor(Color.parseColor("#FFFFFF"));
            day_l.setText(d.name+"            "+d.Days()+"天");
            day_l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Day_List.this,Look_date.class);
                    intent.putExtra("date_data",d);
                    startActivity(intent);
                    finish();
                }
            });
            day_linear.addView(day_l);
        }
        db.close();
        cursor.close();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_date:
                Intent intent = new Intent(Day_List.this,Add_date.class);
                startActivity(intent);
                finish();
                break;
            case R.id.use_app:
                Intent intent_use_app = new Intent(Day_List.this,Look_date.class);
                intent_use_app.putExtra("date_data",use_d);
                startActivity(intent_use_app);
                finish();
                break;
        }
    }
}
















