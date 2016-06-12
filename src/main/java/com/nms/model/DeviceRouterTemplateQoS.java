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
@Table(name = "t_temp_qos")
@JsonIgnoreProperties(ignoreUnknown = true, value={"group", "hibernateLazyInitializer", "handler"})
public class DeviceRouterTemplateQoS extends BaseModel {

    /* template */

    @Column(length = 32)
    private String templateName;

    @Column(length = 128)
    private String templateDescription;

    /* template */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "qos")
    private Collection<DeviceRouterGroup> group = new ArrayList<>();

}
