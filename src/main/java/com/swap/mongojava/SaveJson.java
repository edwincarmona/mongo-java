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
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author Edwin Carmona
 */
public class SaveJson {
    
    public void printCollection(MongoDatabase database, String sCollection) {
        MongoCollection collection = database.getCollection(sCollection);
        
        FindIterable<Document> fi = collection.find();
        
        try (MongoCursor<Document> cursor = fi.iterator()) {
            while(cursor.hasNext()) {				
                System.out.println(cursor.next().toJson());
            }
        }
    }
    
    public void process() {
        MongoConn con = new MongoConn();
        MongoDatabase database = con.getDatabase();
        SaveThings st = new SaveThings();
        
        String xmlPath = "";
        try {
            JSONObject jsonObj = st.readXml(xmlPath);
            ObjectId objId = st.saveJson(database, "cfdis", jsonObj);
            
            System.out.println(objId.toString());
            
            this.printCollection(database, "cfdis");
        }
        catch (IOException ex) {
            Logger.getLogger(SaveJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
