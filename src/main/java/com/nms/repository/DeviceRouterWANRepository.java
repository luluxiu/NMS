package com.nms.repository;

import com.nms.model.DeviceRouter;
import com.nms.model.DeviceRouterSettingsWAN;
import com.nms.model.DeviceRouterTemplateWAN;
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
public interface DeviceRouterWANRepository extends JpaRepository<DeviceRouterTemplateWAN, Long> {

    //DeviceRouterTemplateWAN findOneByRouter(DeviceRouter router);
    DeviceRouterTemplateWAN findOneByTemplateName (String templateName);

    Page<DeviceRouterTemplateWAN> findAllByTemplateName(String name, Pageable pageable);
}
