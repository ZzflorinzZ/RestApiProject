package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class JsonUtils {
	
	public static JSONObject createJsonObject(JSONObject jsonObjectName) {
		jsonObjectName = new JSONObject();
		return jsonObjectName;
	}
	
	public static void writeJsonFile(JSONObject jsonObjectName, String jsonFileName) {
		try (FileWriter jsonFile = new FileWriter(jsonFileName)){
			jsonFile.write(jsonObjectName.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
	
	public static File returnJsonFile(String jsonFileName) { 
		File jsonFile = new File (jsonFileName);	
		return jsonFile; 
	}

}
