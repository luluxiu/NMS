package com.nms.repository;

import com.nms.model.DeviceRouter;
import com.nms.model.DeviceRouterSettingsOTA;
import com.nms.model.DeviceRouterTemplateOTA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by freedom on 2016/5/23.
 */
@Repository
@Transactional
public interface DeviceRouterOTARepository extends JpaRepository<DeviceRouterTemplateOTA, Long> {

    //DeviceRouterTemplateOTA findOneByRouter(DeviceRouter router);
    DeviceRouterTemplateOTA findOneByTemplateName (String templateName);
}
