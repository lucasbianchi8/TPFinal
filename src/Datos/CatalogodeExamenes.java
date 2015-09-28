/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import Datos.DBConexion_1;
import Entidades.Alumno;
import Entidades.Carrera;
import Entidades.Comision;
import Entidades.Ejercicio;
import Entidades.Examen;
import Entidades.Profesor;
import Entidades.AlumnoEnEjercicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static java.lang.System.out;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;

import javax.swing.JOptionPane;

/**
 *
 * @author Lucia
 */


public class CatalogodeExamenes extends DBConexion_1{
    
    private ResultSet resu;
    
    public CatalogodeExamenes()
            {
          
            }
    
    public Examen listarExamen(int anio) throws Exception
    {
        try 
        {   this.Conectar();
            String vsql="SELECT * FROM examen where anio=? and (estado = 'sin generar' or estado='alumnos cargados')";
            PreparedStatement consulta= Cone.prepareStatement(vsql);
            consulta.setInt(1, anio);
            resu = consulta.executeQuery();
            resu.first();
            int codEx = resu.getInt("cod_examen" );
            String tipoEx = resu.getString("tipo_examen");
            int anioEx = resu.getInt("anio");
            String esEx = resu.getString("estado");
            String desEx = resu.getString("descripcion");
            Examen ex = new Examen(codEx,tipoEx, anioEx, esEx, desEx);  
            this.Desconectar();
            return ex;
            
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return null;            
        }
       
    }
    
