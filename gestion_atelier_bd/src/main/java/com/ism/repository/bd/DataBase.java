package com.ism.repository.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DataBase {
    void connection();
     void  closeConnection(); 
     ResultSet executeSelect(String sql);
     int executeUpdate(String sql);
     PreparedStatement getPs();
     void initPreparedStatement(String sql);

    
}
