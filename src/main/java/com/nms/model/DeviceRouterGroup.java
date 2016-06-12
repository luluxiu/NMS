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
@Table(name = "t_group")
@JsonIgnoreProperties(ignoreUnknown = true, value={"nodes", "routers", "wan",
        "lan", "wifi", "ota", "firewall", "qos"})
public class DeviceRouterGroup extends BaseModel {

    @Column(nullable = false, unique=true, length = 32)
    private String name;

    @Column(nullable = false, unique=true, length = 128)
    private String groupId;

    @Column(length = 256)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.REMOVE)
    private Collection<DeviceRouterGroupNode> nodes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.REMOVE)
    private Collection<DeviceRouter> routers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceRouterTemplateWAN wan;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceRouterTemplateLAN lan;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceRouterTemplateWiFi wifi;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceRouterTemplateOTA ota;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceRouterTemplateFirewall firewall;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceRouterTemplateQoS qos;


    @Transient
    private String groupWanName;

    @Transient
    private String groupLanName;

    @Transient
    private String groupWifiName;

    @Transient
    private String groupOTAName;

    @Transient
    private String groupFirewallName;

    @Transient
    private String groupQosName;

}
