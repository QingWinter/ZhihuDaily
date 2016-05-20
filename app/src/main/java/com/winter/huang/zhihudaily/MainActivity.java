package com.winter.huang.zhihudaily;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.winter.huang.zhihudaily.common.ZhiHuApplication;

import io.realm.Realm;
import rx.Observable;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void start(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ZhiHuApplication.getApplication().startActivity(intent);
    }
}
