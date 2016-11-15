package com.example.shalom.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    final String ACTIVITY_LIFE_TAG = "activity lifecycle";
    @Override
    protected void onStart() {
        super.onStart();
        android.util.Log.d(ACTIVITY_LIFE_TAG , "onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        android.util.Log.d(ACTIVITY_LIFE_TAG , "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.d(ACTIVITY_LIFE_TAG , "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.d(ACTIVITY_LIFE_TAG , "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.d(ACTIVITY_LIFE_TAG , "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.d(ACTIVITY_LIFE_TAG , "onDestory()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
