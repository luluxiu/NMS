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
@Table(name = "t_temp_ota")
@JsonIgnoreProperties(ignoreUnknown = true, value={"group", "hibernateLazyInitializer", "handler"})
public class DeviceRouterTemplateOTA extends BaseModel {

    @Column(nullable = false)
    private int mode;

    @Column(length = 128, nullable = false)
    private String server;

    @Column(nullable = false)
    private int pridDelay;

    @Column(nullable = false)
    private int restoreFlag;

    private int windowStart;

    private int windowSize;

    /* template */

    @Column(length = 32)
    private String templateName;

    @Column(length = 128)
    private String templateDescription;

    /* template */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ota")
    private Collection<DeviceRouterGroup> group = new ArrayList<>();
}
