package com.tech.pinduoduo_backdoor;

import android.os.Build;

public class RomOsUtil {

    public static boolean isOppoManufacture() {
        String manufacturer = Build.MANUFACTURER;
        return ("oppo".equalsIgnoreCase(manufacturer) || "realme".equalsIgnoreCase(manufacturer));
    }

    public static boolean isVivoManufacture() {
        String manufacturer = Build.MANUFACTURER;
        return "vivo".equalsIgnoreCase(manufacturer);
    }
}
