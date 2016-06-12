package com.nms.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nms.bean.PageBean;
import com.nms.bean.TemplateLanBean;
import com.nms.bean.TemplateWanBean;
import com.nms.bean.TemplateWifiBean;
import com.nms.model.DeviceRouter;
import com.nms.model.DeviceRouterSettingsLAN;
import com.nms.model.DeviceRouterSettingsWAN;
import com.nms.model.DeviceRouterSettingsWiFi;
import com.nms.repository.DeviceRouterRepository;
import com.nms.service.support.MqttMsgSender;
import com.nms.service.support.NMSJsonBuilder;
import com.nms.utils.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by freedom on 2016/5/23.
 */
@Service
public class DeviceRouterService {

    @Autowired
    private DeviceRouterRepository routerRepository;
/*
    @Autowired
    private DeviceRouterWANRepository deviceRouterWANRepository;

    @Autowired
    private DeviceRouterLANRepository deviceRouterLANRepository;

    @Autowired
    private DeviceRouterWiFiRepository deviceRouterWiFiRepository;
*/
    @Autowired
    private MqttMsgSender mqttMsgSender;

    public static final String CACHE_NAME = "cache.device.router";

    //@Cacheable(value = CACHE_NAME)
    public Page<DeviceRouter> findAll(PageBean pb) {
        return routerRepository.findAll(
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }

    //@Cacheable(value = CACHE_NAME)
    public Page<DeviceRouter> findAllByMac(PageBean pb, String mac) {

        return routerRepository.findByMac(mac,
                new PageRequest(pb.getPage() - 1,
                                pb.getSize(),
                                Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                                pb.getSortName()
                )
        );
    }


    //@CacheEvict(value = CACHE_NAME, allEntries = true)
    public void delete(Long id) {
        routerRepository.delete(id);
    }

    //@Cacheable(value = CACHE_NAME, key = "#id.toString().concat('-router')")
    public DeviceRouter findOne(Long id) {
        return routerRepository.findOne(id);
    }

    public DeviceRouter findOneByMac(String mac) {
        return routerRepository.findOneByMac(mac);
    }

    public void save(DeviceRouter router,
                     TemplateWanBean wan, TemplateLanBean lan, TemplateWifiBean wifi) {


        DTOUtil.mapTo(wan, router.getWan());
        DTOUtil.mapTo(lan, router.getLan());
        DTOUtil.mapTo(wifi, router.getWifi());

        routerRepository.save(router);
    }

    public void save(DeviceRouter router) {


        routerRepository.save(router);
    }

    public void effect(DeviceRouter router, String scope) {
        ObjectNode  node;
        String[]    sc = scope.split(",");
        int         count = 0;


        if(NMSJsonBuilder.contains(sc, "all") >= 0) {   /* topic - all */
            node = NMSJsonBuilder.AllJsonBuilder(router);
            mqttMsgSender.MqttMsgRouterSingleSetup(router.getMac(), node.toString());
        }
        else if(sc.length > 1) {                        /* topic - all */
            node = NMSJsonBuilder.AllJsonBuilder(sc, router);
            mqttMsgSender.MqttMsgRouterSingleSetup(router.getMac(), node.toString());
        }
        else if(sc[0].equalsIgnoreCase("wan")) {        /* topic - wan */
            node = NMSJsonBuilder.WANJsonBuilder(router.getWan());
            mqttMsgSender.MqttMsgRouterSingleSetup(router.getMac(), node.toString());
        }
        else if(sc[0].equalsIgnoreCase("lan")) {        /* topic - lan */
            node = NMSJsonBuilder.LANJsonBuilder(router.getLan());
            mqttMsgSender.MqttMsgRouterSingleSetup(router.getMac(), node.toString());
        }
        else if(sc[0].equalsIgnoreCase("wifi")) {       /* topic - wifi */
            node = NMSJsonBuilder.WiFiJsonBuilder(router.getWifi());
            mqttMsgSender.MqttMsgRouterSingleSetup(router.getMac(), node.toString());
        }
    }

    /* topic - reset */
    public void reset(Long id) {
        DeviceRouter router = routerRepository.findOne(id);

        if(router != null) {
            mqttMsgSender.MqttMsgRouterReset(router.getMac());
        }
    }

    /* topic - reboot */
    public void reboot(Long id) {
        DeviceRouter router = routerRepository.findOne(id);

        if(router != null) {
            mqttMsgSender.MqttMsgRouterReboot(router.getMac());
        }
    }
}
