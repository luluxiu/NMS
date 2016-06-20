package com.nms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by freedom on 2016/5/23.
 */
@Entity
@Data
@Table(name = "t_lan")
@JsonIgnoreProperties(ignoreUnknown = true, value={"router", "hibernateLazyInitializer", "handler"})
public class DeviceRouterSettingsLAN extends BaseModel {

    @Column(length = 16)
    private String lanAddress;

    @Column(length = 16)
    private String lanNetmask;

    @Column(name = "lanDhcp")
    private int dhcp;

    @Column(name = "lanStart")
    private int start;

    @Column(name = "lanLimit")
    private int limit;

    @Column(length = 16)
    private String leaseTime;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "lan")
    private DeviceRouter router;

}
