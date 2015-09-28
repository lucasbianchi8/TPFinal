/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.Profesor;
import Entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Lucia
 */
public class CatalogodeProfesores  extends DBConexion_1 {
    private ResultSet resu;
    
    public void agregaraComision(ArrayList<Profesor> profes, int cod_comision) throws Exception
    {
        try 
        {   this.Conectar();
        	for (int i =0; i<profes.size(); i++)
            {
        		PreparedStatement insert= Cone.prepareStatement("INSERT INTO profesor_en_comision(cod_profesor,cod_comision) VALUES(?,?)");
        	    int codPr= profes.get(i).getCod_profesor();
        		insert.setInt(1, codPr);
        		insert.setInt(2, cod_comision);
                insert.executeUpdate();
               }
            
            JOptionPane.showMessageDialog(null, "Comisión creada satisfactoriamente!!!");
            this.Desconectar(); 
        	
        	
        	
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());            
        }
    }
    
    
     public ArrayList<Profesor> getAllProfesores() throws Exception
    {
        try 
        {    this.Conectar();
             PreparedStatement consulta= Cone.prepareStatement("SELECT * FROM profesor ORDER BY cod_profesor");
             ArrayList<Profesor> profes = new ArrayList<Profesor>();
            
            resu = consulta.executeQuery();
            while(resu.next())
               {
                    
                    int cod_profesor = resu.getInt("cod_profesor" );
                    String nomPr = resu.getString("nombre");
                    String apePr = resu.getString("apellido");
                    Profesor p = new Profesor(cod_profesor,nomPr,apePr);
                    p.setBandera(false);
                    profes.add(p);
                    
                    
                    
               }
            this.Desconectar();
            return profes;
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return null;            
        }
        finally{this.Desconectar();}
    }
     public void agregarProfesor(String nombre, String apellido, String fec_Nac, Usuario u) throws Exception
     {
         try 
         {	 this.Conectar();
             String insert="INSERT INTO profesor(nombre,apellido,fecha_Nac) VALUES(?,?,?)";
             PreparedStatement ins= Cone.prepareStatement(insert);
             ins.setString(1, nombre);
             ins.setString(2, apellido);
             ins.setString(3,fec_Nac);
             ins.executeUpdate();
             
             String sqlc="SELECT max(cod_profesor) as cod FROM profesor";
         	 PreparedStatement consult= Cone.prepareStatement(sqlc);
         	 resu=consult.executeQuery();
         	 resu.first();
         	 int codi= resu.getInt("cod");
             
             String insert2= "INSERT INTO usuario(nombre_usuario, clave, tipo_usuario, codigo_profesor) VALUES (?,?,?,?)";
             PreparedStatement inse = Cone.prepareStatement(insert2);
             inse.setString(1, u.getNombre_usuario());
             inse.setString(2, u.getClave());
             inse.setInt(3, u.getTipo_Usuario());
             inse.setInt(4, codi);
             inse.executeUpdate();
             JOptionPane.showMessageDialog(null,"Se agregó correctamente");
             this.Desconectar();
         }
         catch (Exception ex)
         {
             System.err.println("SQLException: " + ex.getMessage()); 
             JOptionPane.showMessageDialog(null, "Ya existe un profesor con dicho usuario!");
         }
         
         
     }


	public void deleteProfesor_Comsion(int cod_comision) {
		
		  try 
	        {    this.Conectar();
	             PreparedStatement dele= Cone.prepareStatement("DELETE FROM profesor_en_comision WHERE profesor_en_comision.cod_comision=?");
	             dele.setInt(1, cod_comision);
	             int rta= dele.executeUpdate();	         
	            this.Desconectar();
	       
	        }
	        catch (Exception ex)
	        {
	            System.err.println("SQLException: " + ex.getMessage());
	           
	        }
		
	}
}
