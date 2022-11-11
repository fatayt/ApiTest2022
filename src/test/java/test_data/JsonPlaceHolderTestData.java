package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> getExceptedDataSetUp() {
        Map<String, Object> exceptedData = new HashMap<>();
        exceptedData.put("userId", 55);
        exceptedData.put("title", "Tidy your room");
        exceptedData.put("completed", false);


        return exceptedData;
    }

}
