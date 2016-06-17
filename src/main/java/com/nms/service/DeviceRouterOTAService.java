package com.nms.service;

import com.nms.bean.PageBean;
import com.nms.model.DeviceRouterTemplateOTA;
import com.nms.repository.DeviceRouterOTARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by freedom on 2016/5/24.
 */
@Service
public class DeviceRouterOTAService {

    @Autowired
    private DeviceRouterOTARepository deviceRouterOTARepository;

    public Long count() {
        return deviceRouterOTARepository.count();
    }

    public DeviceRouterTemplateOTA findOneByTemplateName(String name) {
        return deviceRouterOTARepository.findOneByTemplateName(name);
    }

    public DeviceRouterTemplateOTA save(DeviceRouterTemplateOTA ota) {
        return deviceRouterOTARepository.save(ota);
    }

    public Page<DeviceRouterTemplateOTA> findAllByTemplateName(PageBean pb, String name) {
        return deviceRouterOTARepository.findAllByTemplateName(name,
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }

    public Page<DeviceRouterTemplateOTA> findAll(PageBean pb) {
        return deviceRouterOTARepository.findAll(
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }

    public DeviceRouterTemplateOTA findOne(Long id) {
        return deviceRouterOTARepository.findOne(id);
    }

    public void delete(Long id) {
        deviceRouterOTARepository.delete(id);
    }

    public List<DeviceRouterTemplateOTA> findAll() {
        return deviceRouterOTARepository.findAll();
    }
}
