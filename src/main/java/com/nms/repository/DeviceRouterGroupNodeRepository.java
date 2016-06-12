package com.nms.repository;

import com.nms.model.DeviceRouterGroup;
import com.nms.model.DeviceRouterGroupNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by freedom on 2016/5/23.
 */
@Repository
@Transactional
public interface DeviceRouterGroupNodeRepository extends JpaRepository<DeviceRouterGroupNode, Long> {

    Page<DeviceRouterGroupNode> findAllByGroup(DeviceRouterGroup group, Pageable pageRequest);

    @Query("select o from DeviceRouterGroupNode o where (o.start <= :mac and o.end >= :mac)")
    DeviceRouterGroupNode findNodeByMac(@Param("mac") String mac);

    @Query( "select o from DeviceRouterGroupNode o where (" +
            "(o.start <= :a and :a <= o.end) or " +
            "(o.start <= :b and :b <= o.end) or " +
            "(o.start >= :a and :b >= o.end)) ")
    Page<DeviceRouterGroupNode> isMACValid(@Param("a") String a, @Param("b") String b, Pageable pageRequest);

}
