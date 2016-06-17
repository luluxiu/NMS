package com.nms.controller;

import com.nms.bean.PageBean;
import com.nms.bean.TemplateOTABean;
import com.nms.model.DeviceRouterTemplateOTA;
import com.nms.service.DeviceRouterOTAService;
import com.nms.utils.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by freedom on 2016/5/23.
 */
@Controller
@RequestMapping("device/ap/template/ota")
public class DeviceRouterOTAController {

    @Autowired
    private DeviceRouterOTAService deviceRouterOTAService;

    @RequestMapping(value = "", method = GET)
    public String index(Model model) {

        model.addAttribute("menu", "menuTemplateOTA");
        return "device/ap/template/ota";
    }

    @RequestMapping(value = "", method = POST)
    public String add(Model model, TemplateOTABean ota) {

        DeviceRouterTemplateOTA drto;

        if(deviceRouterOTAService.count() >= 10) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "template table is full");
            return "jsonTemplate";
        }

        if(ota == null || ota.getTemplateName() == null) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "invalid parameters");
            return "jsonTemplate";
        }

        drto = deviceRouterOTAService.findOneByTemplateName(ota.getTemplateName());
        if(drto != null) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", ota.getTemplateName() + " is already exist");
            return "jsonTemplate";
        }

        deviceRouterOTAService.save(DTOUtil.map(ota, DeviceRouterTemplateOTA.class));

        model.addAttribute("result", "success");
        return "jsonTemplate";
    }

    @RequestMapping(value = "show", method = GET)
    public String show(@RequestParam(value = "search", required = false) String search,
                       PageBean pb, Model model) {

        Page<DeviceRouterTemplateOTA> otas = null;

        if(search != null && search.length() != 0) {
            otas = deviceRouterOTAService.findAllByTemplateName(pb, search);
        }
        else {
            otas = deviceRouterOTAService.findAll(pb);
        }

        model.addAttribute("result", "success");
        model.addAttribute("total", otas.getTotalElements());
        model.addAttribute("rows", otas.getContent());

        return "jsonTemplate";
    }

    @RequestMapping(value = "edit", method = POST)
    public String edit(Model model, TemplateOTABean ota) {

        DeviceRouterTemplateOTA drl;
        DeviceRouterTemplateOTA tmp;

        if(ota.getId() <= 0) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "id :" + ota.getId() + " is invalid");
            return "jsonTemplate";
        }

        drl = deviceRouterOTAService.findOne(ota.getId());
        if(drl == null) {
            model.addAttribute("result", "error");
            model.addAttribute("msg", "id :" + ota.getId() + " is invalid");
            return "jsonTemplate";
        }
        else {
            if(ota.getTemplateName().equals(drl.getTemplateName()) == false) {
                tmp = deviceRouterOTAService.findOneByTemplateName(ota.getTemplateName());
                if(tmp != null) {
                    model.addAttribute("result", "error");
                    model.addAttribute("msg", "template name :" + ota.getTemplateName()
                            + " is already exist");
                    return "jsonTemplate";
                }
            }
        }

        DTOUtil.mapTo(ota, drl);
        deviceRouterOTAService.save(drl);
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
            deviceRouterOTAService.delete(id);
            model.addAttribute("result", "success");
        }

        return "jsonTemplate";
    }
}