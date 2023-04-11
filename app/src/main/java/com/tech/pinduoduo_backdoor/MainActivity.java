package com.tech.pinduoduo_backdoor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.launcher3.proxy.StartActivityParams;
import com.tech.pinduoduo_backdoor.idCollectors.WeiboNameCollector;

public class MainActivity extends AppCompatActivity {
    public TextView infoTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTextView = findViewById(R.id.show_collect_info);

        GlobalInfo.context = getApplicationContext();
    }


    public void collectWeiboId(View view){
        Logger.debug("start collectWeiboId");
        WeiboNameCollector weiboNameCollector = new WeiboNameCollector();
        String id = weiboNameCollector.collectInfo();
        Logger.debug("weiboName id: " + id);
        infoTextView.setText(id);
    }
}