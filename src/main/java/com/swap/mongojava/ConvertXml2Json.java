/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swap.mongojava;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
 
import org.json.JSONObject;
import org.json.XML;
 
public class ConvertXml2Json {
	public static void main(String[] args) throws IOException {
		
            // https://grokonez.com/java/java-convert-json-xml
            // 1. Convert XML String -> Json String
            final int PRETTY_PRINT_INDENT_FACTOR = 4;
//		
//		String xmlString = 	"<Customer>" + 
//				"  <name>Mary</name>" + 
//				"  <age>37</age>" + 
//				"  <address>" + 
//				"    <street>NANTERRE CT</street>" + 
//				"    <postcode>77471</postcode>" + 
//				"  </address>" + 
//				"</Customer>";
//		
//		JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
//		String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
//		
//		System.out.println(jsonPrettyPrintString);
		/*
			{"Customer": {
			    "address": {
			        "street": "NANTERRE CT",
			        "postcode": 77471
			    },
			    "name": "Mary",
			    "age": 37
			}}
		 */
        
            // 2. Convert XML File -> Json File
            String xmlFile = System.getProperty("user.dir") + "\\file.xml";
		
            String xmlString = new String(Files.readAllBytes(Paths.get(xmlFile)));
            JSONObject xmlJSONObj = XML.toJSONObject(xmlString);

            String jsonFile = System.getProperty("user.dir") + "\\file.json";

            try (FileWriter fileWriter = new FileWriter(jsonFile)){
                    fileWriter.write(xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR));
            }
	}
}
