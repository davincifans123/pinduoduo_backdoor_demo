package com.tech.pinduoduo_backdoor.idCollectors;

class BrandInfo {
    private final String targetUri;
    private final String scene;

    public String getScene() {
        return this.scene;
    }

    public String getTargetUri() {
        return this.targetUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BrandInfo(String str, String str2) {
        this.scene = str;
        this.targetUri = str2;
    }
}
