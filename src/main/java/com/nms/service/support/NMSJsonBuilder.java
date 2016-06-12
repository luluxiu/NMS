package com.nms.service.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nms.model.*;
import com.nms.utils.DTOUtil;

/**
 * Created by freedom on 2016/5/24.
 */
public class NMSJsonBuilder {

    public static ObjectNode WANJsonBuilder(DeviceRouterSettingsWAN wan) {
        ObjectNode      root;

        try {
            ObjectMapper    mapper = new ObjectMapper();
            ObjectNode      node = mapper.createObjectNode();

            root = mapper.createObjectNode();
            root.put("wan", node);
            root.put("scope", "wan");

            node.put("protocol", wan.getProtocol());
            if(wan.getProtocol().equals("dhcp")) {
                /* pass through */
            }
            else if(wan.getProtocol().equals("static")) {
                node.put("address", wan.getAddress());
                node.put("netmask", wan.getNetmask());
                node.put("gateway", wan.getGateway());
            }
            else if(wan.getProtocol().equals("pppoe")) {
                node.put("username", wan.getUsername());
                node.put("password", wan.getPassword());
            }
            else if(wan.getProtocol().equals("3g")) {
                node.put("modemDevice", wan.getModemDevice());
                node.put("serviceType", wan.getServiceType());
                node.put("APN", wan.getAPN());
                node.put("dialNumber", wan.getDialNumber());
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }

        return root;
    }


    public static ObjectNode LANJsonBuilder(DeviceRouterSettingsLAN lan) {
        ObjectNode      root;

        try {
            ObjectMapper    mapper = new ObjectMapper();
            ObjectNode      node = mapper.createObjectNode();

            root = mapper.createObjectNode();
            root.put("lan", node);
            root.put("scope", "lan");

            node.put("lanAddress", lan.getLanAddress());
            node.put("lanNetmask", lan.getLanNetmask());
            node.put("dhcp", lan.getDhcp());
            node.put("start", lan.getStart());
            node.put("limit", lan.getLimit());
            node.put("leaseTime", lan.getLeaseTime());

        }
        catch (Exception e) {
            return null;
        }

        return root;
    }


    public static ObjectNode WiFiJsonBuilder(DeviceRouterSettingsWiFi wifi) {
        ObjectNode      root;

        try {
            ObjectMapper    mapper = new ObjectMapper();
            ObjectNode      node = mapper.createObjectNode();

            root = mapper.createObjectNode();
            root.put("wifi", node);
            root.put("scope", "wifi");

            node.put("wifi", wifi.getWifi());
            node.put("ssid", wifi.getSsid());
            node.put("channel", wifi.getChannel());
            node.put("maxStation", wifi.getMaxStation());
            node.put("encryption", wifi.getEncryption());

            if(wifi.getEncryption().equals("none")) {
                /* pass through */
            }
            else if(wifi.getEncryption().equals("open")){
                node.put("key", wifi.getKey());
            }
            else if(wifi.getEncryption().equals("psk")){
                node.put("cipher", wifi.getCipher());
                node.put("key", wifi.getKey());
            }
            else if(wifi.getEncryption().equals("psk2")){
                node.put("cipher", wifi.getCipher());
                node.put("key", wifi.getKey());
            }
            else if(wifi.getEncryption().equals("psk-mixed")){
                node.put("cipher", wifi.getCipher());
                node.put("key", wifi.getKey());
            }

        }
        catch (Exception e) {
            return null;
        }

        return root;
    }


    public static ObjectNode AllJsonBuilder(DeviceRouter router) {
        ObjectNode      root = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node;

            root = mapper.createObjectNode();

            node = WANJsonBuilder(router.getWan());
            root.put("wan", node);

            node = LANJsonBuilder(router.getLan());
            root.put("lan", node);

            node = WiFiJsonBuilder(router.getWifi());
            root.put("wifi", node);

            root.put("scope", "all");
        }
        catch (Exception e) {
            return null;
        }

        return root;
    }

    public static ObjectNode AllJsonBuilder(DeviceRouterGroup group) {
        ObjectNode      root = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node;

            root = mapper.createObjectNode();

            node = WANJsonBuilder(DTOUtil.map(group.getWan(), DeviceRouterSettingsWAN.class));
            root.put("wan", node);

            node = LANJsonBuilder(DTOUtil.map(group.getLan(), DeviceRouterSettingsLAN.class));
            root.put("lan", node);

            node = WiFiJsonBuilder(DTOUtil.map(group.getWifi(), DeviceRouterSettingsWiFi.class));
            root.put("wifi", node);

            root.put("scope", "all");
        }
        catch (Exception e) {
            return null;
        }

        return root;
    }

    public static ObjectNode AllJsonBuilder(String[] scope, DeviceRouter router) {
        ObjectNode      root = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node;

            root = mapper.createObjectNode();

            if(contains(scope, "wan") >= 0) {
                node = WANJsonBuilder(router.getWan());
                root.put("wan", node);
            }

            if(contains(scope, "lan") >= 0) {
                node = LANJsonBuilder(router.getLan());
                root.put("lan", node);
            }

            if(contains(scope, "wifi") >= 0) {
                node = WiFiJsonBuilder(router.getWifi());
                root.put("wifi", node);
            }

            root.put("scope", "all");
        }
        catch (Exception e) {
            return null;
        }

        return root;
    }

    public static ObjectNode AllJsonBuilder(String[] scope, DeviceRouterGroup group) {
        ObjectNode      root = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node;

            root = mapper.createObjectNode();

            if(contains(scope, "wan") >= 0) {
                node = WANJsonBuilder(DTOUtil.map(group.getWan(), DeviceRouterSettingsWAN.class));
                root.put("wan", node);
            }

            if(contains(scope, "lan") >= 0) {
                node = LANJsonBuilder(DTOUtil.map(group.getLan(), DeviceRouterSettingsLAN.class));
                root.put("lan", node);
            }

            if(contains(scope, "wifi") >= 0) {
                node = WiFiJsonBuilder(DTOUtil.map(group.getWifi(), DeviceRouterSettingsWiFi.class));
                root.put("wifi", node);
            }
            root.put("scope", "all");
        }
        catch (Exception e) {
            return null;
        }

        return root;
    }


    public static int contains(String[] arry, String str) {
        for(int i = 0; i < arry.length; i++) {
            if(arry[i].equalsIgnoreCase(str)) {
                return i;
            }
        }
        return -1;
    }
}
