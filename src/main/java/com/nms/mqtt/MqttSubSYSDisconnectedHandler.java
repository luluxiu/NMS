package com.nms.mqtt;

import com.nms.service.support.MqttMsgReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by freedom on 2016/5/4.
 */
public class MqttSubSYSDisconnectedHandler implements MessageHandler {
    @Autowired
    private MqttMsgReceiver mqttMsgReceiver;

    @Async
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        System.out.println("====== Disconnected =====\n" +
                "====== header: " + message.getHeaders().toString() +
                "====== body: " + message.getPayload().toString() +
                "====== Disconnected end =======");
        mqttMsgReceiver.NMSMsgSysDisconnected(message);
    }
}