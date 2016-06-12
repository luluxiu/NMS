package com.nms.repository;

import com.nms.model.DeviceRouterGroup;
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
public interface DeviceRouterGroupRepository extends JpaRepository<DeviceRouterGroup, Long> {

    Page<DeviceRouterGroup> findAllByName(String name, Pageable pageable);
    DeviceRouterGroup findOneByName(String name);
}
