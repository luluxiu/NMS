package com.nms.repository;

import com.nms.model.DeviceRouter;
import com.nms.model.DeviceRouterSettingsLAN;
import com.nms.model.DeviceRouterTemplateLAN;
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
public interface DeviceRouterLANRepository extends JpaRepository<DeviceRouterTemplateLAN, Long> {

    //DeviceRouterTemplateLAN findOneByRouter(DeviceRouter router);
    DeviceRouterTemplateLAN findOneByTemplateName (String templateName);
    Page<DeviceRouterTemplateLAN> findAllByTemplateName(String name, Pageable pageable);
}
