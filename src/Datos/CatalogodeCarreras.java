/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import Datos.DBConexion_1;
import Entidades.Carrera;
import java.sql.ResultSet;
import java.util.ArrayList;
import static java.lang.System.out;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;

/**
 *
 * @author Lucia
 */
public class CatalogodeCarreras  extends DBConexion_1 {
    
    private ResultSet resu;
    
    
    public Carrera buscarCarrera(int cod_carrera) throws Exception
    {
        try 
        {
            
            this.Conectar();
            PreparedStatement cons= Cone.prepareStatement("SELECT * FROM CARRERA WHERE cod_carrera = ?");
            cons.setInt(1,cod_carrera);
            resu = cons.executeQuery();
            resu.first();
            int codCa = resu.getInt("cod_carrera" );
            String nomCa = resu.getString("nombre");
            String desCa = resu.getString("descripcion");
            Carrera ca = new Carrera(codCa, nomCa, desCa);
            this.Desconectar();
            return ca;
            
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return null;            
        }
    }
    
     public ArrayList<Carrera> buscarCarreras() throws Exception
    {
        try 
        {    this.Conectar();
             ArrayList<Carrera> carreras = new ArrayList<Carrera>();
             PreparedStatement cons= Cone.prepareStatement("SELECT * FROM carrera");
            resu = cons.executeQuery();
            while(resu.next())
               {
                    int codCa = resu.getInt("cod_carrera" );
                    String nomCa = resu.getString("nombre");
                    String desCa = resu.getString("descripcion");
                    Carrera ca = new Carrera(codCa, nomCa, desCa); 
                    carreras.add(ca); 
               }
            this.Desconectar();
            return carreras;
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return null;            
        }
    }
    
     public void agregarCarrera(String nombre, String descripcion) throws Exception
    {
        try 
        {   String sqlu="INSERT INTO carrera (nombre, descripcion) VALUES (?,?)";
            this.Conectar();
            PreparedStatement upd= Cone.prepareStatement(sqlu);
            upd.setString(1, nombre);
            upd.setString(2, descripcion);
            upd.executeUpdate();
            this.Desconectar();
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());            
        }
    }
     
     
}

