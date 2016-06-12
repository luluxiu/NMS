package com.nms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by freedom on 2016/5/23.
 */
@Entity
@Data
@Table(name = "t_wifi")
@JsonIgnoreProperties(ignoreUnknown = true, value={"router", "hibernateLazyInitializer", "handler"})
public class DeviceRouterSettingsWiFi extends BaseModel {

    /* basic */
    @Column(name = "wifiEnable")
    private Integer wifi;

    @Column(name = "wifiSSID", length = 64)
    private String ssid;

    @Column(name = "wifiChannel")
    private Integer channel;

    @Column(name = "wifiMaxStation")
    private Integer maxStation;

    /* security */
    @Column(name = "wifiEncryption", length = 16)
    private String encryption;

    @Column(name = "wifiCipher", length = 16)
    private String cipher;          //aes/tkip/aes+tkip

    @Column(name = "wifiKey", length = 64)
    private String key;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "wifi")
    private DeviceRouter router;
}