     public void agregarExamen(Examen e) throws Exception
    {
        try 
        {	this.Conectar();
        	String actu = "INSERT INTO examen (tipo_examen, anio, estado, descripcion) VALUES (?,?,?,?)";
        	PreparedStatement inse = Cone.prepareStatement(actu);
            inse.setString(1, e.getTipo_examen());
            inse.setInt(2, e.getAnio());
            inse.setString(3, e.getEstado());
            inse.setString(4, e.getDescripcion());
        	inse.executeUpdate();
        	String sqlc="SELECT max(cod_examen) as cod FROM examen";
        	PreparedStatement consult= Cone.prepareStatement(sqlc);
        	resu=consult.executeQuery();
        	resu.first();
        	int codi= resu.getInt("cod");
        	if(!e.getTipo_examen().equals("C")){
        		String sql2= "INSERT INTO examen_carrera(cod_examen,cod_carrera) VALUES(?,?)";
        		PreparedStatement inse2=Cone.prepareStatement(sql2);
        		inse2.setInt(1, codi);
        		inse2.setInt(2, 1);
        		inse2.executeUpdate();
        		String sql3= "INSERT INTO examen_carrera(cod_examen,cod_carrera) VALUES(?,?)";
        		PreparedStatement inse3=Cone.prepareStatement(sql3);
        		inse3.setInt(1, codi);
        		inse3.setInt(2, 2);
        		inse3.executeUpdate();
        	}else {
        		String sql2= "INSERT INTO examen_carrera(cod_examen,cod_carrera) VALUES(?,?)";
        		PreparedStatement inse2=Cone.prepareStatement(sql2);
        		inse2.setInt(1, codi);
        		inse2.setInt(2, 2);
        		inse2.executeUpdate();
        		
        		}
            this.Desconectar();
            JOptionPane.showMessageDialog(null, "Se agrego el examen correctamente");
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());            
        }
        
    }
     
     public Examen buscarExamen(String tipo_examen, int anio) throws Exception
    {
        try 
        {	this.Conectar();
            String consult = "SELECT * FROM examen WHERE tipo_examen = ? AND anio=?";
            PreparedStatement consulta= Cone.prepareStatement(consult);
            consulta.setString(1, tipo_examen);
            consulta.setInt(2, anio);
            resu = consulta.executeQuery();
            resu.first();
            int codEx = resu.getInt("cod_examen" );
            String tipoEx = resu.getString("tipo_examen");
            int anioEx = resu.getInt("anio");
            String esEx = resu.getString("estado");
            String desEx = resu.getString("descripcion");
            Examen ex = new Examen(codEx,tipoEx, anioEx, esEx, desEx);   
            this.Desconectar();
            return ex;
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            //JOptionPane.showMessageDialog(null, "No existe tipo de examen para el año seleccionado");
            return null;            
        }
    }
     
      public Examen buscaExamen(int cod_examen) throws Exception
    {
        try 
        {	this.Conectar();
	        PreparedStatement consulta= Cone.prepareStatement("SELECT * FROM examen WHERE cod_examen= ?");
	        consulta.setInt(1, cod_examen);
            resu = consulta.executeQuery();
            resu.first();
            int codEx = resu.getInt("cod_examen" );
            String tipoEx = resu.getString("tipo_examen");
            int anioEx = resu.getInt("anio");
            String esEx = resu.getString("estado");
            String desEx = resu.getString("descripcion");
            Examen ex = new Examen(codEx, tipoEx, anioEx, esEx, desEx);  
            this.Desconectar();
            return ex;
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return null;            
        }
    }
      
      public void cambiarEstado(int cod_examen, String estado) throws Exception
      {
         try
         {    this.Conectar();
         	  PreparedStatement upd = Cone.prepareStatement("UPDATE examen SET estado= ? WHERE cod_examen= ?");
         	  upd.setString(1, estado);
         	  upd.setInt(2, cod_examen);
              upd.executeUpdate();    
              JOptionPane.showMessageDialog(null, "El examen ha sido "+estado, "Informacion", JOptionPane.INFORMATION_MESSAGE);
              this.Desconectar();
         }
         catch (Exception ex)
         {
             System.err.println("SQLException: " + ex.getMessage());
             
         }
        
          
      }

	public ArrayList<Alumno> getAllAlumnos(int cod_examen) {
		// TODO Auto-generated method stub
		 try
		 {
	        	this.Conectar();
	        	ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
	        	String cons="SELECT * FROM alumno_en_examen INNER JOIN alumno ON alumno_en_examen.dni=alumno.dni INNER JOIN carrera ca ON alumno.cod_carrera=ca.cod_carrera WHERE alumno_en_examen.cod_examen = ? ORDER BY alumno.apellido,alumno.nombre";
	        	
	            PreparedStatement consulta= Cone.prepareStatement(cons);
	            consulta.setInt(1, cod_examen);
	             resu = consulta.executeQuery();
	             while(resu.next())
	               {
	                    int dniAl = resu.getInt("alumno.dni" );
	                    String nomAl = resu.getString("alumno.nombre");
	                    String apeAl = resu.getString("alumno.apellido");
	                    String mailAl = resu.getString("alumno.mail");
	                    String ingdiAl = resu.getString("alumno.ingreso_directo");
	                    String tuelAl = resu.getString("alumno.turno_eleccion");
	                    String carrera = resu.getString("ca.nombre");
	                 
	                    Alumno al = new Alumno(dniAl, nomAl, apeAl, mailAl, tuelAl, ingdiAl, carrera); 
	                    alumnos.add(al); 
	                    
	               }
	             this.Desconectar();
	            return alumnos;

	        }
	        catch (Exception ex)
	        {
	            System.err.println("SQLException: " + ex.getMessage());
	            return null;            
	        }
		}
	
	public ArrayList<Examen> buscarExamenes(int cod_profesor) throws Exception
    {
        try 
        {    this.Conectar();
             PreparedStatement consulta= Cone.prepareStatement("select * from profesor_en_comision p INNER JOIN comision c ON c.cod_comision=p.cod_comision INNER JOIN examen e ON e.cod_examen=c.cod_examen WHERE p.cod_profesor=? AND e.estado!=?;");
             consulta.setInt(1, cod_profesor);
             consulta.setString(2, "cerrado");
             ArrayList<Examen> listaExamenes = new ArrayList<Examen>();
             resu = consulta.executeQuery();
             while(resu.next())
               {              
            		int cod_examen = resu.getInt("cod_examen" );
                    String tipo_examen = resu.getString("tipo_examen");
                    int anio = resu.getInt("anio");
                    String estado = resu.getString("estado");
                    String descripcion = resu.getString("descripcion");
                    listaExamenes.add(new Examen(cod_examen, tipo_examen, anio, estado, descripcion,false));
               }
            this.Desconectar();
            return listaExamenes;
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return null;            
        }
    
    }
	
	public ArrayList<Ejercicio> getAllEjercicios(int cod_examen) throws Exception
    {
        try 
        {   this.Conectar();
        	ArrayList<Ejercicio> ejercicios = new ArrayList<Ejercicio>();
        	String lsql = "SELECT * FROM ejercicio WHERE cod_examen=?";
        	PreparedStatement consulta2= Cone.prepareStatement(lsql);
        	consulta2.setInt(1, cod_examen);
        	resu = consulta2.executeQuery();
        	while(resu.next())
             {
        		int cod_ejercicio= resu.getInt("cod_ejercicio");
        		String nombre = resu.getString("nombre");
        		String descripcion = resu.getString("descripcion");
        		int cant_items= resu.getInt("cant_items");
        		int porcentaje= resu.getInt("porcentaje");
        		Ejercicio ej = new Ejercicio(cod_ejercicio, nombre, descripcion, cant_items, porcentaje);
        		ejercicios.add(ej);
             }
        	this.Desconectar();
        	return ejercicios;
        	
            
        }
        
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return null;            
        }
       
    }

	public ArrayList<AlumnoEnEjercicio> recuperarAlumnosEnEjercicio(int cod_ejercicio, int cod_examen) {
		try
		{
		this.Conectar();
		String vsql="SELECT * FROM ejercicio ej INNER JOIN alumno_en_ejercicio_examen alej ON ej.cod_examen=alej.cod_examen AND ej.cod_ejercicio=alej.cod_ejercicio INNER JOIN alumno al ON alej.dni=al.dni where ej.cod_examen=? AND ej.cod_ejercicio=?";
        PreparedStatement consulta= Cone.prepareStatement(vsql);
        consulta.setInt(1, cod_examen);
        consulta.setInt(2, cod_ejercicio);
        ArrayList<AlumnoEnEjercicio> alenej = new ArrayList<AlumnoEnEjercicio>();
        resu = consulta.executeQuery();
        while(resu.next())
        {      
            int dni = resu.getInt("al.dni");
            String apellido = resu.getString("al.apellido");
            String nombre =resu.getString("al.nombre");
            Alumno al = new Alumno(dni, nombre, apellido);
            int resultado = resu.getInt("alej.resultado");
            float notaparcial = resu.getFloat("alej.nota_parcial");
            alenej.add(new AlumnoEnEjercicio(al, resultado, notaparcial));
        }
        this.Desconectar();
		return alenej;
	}catch (Exception ex){
		System.err.println("SQLException: " + ex.getMessage());
        return null; 
		}
}

	public void actualizarNotas(Examen exa) {
		// TODO Auto-generated method stub
		//se actualizan todas las notas por alumno 
		
			
			try 
	        {   this.Conectar();
	        	for (int i =0; i<exa.getListaNotaExamenAlumno().size(); i++)
	            {
	        		
	           	  PreparedStatement upd = Cone.prepareStatement("UPDATE alumno_en_examen SET nota= ? AND estado ? WHERE dni= ? AND cod_examen=?");
	           	  upd.setFloat(1, exa.getListaNotaExamenAlumno().get(i).getNota());
	           	  upd.setString(2, "cargado");
	           	  upd.setInt(3, exa.getListaNotaExamenAlumno().get(i).getAlumno().getDni());
	           	  upd.setInt(4, exa.getCod_examen());
	              upd.executeUpdate();  
	       
	             
	            }
	        	PreparedStatement upd2= Cone.prepareStatement("UPDATE examen SET estado='cerrado' where cod_examen=?");
	        	upd2.setInt(1, exa.getCod_examen());
	        	upd2.executeUpdate();  
	        	JOptionPane.showMessageDialog(null, "Examen cerrado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
	        	this.Desconectar();
		
	        	
	        }
	        catch (Exception ex)
	        {
	            System.err.println("SQLException: " + ex.getMessage());            
	        }
			
		}

	public Comision buscarComision(int cod_examen) {
		 try 
	        {	this.Conectar();
		        PreparedStatement consulta= Cone.prepareStatement("SELECT * FROM examen e INNER JOIN comision com ON com.cod_examen=e.cod_examen WHERE e.cod_examen= ?");
		        consulta.setInt(1, cod_examen);
	            resu = consulta.executeQuery();
	            resu.first();
	            int codC = resu.getInt("com.cod_comision" );
	            String nombre = resu.getString("com.nombre");
	            String descripcion = resu.getString("com.descripcion");
	     
	            Comision com= new Comision(codC,nombre,descripcion);
	         
	           PreparedStatement consulta2=Cone.prepareStatement("SELECT * FROM profesor_en_comision p WHERE p.cod_comision=?");
	           consulta2.setInt(1, codC);
	           resu=consulta2.executeQuery();
	           ArrayList<Profesor> profes= new ArrayList<Profesor>();
	           while(resu.next())
	           {   Profesor p= new Profesor();  
	               int codP = resu.getInt("p.cod_profesor");
	               p.setCod_profesor(codP);
	             profes.add(p);
	           }
	           com.setProfes(profes);
	           this.Desconectar();
	           return com;
	        }
	        catch (Exception ex)
	        {
	            System.err.println("SQLException: " + ex.getMessage());
	            return null;            
	        }
	}
		
		
		
	
}