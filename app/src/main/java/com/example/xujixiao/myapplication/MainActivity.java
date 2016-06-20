package com.example.xujixiao.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("xujxiao", "fjsdo");

    }

    public void test() {
        int i = 90;
    }
}
