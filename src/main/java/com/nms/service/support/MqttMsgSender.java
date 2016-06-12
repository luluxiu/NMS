package com.nms.service.support;

import com.nms.mqtt.MqttPublishConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.stereotype.Component;

/**
 * Created by freedom on 2016/5/25.
 */
@Component
public class MqttMsgSender {

    @Autowired
    private MqttPublishConfig mqttPublishConfig;

    @Autowired
    private MqttPublishConfig.PubGateway gateway;


    /* single */
    public void MqttMsgRouterReset(String id) {
        MqttPahoMessageHandler mpmh = mqttPublishConfig.getMpmh();
        mpmh.setDefaultTopic("device/ap/single/id/" + id);
        gateway.sendToMqtt("{\"scope\": \"reset\", \"reset\": \"true\"}");
    }

    public void MqttMsgRouterReboot(String id) {
        MqttPahoMessageHandler mpmh = mqttPublishConfig.getMpmh();
        mpmh.setDefaultTopic("device/ap/single/id/" + id);
        gateway.sendToMqtt("{\"scope\": \"reboot\", \"reboot\": \"true\"}");
    }


    public void MqttMsgRouterSingleSetup(String id, String settings) {
        MqttPahoMessageHandler mpmh = mqttPublishConfig.getMpmh();
        mpmh.setDefaultTopic("device/ap/single/id/" + id);
        gateway.sendToMqtt(settings);
    }

    /* group */
    public void MqttMsgRouterGroupReset(String groupId) {
        MqttPahoMessageHandler mpmh = mqttPublishConfig.getMpmh();
        mpmh.setDefaultTopic("device/ap/group/" + groupId);
        gateway.sendToMqtt("{\"scope\": \"reset\", \"reset\": \"true\"}");
    }

    public void MqttMsgRouterGroupReboot(String groupId) {
        MqttPahoMessageHandler mpmh = mqttPublishConfig.getMpmh();
        mpmh.setDefaultTopic("device/ap/group/" + groupId);
        gateway.sendToMqtt("{\"scope\": \"reboot\", \"reboot\": \"true\"}");
    }

    public void MqttMsgRouterGroupSetup(String groupId, String settings) {
        MqttPahoMessageHandler mpmh = mqttPublishConfig.getMpmh();
        mpmh.setDefaultTopic("device/ap/group/" + groupId);
        gateway.sendToMqtt(settings);
    }
}
