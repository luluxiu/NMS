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
@Table(name = "t_temp_wan")
@JsonIgnoreProperties(ignoreUnknown = true, value={"group", "hibernateLazyInitializer", "handler"})
public class DeviceRouterTemplateWAN extends BaseModel {

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

    /* template */

    @Column(length = 32)
    private String templateName;

    @Column(length = 128)
    private String templateDescription;

    /* template */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wan")
    private Collection<DeviceRouterGroup> group = new ArrayList<>();

}
