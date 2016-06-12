package com.nms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by freedom on 2016/5/23.
 */
@Entity
@Data
@Table(name = "t_group_node")
@JsonIgnoreProperties(ignoreUnknown = true, value={"group"})
public class DeviceRouterGroupNode extends BaseModel {

    @Column(nullable = false, unique=true, length=18)
    private String start;

    @Column(nullable = false, unique=true, length=18)
    private String end;

    @Column(length=128)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceRouterGroup group;

    @Transient
    private String groupName;
}
