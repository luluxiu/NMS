package com.nms.service;

import com.nms.bean.PageBean;
import com.nms.model.DeviceRouterTemplateLAN;
import com.nms.repository.DeviceRouterLANRepository;
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
public class DeviceRouterLANService {
    @Autowired
    private DeviceRouterLANRepository deviceRouterLANRepository;

    public DeviceRouterTemplateLAN findOneByTemplateName(String templateName) {

        return deviceRouterLANRepository.findOneByTemplateName(templateName);
    }

    public DeviceRouterTemplateLAN save(DeviceRouterTemplateLAN wan) {

        return deviceRouterLANRepository.save(wan);
    }

    public Page<DeviceRouterTemplateLAN> findAll(PageBean pb) {
        return deviceRouterLANRepository.findAll(
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }

    public Page<DeviceRouterTemplateLAN> findAllByTemplateName(PageBean pb, String name) {
        return deviceRouterLANRepository.findAllByTemplateName(name,
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }


    public DeviceRouterTemplateLAN findOne(Long id) {
        return deviceRouterLANRepository.findOne(id);
    }

    public void delete(Long id) {
        deviceRouterLANRepository.delete(id);
    }

    public List<DeviceRouterTemplateLAN> findAll() {
        return deviceRouterLANRepository.findAll();
    }

    public long count() {
        return deviceRouterLANRepository.count();
    }
}
