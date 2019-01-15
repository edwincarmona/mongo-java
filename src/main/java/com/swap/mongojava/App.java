package com.swap.mongojava;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        MongoConn con = new MongoConn();
        SaveJson s = new SaveJson();
        //            con.connect();
        s.process();
    }
}
