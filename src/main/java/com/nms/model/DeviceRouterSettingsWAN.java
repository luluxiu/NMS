package com.nms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by freedom on 2016/5/23.
 */
@Entity
@Data
@Table(name = "t_wan")
@JsonIgnoreProperties(ignoreUnknown = true, value={"router", "hibernateLazyInitializer", "handler"})
public class DeviceRouterSettingsWAN extends BaseModel {


    @Column(nullable = false)
    private String protocol;

    /* static */
    @Column(length = 16)
    private String address;

    @Column(length = 16)
    private String netmask;

    @Column(length = 16)
    private String gateway;

    /* pppoe */
    @Column(length = 64)
    private String username;

    @Column(length = 64)
    private String password;

    /* 3/4G */
    @Column(length = 32)
    private String modemDevice;

    @Column(length = 32)
    private String serviceType;

    @Column(length = 32)
    private String APN;

    @Column(length = 32)
    private String dialNumber;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "wan")
    private DeviceRouter router;
}
