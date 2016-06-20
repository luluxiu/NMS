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
@Table(name = "t_temp_lan")
@JsonIgnoreProperties(ignoreUnknown = true, value={"group", "hibernateLazyInitializer", "handler"})
public class DeviceRouterTemplateLAN extends BaseModel {

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

    /* template */

    @Column(length = 32)
    private String templateName;

    @Column(length = 128)
    private String templateDescription;

    /* template */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lan")
    private Collection<DeviceRouterGroup> group = new ArrayList<>();

}
