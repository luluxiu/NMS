package com.nms.model.support;

/**
 * Created by freedom on 2016/5/23.
 */
public enum WANProtocol {
    DHCP("dhcp"),
    STATIC("static"),
    PPPOE("pppoe"),
    G34("3g");


    private String protocol;

    WANProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getId(){
        return name();
    }

    @Override
    public String toString() {
        return protocol;
    }
}
