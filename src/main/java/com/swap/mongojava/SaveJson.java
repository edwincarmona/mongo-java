/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swap.mongojava;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author Edwin Carmona
 */
public class SaveJson {
    
    public void saveJson(JSONObject jsonObj) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://root:msroot@192.168.99.100:27017"));
        MongoDatabase database = mongoClient.getDatabase("testdb");
        
        MongoCollection collection = database.getCollection("cfdis");
        
        Document doc = Document.parse( jsonObj.toString() );
        
        collection.insertOne(doc);
    }
    
    public void printCollection(MongoCollection collection) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://root:msroot@192.168.99.100:27017"));
        MongoDatabase database = mongoClient.getDatabase("testdb");
        
        collection = database.getCollection("cfdis");
        
        FindIterable<Document> fi = collection.find();
        
        try (MongoCursor<Document> cursor = fi.iterator()) {
            while(cursor.hasNext()) {				
                System.out.println(cursor.next().toJson());
            }
        }
    }
    
    public JSONObject read() throws IOException {
        String xmlFile = System.getProperty("user.dir") + "\\file.xml";
		
        String xmlString = new String(Files.readAllBytes(Paths.get(xmlFile)), StandardCharsets.UTF_8);
        JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
        
        return xmlJSONObj;
    }
    
    public void process() {
        try {
            
            JSONObject obj = this.read();
            this.saveJson(obj);
            this.printCollection(null);
            
        } catch (IOException ex) {
            Logger.getLogger(SaveJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
