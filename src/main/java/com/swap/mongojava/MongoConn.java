/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swap.mongojava;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Edwin Carmona
 */
public class MongoConn {
   
    /**
     * returns the database with the parameters specified
     * 
     * @return 
     */
    public MongoDatabase getDatabase() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://root:msroot@192.168.99.100:27017"));
        MongoDatabase database = mongoClient.getDatabase("testdb");
        
        return database;
    }
    
}
