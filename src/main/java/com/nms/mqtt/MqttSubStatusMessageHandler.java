package com.nms.mqtt;

import com.nms.service.support.MqttMsgReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by freedom on 2016/5/4.
 */
public class MqttSubStatusMessageHandler implements MessageHandler {
    @Autowired
    private MqttMsgReceiver mqttMsgReceiver;

    private static Logger logger = LoggerFactory.getLogger(MqttSubStatusMessageHandler.class);

    @Async
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        logger.debug("====== Status =====\n" +
                "====== header: " + message.getHeaders().toString() +
                "====== body: " + message.getPayload().toString() +
                "====== Status end =======");
        mqttMsgReceiver.NMSMsgSingleAPStatus(message);
    }
}