package com.kingja.cardpackage.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kingja.cardpackage.util.ActivityUtil;
import com.kingja.cardpackage.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myHouse(View view) {
        ActivityUtil.goActivity(this,HouseActivity.class);
    }

    public void rentHouse(View view) {
        ActivityUtil.goActivity(this,RentActivity.class);
    }
}
