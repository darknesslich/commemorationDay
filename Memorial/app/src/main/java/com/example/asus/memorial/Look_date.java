package com.example.asus.memorial;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.necer.ndialog.NDialog;

/**
 * Created by asus on 2018/2/2.
 */

public class Look_date extends AppCompatActivity implements View.OnClickListener{

    protected TextView date_name,date,days_after,next_days;
    protected Button del,out;
    protected date d;
    protected mysqlite mysql;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_date);
        d = (date) getIntent().getSerializableExtra("date_data");
        date_name = (TextView) findViewById(R.id.date_name);
        date_name.setText(d.name);
        date = (TextView) findViewById(R.id.DATE);
        date.setText(d.year+"年"+d.month+"月"+d.day+"日");
        days_after = (TextView) findViewById(R.id.days_after);
        days_after.setText("到今天已过"+d.Days()+"天");
        next_days = (TextView) findViewById(R.id.next_days);
        if (d.nextdays()==0){
            next_days.setText("今天就是纪念日哦");
        }
        else{
            next_days.setText("距下次纪念日"+d.nextdays()+"天");
        }

        del = (Button) findViewById(R.id.del);
        del.setOnClickListener(this);
        out = (Button) findViewById(R.id.out);
        out.setOnClickListener(this);
        mysql = new mysqlite(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.out:
                Intent intent = new Intent(Look_date.this,Day_List.class);
                startActivity(intent);
                finish();
                break;
            case R.id.del:
                if(d.id==1){
                    new NDialog(this).setTitle("无法删除")
                            .setTitleColor(Color.parseColor("#000000"))
                            .setTitleSize(22)
                            .setTitleCenter(false)
                            .setMessage("开始使用的记录不能删除哦")
                            .setMessageSize(15)
                            .setMessageColor(Color.parseColor("#000000"))
                            .setMessageCenter(true)
                            .setPositiveButtonText("确定")
                            .setPositiveTextColor(Color.parseColor("#000000"))
                            .setCancleable(true)
                            .setOnConfirmListener(new NDialog.OnConfirmListener() {
                                @Override
                                public void onClick(int which) {
                                    switch (which){
                                        case 1:
                                            break;
                                        case 0:
                                            break;
                                    }
                                }
                            }).create(NDialog.CONFIRM).show();
                }
                else {
                    new NDialog(this).setTitle("确认删除")
                            .setTitleColor(Color.parseColor("#000000"))
                            .setTitleSize(22)
                            .setTitleCenter(false)
                            .setMessage("是否要删除")
                            .setMessageSize(15)
                            .setMessageColor(Color.parseColor("#000000"))
                            .setMessageCenter(true)
                            .setNegativeButtonText("取消")
                            .setNegativeTextColor(Color.parseColor("#000000"))
                            .setPositiveButtonText("删除")
                            .setPositiveTextColor(Color.parseColor("#ff0000"))
                            .setCancleable(true)
                            .setOnConfirmListener(new NDialog.OnConfirmListener() {
                                @Override
                                public void onClick(int which) {
                                    switch (which){
                                        case 1:
                                            SQLiteDatabase db = mysql.getWritableDatabase();
                                            String sql = "DELETE FROM "+constant_db.TABLE_NAME+" WHERE id="+d.id;
                                            db.execSQL(sql);
                                            db.close();
                                            Toast.makeText(Look_date.this,"删除成功",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Look_date.this,Day_List.class);
                                            startActivity(intent);
                                            finish();
                                            break;
                                        case 0:
                                            break;
                                    }
                                }
                            }).create(NDialog.CONFIRM).show();
                }
                break;
        }
    }





}
