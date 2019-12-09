package com.example.asus.memorial;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by asus on 2018/2/2.
 */

public class Add_date extends AppCompatActivity implements View.OnClickListener{

    protected String year,month,day;
    protected String date_name;
    protected EditText year_edit,month_edit,day_edit,date_name_edit;
    protected Button save,out;
    protected mysqlite mysql;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_date);
        year_edit = (EditText) findViewById(R.id.year_edit);
        month_edit = (EditText) findViewById(R.id.month_edit);
        day_edit = (EditText) findViewById(R.id.day_edit);
        date_name_edit = (EditText) findViewById(R.id.date_name_edit);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
        out = (Button) findViewById(R.id.out);
        out.setOnClickListener(this);

        mysql = new mysqlite(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                if(year_edit.getText().toString().equals("")||month_edit.getText().toString().equals("")||day_edit.getText().toString().equals("")||date_name_edit.getText().toString().equals("")){
                    Toast.makeText(this,"不能有空内容",Toast.LENGTH_SHORT).show();
                }
                else {
                    year = year_edit.getText().toString();
                    month = month_edit.getText().toString();
                    day = day_edit.getText().toString();
                    date_name = date_name_edit.getText().toString();
                    SQLiteDatabase db = mysql.getWritableDatabase();
                    String sql = "INSERT INTO "+constant_db.TABLE_NAME+"(name,year,month,day)"+" VALUES('"+date_name+"',"+year+","+month+","+day+")";
                    db.execSQL(sql);
                    db.close();
                    Toast.makeText(this,"添加完成",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Add_date.this,Day_List.class);
                    startActivity(intent);
                    finish();
                    break;
                }
                break;
            case R.id.out:
                Intent intent = new Intent(Add_date.this,Day_List.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
