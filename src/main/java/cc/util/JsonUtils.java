package cc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked, unused")
public class JsonUtils {

    static ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

    /**
     * @param jsonStr (List<License>로 변환할 jsonString)
     * @return 변환된 List<License>
     */
    public static List<Map<String, Object>> JsonToList(String jsonStr) throws IOException {
        return objectMapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>(){});
    }

    /**
     * Object 객체를 Json String 으로 변환한다.
     * @param object (변환할 객체)
     * @return json String
     */
    public static String ObjToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * json String 을 Map 으로 변환한다.
     * @param jsonStr (json string)
     * @return Map<String, Object>
     */
    public static Map<String, Object> JsonToMap(String jsonStr) throws IOException {
        return objectMapper.readValue(jsonStr, new TypeReference<HashMap<String, Object>>() {});
    }

    /**
     * json String 을 지정한 클래스로 변환한다.
     * @param jsonStr (json string)
     * @param clazz 변환할 클래스 타입
     * @return Object
     */
    public static <T> T JsonToObj(String jsonStr, Class<T> clazz) throws IOException {
        return objectMapper.readValue(jsonStr, clazz);
    }

    public static JsonNode fileToJson(String path) throws IOException {
        return objectMapper.readTree(new File(path));
    }

    public static String nodeToJson(ObjectNode node) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }

    public static ObjectNode objToNode(Object object) {
        return objectMapper.convertValue(object, ObjectNode.class);
    }

    public static String getJsonValue(String jsonStr, String key) throws IOException {
        JsonNode node = objectMapper.readTree(jsonStr).get(key);
        return node != null ? node.isTextual() ? node.asText() : node.toString() : null;
    }

    public static String getJsonValue(File file, String key) throws IOException {
        return objectMapper.readTree(file).path(key).asText();
    }

    public static boolean isEmpty(String jsonStr, String key) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonStr);
        return rootNode.has(key) && !rootNode.get(key).asText().isEmpty();
    }
}
