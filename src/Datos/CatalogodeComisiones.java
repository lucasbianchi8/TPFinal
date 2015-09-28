/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.*;



/**
 *
 * @author Lucia
 */
public class CatalogodeComisiones extends DBConexion_1{
	
	 private ResultSet resu;
    
   public int agregarComision (String nombre, String descripcion, int cod_examen)
   {
	  
       try 
        {
            this.Conectar();
            String insert="INSERT INTO comision (cod_examen, nombre, descripcion)VALUES(?,?,?)";
            PreparedStatement ins= Cone.prepareStatement(insert);
            ins.setInt(1,cod_examen);
            ins.setString(2,nombre);
            ins.setString(3, descripcion);
            ins.executeUpdate();
            this.Desconectar();
        }
        catch (Exception ex)
            {
                System.err.println("SQLException: " + ex.getMessage());
                return 0;
                
                            
            }
        try{
            
            this.Conectar();
            String cons="SELECT MAX(cod_comision) as cod_comision from comision";
            PreparedStatement consul = Cone.prepareStatement(cons);
            resu = consul.executeQuery();
            resu.first();
            int codCo = resu.getInt("cod_comision" );
            this.Desconectar();
            return codCo;
            
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return 0;
            
                        
        }
       
       
   }

public int buscarComision(int cod_examen) {
	    
	    try {
	    	this.Conectar();
		    String consulta="SELECT COUNT(cod_comision) as cantidad FROM comision where cod_examen=?";
		    
			PreparedStatement cons= Cone.prepareStatement(consulta);
			cons.setInt(1, cod_examen);
			resu=cons.executeQuery();
			resu.first();
		    int canti = resu.getInt("cantidad" );
		    return canti;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	    
}
    
}
