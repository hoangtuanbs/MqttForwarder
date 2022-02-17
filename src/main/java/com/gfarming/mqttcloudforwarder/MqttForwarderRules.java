/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gfarming.mqttcloudforwarder;

import java.util.Iterator;
import java.util.List;
import org.json.*;

/**
 *
 * @author tuanv
 */
public final class MqttForwarderRules {
    public class FromCloudRule
    {
        private String cloudTopic;
        private String localTopic;

        public FromCloudRule(String cloudTopic, String localTopic) {
            this.cloudTopic = cloudTopic;
            this.localTopic = localTopic;
        }

        public String getCloudTopic() {
            return cloudTopic;
        }

        public void setCloudTopic(String cloudTopic) {
            this.cloudTopic = cloudTopic;
        }

        public String getLocalTopic() {
            return localTopic;
        }

        public void setLocalTopic(String localTopic) {
            this.localTopic = localTopic;
        }
    }
    
    public class ToCloudRule
    {
        private String cloudTopic;
        private String localTopic;

        public ToCloudRule(String localTopic, String cloudTopic) {
            this.cloudTopic = cloudTopic;
            this.localTopic = localTopic;
        }

        public String getCloudTopic() {
            return cloudTopic;
        }

        public void setCloudTopic(String cloudTopic) {
            this.cloudTopic = cloudTopic;
        }

        public String getLocalTopic() {
            return localTopic;
        }

        public void setLocalTopic(String localTopic) {
            this.localTopic = localTopic;
        }
    }
        
    public List<FromCloudRule> getFromCloudRules() {
        return fromCloudRules;
    }

    public List<ToCloudRule> getToCloudRules() {
        return toCloudRules;
    }
    
    public MqttForwarderRules(String jsonString) {
        JSONObject obj = new JSONObject(jsonString);
        
        parseJson(obj);
    }
    
    public void parseJson(JSONObject obj)
    {
        // Parse from cloud rules
        {
            JSONObject fromcloud = obj.getJSONObject("fromcloud");
            Iterator<String> keys = fromcloud.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                if (fromcloud.get(key) instanceof String) {
                      FromCloudRule rule = new FromCloudRule(key, fromcloud.get(key).toString());  
                      fromCloudRules.add(rule);
                }
            }
        }
        
        {
            JSONObject tocloud = obj.getJSONObject("tocloud");
            Iterator<String> keys = tocloud.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                if (tocloud.get(key) instanceof String) {
                      ToCloudRule rule = new ToCloudRule(key, tocloud.get(key).toString());  
                      toCloudRules.add(rule);
                }
            }
        }
    }
    
    public List<FromCloudRule> fromCloudRules;
    public List<ToCloudRule> toCloudRules;
}
