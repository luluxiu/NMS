package com.nms.service;

import com.nms.bean.PageBean;
import com.nms.bean.RouterGroupNodeBean;
import com.nms.model.DeviceRouterGroup;
import com.nms.model.DeviceRouterGroupNode;
import com.nms.repository.DeviceRouterGroupNodeRepository;
import static com.nms.service.support.CacheNameSettings.*;
import com.nms.utils.DTOUtil;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by freedom on 2016/5/25.
 */
@Service
public class DeviceRouterGroupNodeService {

    @Autowired
    private DeviceRouterGroupNodeRepository deviceRouterGroupNodeRepository;

    @Autowired
    private DeviceRouterGroupService deviceRouterGroupService;


    public Boolean isMACValid(String start, String end) {
        Page<DeviceRouterGroupNode> nodes = deviceRouterGroupNodeRepository.isMACValid(
                                                start,
                                                end,
                                                new PageRequest(0, 1)
                                            );

        if(nodes != null && nodes.getContent().isEmpty() == false) {
            return false;
        }

        return true;

    }

    @CacheEvict(value = CACHE_NAME_AP_NODE, allEntries = true)
    public DeviceRouterGroupNode save(RouterGroupNodeBean n, String groupName) {
        DeviceRouterGroup group = deviceRouterGroupService.findOneByName(groupName);
        DeviceRouterGroupNode node = null;

        if(group == null) {
            return null;
        }
        else {
            node = new DeviceRouterGroupNode();
            DTOUtil.mapTo(n, node);
            node.setGroup(group);
        }

        return deviceRouterGroupNodeRepository.save(node);
    }


    public Page<DeviceRouterGroupNode> findAll(PageBean pb) {
        return deviceRouterGroupNodeRepository.findAll(
                new PageRequest(pb.getPage() - 1,
                pb.getSize(),
                Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                pb.getSortName()
        ));
    }

    public DeviceRouterGroupNode findAllByMac(String mac) {
        return deviceRouterGroupNodeRepository.findNodeByMac(mac);
    }

    @CacheEvict(value = CACHE_NAME_AP_NODE, allEntries = true)
    public void delete(long id) {
        deviceRouterGroupNodeRepository.delete(id);
    }

    @Cacheable(value = CACHE_NAME_AP_NODE, key = "#mac")
    public DeviceRouterGroup findGroupByMAC(String mac) {

        DeviceRouterGroupNode node = deviceRouterGroupNodeRepository.findNodeByMac(mac);

        if(node == null) {
            return null;
        }

        Hibernate.initialize(node.getGroup());
        return node.getGroup();
    }

    public Page<DeviceRouterGroupNode> findAllByGroup(PageBean pb, DeviceRouterGroup group) {
        return deviceRouterGroupNodeRepository.findAllByGroup(group,
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                ));
    }
}
