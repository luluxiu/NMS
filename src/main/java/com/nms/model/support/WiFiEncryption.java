package com.nms.model.support;

/**
 * Created by freedom on 2016/5/23.
 */
public enum WiFiEncryption {
    NONE("none"),
    OPEN("open"),
    PSK("psk"),
    PSK2("psk2"),
    PSKMIXED("psk-mixed");

    private String encryption;

    WiFiEncryption(String protocol) {
        this.encryption = protocol;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String protocol) {
        this.encryption = protocol;
    }

    public String getId(){
        return name();
    }

    @Override
    public String toString() {
        return encryption;
    }
}
