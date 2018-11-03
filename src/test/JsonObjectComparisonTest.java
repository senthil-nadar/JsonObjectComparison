package test;

import lib.JsonToHashMapCustom;

import java.util.HashMap;

import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsonObjectComparisonTest {

	
	//Same objects returns true even if strict mode is set to true
	@Test(priority = 0)
	public void compareTwoJsonObjectUsingAssert(){
		JSONObject expected = new JSONObject(
				  "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}"
				);
		JSONObject actual = new JSONObject(
				  "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}"
				);
		
			JSONAssert.assertEquals(expected, actual, true);
	}
	
	//Same objects with reordered returns true even if strict mode is set to true
	@Test(priority = 1)
	public void compareSameTwoJsonObjectUsingAssertWithOrderChange(){
		JSONObject expected = new JSONObject(
				  "{\"name\":\"jon doe\",\"city\":\"chicago\",\"age\":\"22\"}"
				);
		JSONObject actual = new JSONObject(
				  "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}"
				);
		
			JSONAssert.assertEquals(expected, actual, true);
	}
	
	//Same objects with array element reordered returns false if strict mode is set to true
	@Test(priority = 3)
	public void compareTwoJsonObjectUsingAssertNestedDataOrderChange(){
		JSONObject expected = new JSONObject(
				"{id:1,name:\"Joe\",friends:[{id:2,name:\"Pat\",pets:[\"dog\"]},{id:3,name:\"Sue\",pets:[\"bird\",\"fish\"]}],pets:[]}"
				);
		JSONObject actual = new JSONObject(
				"{id:1,name:\"Joe\",friends:[{id:2,name:\"Pat\",pets:[\"dog\"]},{id:3,name:\"Sue\",pets:[\"fish\",\"bird\"]}],pets:[]}"
				);	JSONAssert.assertEquals(expected, actual, true);
	}
	
	//Same objects with array element reordered returns false using custom function return to check two JSON Object
	@Test(priority = 4)
	public void compareTwoJsonObject(){
		JSONObject expected = new JSONObject(
				"{id:1,name:\"Joe\",friends:[{id:2,name:\"Pat\",pets:[\"dog\"]},{id:3,name:\"Sue\",pets:[\"bird\",\"fish\"]}],pets:[]}"
				);
		JSONObject actual = new JSONObject(
				"{id:1,name:\"Joe\",friends:[{id:2,name:\"Pat\",pets:[\"dog\"]},{id:3,name:\"Sue\",pets:[\"fish\",\"bird\"]}],pets:[]}"
				);
		
			if(expected == actual)
				Assert.assertTrue(true);
			
			System.out.println("entered");
			HashMap<String,Object> mapExpected=new HashMap<String,Object>();
			mapExpected = JsonToHashMapCustom.jsonToMap(expected);
			
			HashMap<String,Object> mapActual=new HashMap<String,Object>();
			mapActual = JsonToHashMapCustom.jsonToMap(actual);
			
			for (HashMap.Entry<String,Object> entry : mapExpected.entrySet()) {
				  String key = entry.getKey();
				  Object value = entry.getValue();
				  // do stuff
				  if(!(mapActual.containsKey(key))){
					  Assert.assertTrue(false);
				  }
				  
				  if(!(mapActual.containsValue(value))){
					  Assert.assertTrue(false);
				  }
				}
			
				
	}
	

}
