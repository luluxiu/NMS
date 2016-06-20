package com.nms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by freedom on 2016/5/23.
 */
@Entity
@Data
@Table(name = "t_temp_wifi")
@JsonIgnoreProperties(ignoreUnknown = true, value={"group", "hibernateLazyInitializer", "handler"})
public class DeviceRouterTemplateWiFi extends BaseModel {

    /* basic */
    @Column(name = "wifiEnable")
    private int wifi;

    @Column(name = "wifiSSID", length = 64)
    private String ssid;

    @Column(name = "wifiChannel")
    private int channel;

    @Column(name = "wifiMaxStation")
    private int maxStation;

    /* security */
    @Column(name = "wifiEncryption", length = 16)
    private String encryption;

    @Column(name = "wifiCipher", length = 16)
    private String cipher;          //aes/tkip/aes+tkip

    @Column(name = "wifiKey", length = 64)
    private String key;


    /* template */

    @Column(length = 32)
    private String templateName;

    @Column(length = 128)
    private String templateDescription;

    /* template */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wifi")
    private Collection<DeviceRouterGroup> group = new ArrayList<>();
}
