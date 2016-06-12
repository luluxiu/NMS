package com.nms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by freedom on 2016/5/23.
 */
@Entity
@Data
@Table(name = "t_router")
@JsonIgnoreProperties(ignoreUnknown = true,
        value={"group", "wan", "lan", "wifi", "ota", "firewall", "qos", "status"})
public class DeviceRouter extends BaseModel {

    @Column(nullable = false, unique=true, length=18)
    private String mac;

    @Column(length=18)
    private String ip;

    private Boolean online;      //online or offline

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceRouterGroup group;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeviceRouterSettingsWAN wan;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeviceRouterSettingsLAN lan;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeviceRouterSettingsWiFi wifi;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeviceRouterSettingsOTA ota;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeviceRouterSettingsFirewall firewall;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeviceRouterSettingsQoS qos;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private DeviceRouterStatus status;
}
