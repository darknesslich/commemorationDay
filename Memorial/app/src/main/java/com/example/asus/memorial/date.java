package com.example.asus.memorial;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by asus on 2018/2/1.
 */

public class date implements Serializable{
    int id;
    String name;
    int year;
    int month;
    int day;
    public int Days(){
        int days = 0;
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH)+1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        if (y-year==0){
            days = year_days(y,m,d)-year_days(year,month,day);
        }
        if (y-year==1){
            if (year%4==0&&year%100!=0||year%400==0){
                days = year_days(y,m,d)+366-year_days(year,month,day);
            }
            else{
                days = year_days(y,m,d)+365-year_days(year,month,day);
            }
        }
        if (y-year>1){
            if(year%4==0&&year%100!=0||year%400==0){
                days = year_days(y,m,d)+366-year_days(year,month,day);
            }
            else {
                days = year_days(y,m,d)+365-year_days(year,month,day);
            }
            int year1 = year;
            for (year1=year1+1;year1<y;year1++){
                if(year1%4==0&&year1%100!=0||year1%400==0){
                    days=days+366;
                }
                else{
                    days=days+365;
                }
            }
        }
        return days;
    }

    public int year_days(int y,int m,int d){
        int[][] x = {{31,28,31,30,31,30,31,31,30,31,30,31},{31,29,31,30,31,30,31,31,30,31,30,31}};
        int i,sum=0;
        if(y%4==0&&y%100!=0||y%400==0){
            for(i=0;i<m-1;i++){
                sum=sum+x[1][i];
            }
        }
        else{
            for(i=0;i<m-1;i++){
                sum=sum+x[0][i];
            }
        }
        return sum+d;
    }

    public int nextdays(){
        int days;
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH)+1;
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        if(y*10000+m*100+d<=y*10000+month*100+day){
            days = year_days(y,month,day)-year_days(y,m,d);
        }
        else {
            if (y%4==0&&y%100!=0||y%400==0){
                days = year_days(y+1,month,day)+366-year_days(y,m,d);
            }
            else{
                days = year_days(y+1,month,day)+365-year_days(y,m,d);
            }
        }
        return days;
    }
}
