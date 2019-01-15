/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swap.mongojava;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
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
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Edwin Carmona
 */
public class MongoConn {
    
    public void connect() throws FileNotFoundException, IOException {
//        String user = "root";        // the user name
//        String database = "testdb";    // the name of the database in which the user is defined
//        char[] password = "msroot".toCharArray();    // the password as a character array
//        // ...
//        MongoCredential credential = MongoCredential.createCredential(user,
//                                                                      database,
//                                                                      password);
//        credential.
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://root:msroot@192.168.99.100:27017"));
        MongoDatabase database = mongoClient.getDatabase("testdb");
        GridFSBucket gridFSBucket = GridFSBuckets.create(database);
        
        // Get the input stream
//        InputStream streamToUploadFrom = new FileInputStream(new File("C:\\Users\\Sergio Flores\\Documents\\AAA010101AAA_I_A_0000003170.xml"));
//
//        // Create some custom options
//        GridFSUploadOptions options = new GridFSUploadOptions()
//                                            .chunkSizeBytes(1024)
//                                            .metadata(new Document("type", "presentation"));
//
//        ObjectId fileId = gridFSBucket.uploadFromStream("mongodb-tutorial", streamToUploadFrom, options);
        ObjectId fileId = new ObjectId("5c38f3218de2b554c86359cf");
        
        FileOutputStream streamToDownloadTo = new FileOutputStream("C:\\Users\\Sergio Flores\\Documents\\AAA010101AAA_I_A_0000003170__1_2.xml");
        
        gridFSBucket.downloadToStream(fileId, streamToDownloadTo);
        streamToDownloadTo.close();
        System.out.println(streamToDownloadTo.toString());
        System.out.println(fileId.toString());
    }
    
}
