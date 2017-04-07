package net.bechtelus.common;


import java.sql.Connection; 
import java.sql.SQLException; 
import java.util.Properties; 

public interface ConnectionProvider { 

   String PROP_DB_HOST = "loalhost.HS_Dashboard.database.host"; 
   String PROP_DB_PORT = "loalhost.HS_Dashboard.database.port"; 
   String PROP_DB_NAME = "loalhost.HS_Dashboard.database.db"; 
   String PROP_DB_USER = "loalhost.HS_Dashboard.database.user"; 
   String PROP_DB_PASSWORD = "loalhost.HS_Dashboard.database.password"; 

   Connection provide(Properties properties) throws SQLException; 
}
