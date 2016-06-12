package com.nms.repository;

import com.nms.model.DeviceRouter;
import com.nms.model.DeviceRouterSettingsQoS;
import com.nms.model.DeviceRouterTemplateQoS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by freedom on 2016/5/23.
 */
@Repository
@Transactional
public interface DeviceRouterQoSRepository extends JpaRepository<DeviceRouterTemplateQoS, Long> {

    //DeviceRouterTemplateQoS findOneByRouter(DeviceRouter router);
    DeviceRouterTemplateQoS findOneByTemplateName (String templateName);
}
