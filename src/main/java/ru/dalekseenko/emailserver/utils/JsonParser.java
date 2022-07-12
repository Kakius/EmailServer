/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dalekseenko.emailserver.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author fif90
 */
public class JsonParser {

    private Map<String, Object> dataMap;

    public JsonParser(String data) {
        ClassLoger.log(JsonParser.class);
        dataMap = new HashMap<>();
        parsing(data);
    }

    private void parsing(String data) {
        try {
            JsonFactory factory = new JsonFactory();
            ObjectMapper objMapping = new ObjectMapper(factory);
            JsonNode jsonNode = objMapping.readTree(data);

            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();
            while (fieldsIterator.hasNext()) {
                Map.Entry<String, JsonNode> field = fieldsIterator.next();
                dataMap.put(field.getKey(), field.getValue());
                ClassLoger.logInfo("Key: " + field.getKey() + "\tValue:" + field.getValue());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public JsonParser getJsonParser(String key) {
        if (dataMap.isEmpty()) {
            ClassLoger.logError("collection is empty");
            return null;
        } else if (!dataMap.containsKey(key)) {
            ClassLoger.logError("Unknow key: " + key);
            return null;
        }
        String data = (String) dataMap.get(key);
        JsonParser ret = new JsonParser(data);
        return ret;
    }

    public boolean has(String key) {
        return dataMap.containsKey(key);
    }

    public Integer getInt(String key) {
        if (!dataMap.containsKey(key)) {
            return null;
        }
        TextNode node = (TextNode) dataMap.get(key);
        return node.asInt();
    }

    public String getString(String key) {
        if (!dataMap.containsKey(key)) {
            return null;
        }
        TextNode node = (TextNode) dataMap.get(key);
        return node.asText();
    }

    public Boolean getBoolean(String key) {
        if (!dataMap.containsKey(key)) {
            return null;
        }
        TextNode node = (TextNode) dataMap.get(key);
        return node.asBoolean();
    }
}
