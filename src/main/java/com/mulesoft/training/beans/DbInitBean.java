package com.mulesoft.training.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class DbInitBean implements InitializingBean {
   
   private static Logger log = Logger.getLogger(DbInitBean.class);
   
   @Override
   public void afterPropertiesSet() throws Exception {
      
      String dbURL = "jdbc:derby:memory:training;create=true";
      Connection conn = null;
      try {
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
         conn = DriverManager.getConnection(dbURL);
         Statement stmt = conn.createStatement();
         log.info("Creating table Giftcards...");
         stmt.executeUpdate("CREATE TABLE Giftcards (number VARCHAR(80), sourceID VARCHAR(80), balance FLOAT, createdOn TIMESTAMP DEFAULT CURRENT_TIMESTAMP  )");
         
         log.info("Populate table GiftCards with sample giftcards...");
         stmt.executeUpdate(
        		  "INSERT INTO Giftcards (number, sourceID, balance) VALUES "
         		+ "('SAMPLE1','MULEBANK-0949', 20.50),"
         		+ "('SAMPLE2','MULEBANK-0949', 100.25),"
         		+ "('SAMPLE3','MULEBANK-0949', 400)" 
         		);
      } 
      catch (java.sql.SQLException sqle) {
         sqle.printStackTrace();
         throw sqle;
      }
   }
}