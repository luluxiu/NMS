package com.nms.controller;

import com.nms.bean.PageBean;
import com.nms.bean.TemplateWifiBean;
import com.nms.model.DeviceRouterTemplateWiFi;
import com.nms.service.DeviceRouterWiFiService;
import com.nms.utils.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by freedom on 2016/5/23.
 */
@Controller
@RequestMapping("device/ap/template/wifi")
public class DeviceRouterWiFiController {


    @Autowired
    private DeviceRouterWiFiService deviceRouterWiFiService;

    @RequestMapping(value = "", method = GET)
    public String index(Model model) {


        model.addAttribute("menu", "menuTemplateWiFi");
        return "device/ap/template/wifi";
    }

    @RequestMapping(value = "", method = POST)
    public String add(Model model, TemplateWifiBean wifi) {
        DeviceRouterTemplateWiFi drw;

        if(deviceRouterWiFiService.count() >= 10) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "template table is full");
            return "jsonTemplate";
        }

        if(wifi == null || wifi.getTemplateName() == null) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "invalid parameters");
            return "jsonTemplate";
        }

        drw = deviceRouterWiFiService.findOneByTemplateName(wifi.getTemplateName());
        if(drw != null) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", wifi.getTemplateName() + " is already exist");
            return "jsonTemplate";
        }

        deviceRouterWiFiService.save(DTOUtil.map(wifi, DeviceRouterTemplateWiFi.class));

        model.addAttribute("result", "success");
        return "jsonTemplate";
    }

    @RequestMapping(value = "show", method = GET)
    public String show(@RequestParam(value = "search", required = false) String search,
                       PageBean pb, Model model) {

        Page<DeviceRouterTemplateWiFi> wifis = null;

        if(search != null && search.length() != 0) {
            wifis = deviceRouterWiFiService.findAllByTemplateName(pb, search);
        }
        else {
            wifis = deviceRouterWiFiService.findAll(pb);
        }

        model.addAttribute("result", "success");
        model.addAttribute("total", wifis.getTotalElements());
        model.addAttribute("rows", wifis.getContent());

        return "jsonTemplate";
    }

    @RequestMapping(value = "edit", method = POST)
    public String edit(Model model, TemplateWifiBean wifi) {

        DeviceRouterTemplateWiFi drw;
        DeviceRouterTemplateWiFi tmp;

        if(wifi.getId() <= 0) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "id :" + wifi.getId() + " is invalid");
            return "jsonTemplate";
        }

        drw = deviceRouterWiFiService.findOne(wifi.getId());
        if(drw == null) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "id :" + wifi.getId() + " is invalid");
            return "jsonTemplate";
        }
        else {
            if(wifi.getTemplateName().equals(drw.getTemplateName()) == false) {
                tmp = deviceRouterWiFiService.findOneByTemplateName(wifi.getTemplateName());
                if(tmp != null) {
                    model.addAttribute("result", "error");
                    model.addAttribute("msg", "template name :" + wifi.getTemplateName()
                            + " is already exist");
                    return "jsonTemplate";
                }
            }
        }

        DTOUtil.mapTo(wifi, drw);
        deviceRouterWiFiService.save(drw);
        model.addAttribute("result", "success");

        return "jsonTemplate";
    }

    @RequestMapping(value = "delete", method = POST)
    public String delete(Model model, @RequestParam(name="id", required = true) Long id) {

        if(id <= 0) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "id :" + id + " is invalid");
        }
        else {
            deviceRouterWiFiService.delete(id);
            model.addAttribute("result", "success");
        }

        return "jsonTemplate";
    }
}
