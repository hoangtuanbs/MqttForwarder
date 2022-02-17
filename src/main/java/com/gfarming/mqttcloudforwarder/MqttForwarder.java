/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gfarming.mqttcloudforwarder;
import javax.lang.model.SourceVersion;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author tuanv
 */
public class MqttForwarder {
    private MqttClient remoteClient;
    String remoteBroker       = "tcp://iot.eclipse.org:1883";
    private MqttClient localClient;
    String localBroker       = "tcp://127.0.0.1:1883";
    
    String topic        = "MQTT Examples";
    String content      = "Message from MqttPublishSample";
    int qos             = 2;
    
    String clientId     = "SampleClientId";
    RemoteListener remoteListener = new RemoteListener(this);
    LocalListener localListener = new LocalListener(this);
    
    public MqttForwarder(String clientid, String remoteBroker, String localBroker)
    {
        clientId = clientid;
    }
    
    public class RemoteListener implements MqttCallback
    {
        private MqttForwarder parent;
        private RemoteListener(MqttForwarder aThis) {
            parent = aThis;
        }

        @Override
        public void messageArrived(String topic, MqttMessage mm) throws Exception {
            
        }            

        @Override
        public void connectionLost(Throwable thrwbl) {
            
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken imdt) {
            
        }
    };
    
    public class LocalListener implements MqttCallback
    {
        private MqttForwarder parent;
        private LocalListener(MqttForwarder aThis) {
            parent = aThis;
        }
        @Override
        public void messageArrived(String topic, MqttMessage mm) throws Exception {
            
        }            

        @Override
        public void connectionLost(Throwable thrwbl) {
            
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken imdt) {
            
        }
    };
            
    public void init() throws MqttException
    {
        remoteClient = new MqttClient(remoteBroker, clientId, new MemoryPersistence());
        remoteClient.setCallback(remoteListener);
        MqttConnectOptions remopts = new MqttConnectOptions();
        remopts.setCleanSession(true);
        remoteClient.connect(remopts);
        
        localClient = new MqttClient(localBroker, clientId, new MemoryPersistence());
        localClient.setCallback(localListener);
        MqttConnectOptions localopts = new MqttConnectOptions();
        localopts.setCleanSession(true);
        localClient.connect(localopts);
        

        // Configure rules
        String[] remoteTopics = null;
        String[] localTopics = null;
        
        // Subscribe remote topics
        remoteClient.subscribe(remoteTopics);
        // Subscribe local topics
        localClient.subscribe(localTopics);
    }
}
