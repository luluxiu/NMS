package com.nms.repository;

import com.nms.model.DeviceRouter;
import com.nms.model.DeviceRouterStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by freedom on 2016/5/23.
 */
public interface DeviceRouterStatusRepository extends JpaRepository<DeviceRouterStatus, Long> {

    DeviceRouterStatus findOneByRouter(DeviceRouter router);
}
