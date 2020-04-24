package service;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.integration.transports.netty.NettyConnectorFactory;
import org.hornetq.integration.transports.netty.TransportConstants;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

public class NotificationService {

    public NotificationService () {}

    /**
     * After share a file with another user, a message queue named by this file ID is created
     */
    public void sendNotification (String message, long fileID) {
        try {
            Map<String, Object> connectionParams = new HashMap<String, Object>();
            connectionParams.put(TransportConstants.PORT_PROP_NAME, 5445);

            TransportConfiguration transportConfiguration = new TransportConfiguration(
                    NettyConnectorFactory.class.getName(), connectionParams);

            //Create and start connection
            QueueConnectionFactory queueFactory = (QueueConnectionFactory) HornetQJMSClient.createConnectionFactory(transportConfiguration);
            QueueConnection queueConn = queueFactory.createQueueConnection();
            queueConn.start();

            //Create queue session
            QueueSession queueSess = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            //Get the Queue object
            Queue queue = HornetQJMSClient.createQueue(String.valueOf("file" + fileID));
            //Create QueueSender object
            QueueSender sender = queueSess.createSender(queue);
            //Create TextMessage object
            TextMessage msg = queueSess.createTextMessage();
            msg.setText(message);
            //Send message
            sender.send(msg);
            System.out.println("Notification successfully sent.");

            //Close connection
            queueSess.close();
            queueConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
