package com.nms.service;

import com.nms.bean.PageBean;
import com.nms.model.DeviceRouterTemplateWiFi;
import com.nms.repository.DeviceRouterWiFiRepository;
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
public class DeviceRouterWiFiService {
    @Autowired
    private DeviceRouterWiFiRepository deviceRouterWiFiRepository;

    public DeviceRouterTemplateWiFi findOneByTemplateName(String templateName) {

        return deviceRouterWiFiRepository.findOneByTemplateName(templateName);
    }

    public DeviceRouterTemplateWiFi save(DeviceRouterTemplateWiFi wan) {

        return deviceRouterWiFiRepository.save(wan);
    }

    public Page<DeviceRouterTemplateWiFi> findAll(PageBean pb) {
        return deviceRouterWiFiRepository.findAll(
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }

    public Page<DeviceRouterTemplateWiFi> findAllByTemplateName(PageBean pb, String name) {
        return deviceRouterWiFiRepository.findAllByTemplateName(name,
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }


    public DeviceRouterTemplateWiFi findOne(Long id) {
        return deviceRouterWiFiRepository.findOne(id);
    }

    public void delete(Long id) {
        deviceRouterWiFiRepository.delete(id);
    }

    public List<DeviceRouterTemplateWiFi> findAll(){
        return deviceRouterWiFiRepository.findAll();
    }

    public long count() {
        return deviceRouterWiFiRepository.count();
    }
}
