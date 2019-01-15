/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swap.mongojava;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author Edwin Carmona
 */
public class SaveThings {
    
    public ObjectId saveFile(MongoDatabase database, String path, String fileName) throws FileNotFoundException {
        GridFSBucket gridFSBucket = GridFSBuckets.create(database, "mfiles");
        
        // Get the input stream
        // InputStream streamToUploadFrom = new FileInputStream(new File("C:\\Users\\Sergio Flores\\Documents\\AAA010101AAA_I_A_0000003170.xml"));
        InputStream streamToUploadFrom = new FileInputStream(new File(path));

        // Create some custom options
        GridFSUploadOptions options = new GridFSUploadOptions()
                                            .chunkSizeBytes(1024)
                                            .metadata(new Document("type", "presentation"));

        ObjectId fileId = gridFSBucket.uploadFromStream(fileName, streamToUploadFrom, options);
        
        return fileId;
    }
    
    public FileOutputStream getFile(MongoDatabase database, String objectId, String pathToDownload) throws FileNotFoundException, IOException {
        ObjectId fileId = new ObjectId(objectId);
        GridFSBucket gridFSBucket = GridFSBuckets.create(database);
        FileOutputStream streamToDownloadTo = new FileOutputStream(pathToDownload);
        
        gridFSBucket.downloadToStream(fileId, streamToDownloadTo);
        streamToDownloadTo.close();
        
        return streamToDownloadTo;
    }
    
    /**
     * Convert xml file to json object
     * 
     * @param pathXml
     * @return
     * @throws IOException 
     */
    public JSONObject readXml(String pathXml) throws IOException {
        // String xmlFile = System.getProperty("user.dir") + "\\file.xml";
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
		
        String xmlString = new String(Files.readAllBytes(Paths.get(pathXml)), StandardCharsets.UTF_8);
        JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
        
        return xmlJSONObj;
    }
    
    /**
     * Receive a json object and save it on database
     * 
     * @param database
     * @param sCollection
     * @param jsonObject
     * @return 
     */
    public ObjectId saveJson(MongoDatabase database, String sCollection, JSONObject jsonObject) {
        Document doc = Document.parse( jsonObject.toString() );
        
        ObjectId id = this.saveDocument(database, sCollection, doc);
        
        return id;
    }
    
    public ObjectId saveDocument(MongoDatabase database, String sCollection, Document doc) {
        MongoCollection collection = database.getCollection(sCollection);
        
        collection.insertOne(doc);
        
        ObjectId id = (ObjectId) doc.get( "_id" );
        
        return id;
    }
}
