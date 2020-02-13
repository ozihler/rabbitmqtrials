package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    public static final String QUEUE_NAME = "MY_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        String ip = "localhost";
        factory.setHost(ip);
        factory.setPort(80686);
        try (Connection con = factory.newConnection()) {
            Channel channel = con.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String msg = "Hello World";
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println(" [x] Sent '"+msg+"'");
        }
    }
}
