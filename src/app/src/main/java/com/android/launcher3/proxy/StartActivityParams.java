package com.android.launcher3.proxy;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class StartActivityParams implements Parcelable {
    private static final String TAG = "StartActivityParams";
    public int extraFlags;
    public Intent fillInIntent;
    public int flagsMask;
    public int flagsValues;
    public Intent intent;
    public IntentSender intentSender;
    private PendingIntent mPICallback;
    public Bundle options;
    public final int requestCode;

    public static final Parcelable.Creator<StartActivityParams> CREATOR =
            new Parcelable.Creator<StartActivityParams>() {
                public StartActivityParams createFromParcel(Parcel source) {
                    return new StartActivityParams(source);
                }

                public StartActivityParams[] newArray(int size) {
                    return new StartActivityParams[size];
                }
            };

    public StartActivityParams(Intent arg1, int arg2) {
        this.intent = arg1;
        this.requestCode = arg2;
    }

    public StartActivityParams(Parcel arg2) {
        this.mPICallback = (PendingIntent)arg2.readTypedObject(PendingIntent.CREATOR);
        this.requestCode = arg2.readInt();
        this.intent = (Intent)arg2.readTypedObject(Intent.CREATOR);
        this.intentSender = (IntentSender)arg2.readTypedObject(IntentSender.CREATOR);
        this.fillInIntent = (Intent)arg2.readTypedObject(Intent.CREATOR);
        this.flagsMask = arg2.readInt();
        this.flagsValues = arg2.readInt();
        this.extraFlags = arg2.readInt();
        this.options = arg2.readBundle();
    }

    public void deliverResult(Context arg2, int arg3, Intent arg4) {
        try {
            PendingIntent v0 = this.mPICallback;
            if(v0 != null) {
                v0.send(arg2, arg3, arg4);
                return;
            }
        }
        catch(PendingIntent.CanceledException v2) {
            Log.e("StartActivityParams", "Unable to send back result", v2);
            return;
        }
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "params\'intent = " + this.intent;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel arg2, int arg3) {
        arg2.writeTypedObject(this.mPICallback, arg3);
        arg2.writeInt(this.requestCode);
        arg2.writeTypedObject(this.intent, arg3);
        arg2.writeTypedObject(this.intentSender, arg3);
        arg2.writeTypedObject(this.fillInIntent, arg3);
        arg2.writeInt(this.flagsMask);
        arg2.writeInt(this.flagsValues);
        arg2.writeInt(this.extraFlags);
        arg2.writeBundle(this.options);
    }
}


