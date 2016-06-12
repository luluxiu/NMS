package com.nms.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nms.bean.PageBean;
import com.nms.bean.RouterGroupBean;
import com.nms.model.*;
import com.nms.repository.DeviceRouterGroupRepository;
import com.nms.service.support.MqttMsgSender;
import com.nms.service.support.NMSJsonBuilder;
import com.nms.utils.DTOUtil;
import com.nms.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by freedom on 2016/5/25.
 */
@Service
public class DeviceRouterGroupService {

    @Autowired
    private DeviceRouterGroupRepository deviceRouterGroupRepository;

    @Autowired
    private DeviceRouterWANService deviceRouterWANService;

    @Autowired
    private DeviceRouterLANService deviceRouterLANService;

    @Autowired
    private DeviceRouterWiFiService deviceRouterWiFiService;

    @Autowired
    private MqttMsgSender mqttMsgSender;


    public DeviceRouterGroup findOneByName(String name) {
        return deviceRouterGroupRepository.findOneByName(name);
    }

    public DeviceRouterGroup save(RouterGroupBean g) {
        DeviceRouterTemplateWAN w = deviceRouterWANService.findOneByTemplateName(g.getGroupWanName());
        DeviceRouterTemplateLAN l = deviceRouterLANService.findOneByTemplateName(g.getGroupLanName());
        DeviceRouterTemplateWiFi wi = deviceRouterWiFiService.findOneByTemplateName(g.getGroupWifiName());
        DeviceRouterGroup group = new DeviceRouterGroup();

        group.setName(g.getName());
        group.setDescription(g.getDescription());
        group.setWan(w);
        group.setLan(l);
        group.setWifi(wi);
        group.setGroupId(IDGenerator.getID());

        return deviceRouterGroupRepository.save(group);
    }

    public Page<DeviceRouterGroup> findAllByName(PageBean pb, String name) {
        return deviceRouterGroupRepository.findAllByName(name,
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }

    public Page<DeviceRouterGroup> findAll(PageBean pb) {
        return deviceRouterGroupRepository.findAll(
                new PageRequest(pb.getPage() - 1,
                        pb.getSize(),
                        Sort.Direction.valueOf(pb.getSortOrder().toUpperCase()),
                        pb.getSortName()
                )
        );
    }

    public DeviceRouterGroup findOne(long id){
        return deviceRouterGroupRepository.findOne(id);
    }

    public DeviceRouterGroup update(DeviceRouterGroup group, String wan, String lan, String wifi) {

        if(group.getWan().getTemplateName().equals(wan) == false) {
            group.setWan(deviceRouterWANService.findOneByTemplateName(wan));
        }

        if(group.getLan().getTemplateName().equals(lan) == false) {
            group.setLan(deviceRouterLANService.findOneByTemplateName(lan));
        }

        if(group.getWifi().getTemplateName().equals(wifi) == false) {
            group.setWifi(deviceRouterWiFiService.findOneByTemplateName(wifi));
        }

        return deviceRouterGroupRepository.save(group);
    }

    public void delete(Long id) {
        deviceRouterGroupRepository.delete(id);
    }


    public void effect(DeviceRouterGroup group, String scope) {
        ObjectNode node;
        String[]    sc = scope.split(",");
        int         count = 0;


        if(NMSJsonBuilder.contains(sc, "all") >= 0) {   /* topic - all */
            node = NMSJsonBuilder.AllJsonBuilder(group);
            mqttMsgSender.MqttMsgRouterGroupSetup(group.getGroupId(), node.toString());
        }
        else if(sc.length > 1) {                        /* topic - all */
            node = NMSJsonBuilder.AllJsonBuilder(sc, group);
            mqttMsgSender.MqttMsgRouterGroupSetup(group.getGroupId(), node.toString());
        }
        else if(sc[0].equalsIgnoreCase("wan")) {        /* topic - wan */
            node = NMSJsonBuilder.WANJsonBuilder(DTOUtil.map(group.getWan(), DeviceRouterSettingsWAN.class));
            mqttMsgSender.MqttMsgRouterGroupSetup(group.getGroupId(), node.toString());
        }
        else if(sc[0].equalsIgnoreCase("lan")) {        /* topic - lan */
            node = NMSJsonBuilder.LANJsonBuilder(DTOUtil.map(group.getLan(), DeviceRouterSettingsLAN.class));
            mqttMsgSender.MqttMsgRouterGroupSetup(group.getGroupId(), node.toString());
        }
        else if(sc[0].equalsIgnoreCase("wifi")) {       /* topic - wifi */
            node = NMSJsonBuilder.WiFiJsonBuilder(DTOUtil.map(group.getWifi(), DeviceRouterSettingsWiFi.class));
            mqttMsgSender.MqttMsgRouterGroupSetup(group.getGroupId(), node.toString());
        }
    }
}
