package com.nms.repository;

import com.nms.model.DeviceRouter;
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
public interface DeviceRouterRepository extends JpaRepository<DeviceRouter, Long> {

    Page<DeviceRouter> findByMac(String mac, Pageable pageable);
    DeviceRouter findOneByMac(String mac);
}
