package com.nms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by freedom on 2016/5/23.
 */
@Entity
@Data
@Table(name = "t_ota")
@JsonIgnoreProperties(ignoreUnknown = true, value={"router", "hibernateLazyInitializer", "handler"})
public class DeviceRouterSettingsOTA extends BaseModel {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "ota")
    private DeviceRouter router;
}
