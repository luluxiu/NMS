package com.nms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by freedom on 2016/5/23.
 */
@Entity
@Data
@Table(name = "t_status")
@JsonIgnoreProperties(ignoreUnknown = true, value={"hibernateLazyInitializer", "handler"})
public class DeviceRouterStatus extends BaseModel {


    private String version;

    private String system;

    private String wan;

    private String lan;

    private String wifi;

    private String client;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "status")
    private DeviceRouter router;
}
