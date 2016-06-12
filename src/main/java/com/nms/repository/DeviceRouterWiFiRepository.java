package com.nms.repository;

import com.nms.model.DeviceRouter;
import com.nms.model.DeviceRouterSettingsWiFi;
import com.nms.model.DeviceRouterTemplateWiFi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by freedom on 2016/5/23.
 */
@Repository
@Transactional
public interface DeviceRouterWiFiRepository extends JpaRepository<DeviceRouterTemplateWiFi, Long> {

    //DeviceRouterTemplateWiFi findOneByRouter(DeviceRouter router);
    DeviceRouterTemplateWiFi findOneByTemplateName (String templateName);
    Page<DeviceRouterTemplateWiFi> findAllByTemplateName(String name, Pageable pageable);
}
