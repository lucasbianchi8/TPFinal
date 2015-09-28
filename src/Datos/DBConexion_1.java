/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.*;
/**
 *
 * @author Lucas
 */
public class DBConexion_1 {
	String connectionUrl="jdbc:mysql://localhost:3306/cossettini";
	String connectionDriver="com.mysql.jdbc.Driver";
    String connectionUser = "root";
	String connectionPassword = "lucas";
    protected Statement stmt;
    Connection Cone = null;

  
      public void Conectar()
        {
    	try {
    			Class.forName("com.mysql.jdbc.Driver").newInstance();
    			Cone = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
				
			
		}
    	catch( Exception e)
		{
			e.printStackTrace();
		} 
		}
		
	    
        
    
   
    
   // private String getConnectUrl()
    //{
        //return url;
    //}
    
    public void Desconectar () throws SQLException
    {
     try{  
        Cone.close();
    }catch( Exception e)
	{
		e.printStackTrace();
	}
    }
   public Statement getStmt()
    {
        return this.stmt;
    }
}