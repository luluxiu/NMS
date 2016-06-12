package com.nms.repository;

import com.nms.model.DeviceRouter;
import com.nms.model.DeviceRouterSettingsFirewall;
import com.nms.model.DeviceRouterTemplateFirewall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by freedom on 2016/5/23.
 */
@Repository
@Transactional
public interface DeviceRouterFirewallRepository extends JpaRepository<DeviceRouterTemplateFirewall, Long> {

    //DeviceRouterTemplateFirewall findOneByRouter(DeviceRouter router);
    DeviceRouterTemplateFirewall findOneByTemplateName (String templateName);
}
