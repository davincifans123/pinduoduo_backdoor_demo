package com.tech.pinduoduo_backdoor;

//import com.google.gson.annotations.SerializedName;

public class IntentWrapper {
    //@SerializedName("flags")
    private int flags;
    //@SerializedName("isReadWriteOnly")
    private Boolean isReadWriteOnly;
    //@SerializedName("isUseIntentChooser")
    private Boolean isUseIntentChooser;
    //@SerializedName("isUseService")
    private Boolean isUseService;
    //@SerializedName("needAddSpecialFlags")
    private boolean needSpecialFlags;
    //@SerializedName("needTransit")
    private boolean needTransit;
    //@SerializedName("scene")
    private String scene;
    //@SerializedName("targetIntent")
    private String targetIntent;
    //@SerializedName("targetUri")
    private String targetUri;
    //@SerializedName("transitIntentKey")
    private String transitIntentKey;
    //@SerializedName("transitIntentUri")
    private String transitIntentUri;

    public IntentWrapper(String arg3, String arg4, String arg5, boolean arg6, boolean arg7, int arg8, String arg9, String arg10) {
        this.scene = "";
        this.targetIntent = "";
        this.targetUri = "";
        this.needSpecialFlags = false;
        this.needTransit = false;
        this.transitIntentUri = "";
        this.transitIntentKey = "";
        this.flags = 0;
        this.scene = arg3;
        this.targetIntent = arg4;
        this.targetUri = arg5;
        this.needSpecialFlags = arg6;
        this.needTransit = arg7;
        this.flags = arg8;
        this.transitIntentUri = arg9;
        this.transitIntentKey = arg10;
    }

    public int getFlags() {
        return this.flags;
    }

    public Boolean getIsReadWriteOnly() {
        return this.isReadWriteOnly;
    }

    public Boolean getIsUseIntentChooser() {
        return this.isUseIntentChooser;
    }

    public boolean getIsUseService() {
        return this.isUseService;
    }

    public String getScene() {
        return this.scene;
    }

    public String getTargetIntent() {
        return this.targetIntent;
    }

    public String getTargetUri() {
        return this.targetUri;
    }

    public String getTransitIntent() {
        return this.transitIntentUri;
    }

    public String getTransitIntentKey() {
        return this.transitIntentKey;
    }

    public boolean isNeedSpecialFlags() {
        return this.needSpecialFlags;
    }

    public boolean isNeedTransit() {
        return this.needTransit;
    }

    public void setReadWriteOnly(Boolean arg1) {
        this.isReadWriteOnly = arg1;
    }

    public void setUseService(boolean arg1) {
        this.isUseService = arg1;
    }

    @Override
    public String toString() {
        return "IntentWrapper{scene=\'" + this.scene + '\'' + ", targetIntent=\'" + this.targetIntent + '\'' + ", targetUri=\'" + this.targetUri + '\'' + ", needSpecialFlags=" + this.needSpecialFlags + ", needTransit=" + this.needTransit + ", transitIntentUri=\'" + this.transitIntentUri + '\'' + ", transitIntentKey=\'" + this.transitIntentKey + '\'' + ", flags=" + this.flags + ", isReadWriteOnly=" + this.isReadWriteOnly + ", isUseService=" + this.isUseService + '}';
    }
}



