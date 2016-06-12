package com.nms.service;

import com.nms.bean.PageBean;
import com.nms.model.DeviceRouterTemplateWAN;
import com.nms.repository.DeviceRouterWANRepository;
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
public class DeviceRouterWANService {

    @Autowired
    private DeviceRouterWANRepository deviceRouterWANRepository;

    public DeviceRouterTemplateWAN findOneByTemplateName(String templateName) {

        return deviceRouterWANRepository.findOneByTemplateName(templateName);
    }

    public DeviceRouterTemplateWAN save(DeviceRouterTemplateWAN wan) {

        return deviceRouterWANRepository.save(wan);
    }

    public Page<DeviceRouterTemplateWAN> findAll(PageBean pb) {
        return deviceRouterWANRepository.findAll(
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }

    public Page<DeviceRouterTemplateWAN> findAllByTemplateName(PageBean pb, String name) {
        return deviceRouterWANRepository.findAllByTemplateName(name,
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }


    public DeviceRouterTemplateWAN findOne(Long id) {
        return deviceRouterWANRepository.findOne(id);
    }

    public void delete(Long id) {
        deviceRouterWANRepository.delete(id);
    }

    public List<DeviceRouterTemplateWAN> findAll() {
        return deviceRouterWANRepository.findAll();
    }

    public long count() {
        return deviceRouterWANRepository.count();
    }
}
