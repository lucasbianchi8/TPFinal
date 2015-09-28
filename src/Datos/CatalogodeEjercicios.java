/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.Alumno;
import Entidades.AlumnoEnEjercicio;
import Entidades.Ejercicio;
import Entidades.Profesor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Lucia
 */
public class CatalogodeEjercicios extends DBConexion_1 {
    
    private ResultSet resu;
    
    public void agregarEjercicios(ArrayList<Ejercicio> ejercicios, int cod_examen) throws Exception
    {
        try 
        {   this.Conectar();
        	for (int i =0; i<ejercicios.size(); i++)
            {
        		PreparedStatement insert= Cone.prepareStatement("INSERT INTO ejercicio(cod_examen, nombre, descripcion, cant_items, porcentaje) VALUES(?,?,?,?,?)");
        		insert.setInt(1, cod_examen);
        		insert.setString(2, ejercicios.get(i).getNombre());
        		insert.setString(3, ejercicios.get(i).getDescripcion());
        		insert.setInt(4, ejercicios.get(i).getCant_items());
        		insert.setInt(5, ejercicios.get(i).getPorcentaje());
                insert.executeUpdate();
               }
            
            JOptionPane.showMessageDialog(null, "Ejercicios cargados correctamente!");
            this.Desconectar(); 
        	
        	
        	
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());            
        }
    }

	public int buscarEjercicio(int cod_examen) {
		   
	    try {
	    	this.Conectar();
		    String consulta="SELECT COUNT(cod_ejercicio) as cantidad FROM ejercicio where cod_examen=?";
		    
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

	public void agregarAlumnos(int cod_examen,ArrayList<Alumno> alums,ArrayList<Ejercicio> ejs) {
		try 
        {   this.Conectar();
        	for (int i =0; i<ejs.size(); i++)
            {
        		for(int j=0;j<alums.size();j++)
        		{
        		PreparedStatement insert= Cone.prepareStatement("INSERT INTO alumno_en_ejercicio_examen(dni,cod_examen,cod_ejercicio,resultado,nota_parcial) VALUES(?,?,?,?,?)");
        		insert.setInt(1, alums.get(j).getDni());
        		insert.setInt(2, cod_examen);
        		insert.setInt(3, ejs.get(i).getCod_ejercicio());
        		insert.setFloat(4, 0);
        		insert.setFloat(5, 0);
                insert.executeUpdate();
               }
            }
        	
           // JOptionPane.showMessageDialog(null, "Ejercicios cargados correctamente!");
            this.Desconectar(); 
        	
        	
        	
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());            
        }
    
		
	}

	public ArrayList<Ejercicio> getAllEjercicios(int cod_examen) {
		  
		        try{
		        	
		        	this.Conectar();
		        	ArrayList<Ejercicio> ejs = new ArrayList<Ejercicio>();
		            PreparedStatement consulta= Cone.prepareStatement("SELECT * FROM ejercicio where cod_examen=?");
		            consulta.setInt(1, cod_examen);
		             resu = consulta.executeQuery();
		             while(resu.next())
		               {
		                    //int c1 = resu.getInt("cod_examen" );
		                    int c2 = resu.getInt("cod_ejercicio");
		                    String c3 = resu.getString("nombre");
		                    String c4 = resu.getString("descripcion");
		                    int c5 = resu.getInt("cant_items");
		                    int c6 = resu.getInt("porcentaje");
		                    Ejercicio ej = new Ejercicio(c2,c3,c4,c5,c6); 
		                    ejs.add(ej); 
		                    
		               }
		             this.Desconectar();
		            return ejs;

		        }
		        catch (Exception ex)
		        {
		            System.err.println("SQLException: " + ex.getMessage());
		            return null;            
		        }
		      
	}

	public void cargarNotas(Ejercicio e) {
		
		try 
        {   this.Conectar();
        	for (int i =0; i<e.getListaAlumnos().size(); i++)
            {
        		
           	  PreparedStatement upd = Cone.prepareStatement("UPDATE alumno_en_ejercicio_examen SET resultado= ? , nota_parcial=? WHERE dni= ? AND cod_ejercicio=?");
           	  upd.setInt(1, e.getListaAlumnos().get(i).getResultado());
           	  upd.setFloat(2, e.getListaAlumnos().get(i).getNota_parcial());
           	  upd.setInt(3, e.getListaAlumnos().get(i).getAlumno().getDni());
           	  upd.setInt(4, e.getCod_ejercicio());
              upd.executeUpdate();  
       
             
            }
        	
        	JOptionPane.showMessageDialog(null, "Las notas para "+e.getNombre()+" han sido cargadas.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        	this.Desconectar();
	
        	
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());            
        }
		
	}
	



	public ArrayList<AlumnoEnEjercicio> getAlumnosenEjercicio(int cod, Alumno al) {
		  
        try{
        	
        	this.Conectar();
        	ArrayList<AlumnoEnEjercicio> alej = new ArrayList<AlumnoEnEjercicio>();
            PreparedStatement consulta= Cone.prepareStatement("SELECT * FROM alumno_en_ejercicio_examen INNER JOIN ejercicio on ejercicio.cod_ejercicio=alumno_en_ejercicio_examen.cod_ejercicio AND alumno_en_ejercicio_examen.cod_examen=ejercicio.cod_examen where alumno_en_ejercicio_examen.cod_examen=? AND alumno_en_ejercicio_examen.dni=?");
            consulta.setInt(1, cod);
            consulta.setInt(2, al.getDni());
            resu = consulta.executeQuery();
             while(resu.next())
               {
                    //int c1 = resu.getInt("cod_examen" );
            	 	
                    int codig = resu.getInt("cod_ejercicio");
                    //int codex= resu.getInt("cod")
                    int c2 = resu.getInt("resultado");
                    float c3 = resu.getFloat("nota_parcial");
                     int cant_items = resu.getInt("ejercicio.cant_items");
                     int porcentaje = resu.getInt("ejercicio.porcentaje");
                     String nombre= resu.getString("ejercicio.nombre");
                     
                    
                   alej.add(new AlumnoEnEjercicio(new Ejercicio(codig,nombre,cant_items,porcentaje),c2,c3));
                   
               }
             this.Desconectar();
            return alej;

        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return null;            
        }
      
	}

	public void agregarNotasEnEjercicio(ArrayList<AlumnoEnEjercicio> alen,float nota, int cod) {
		try 
        {   this.Conectar();
        	for (int i =0; i<alen.size(); i++)
            {
        		
           	  PreparedStatement upd = Cone.prepareStatement("UPDATE alumno_en_ejercicio_examen SET resultado= ? , nota_parcial=? WHERE dni= ? AND cod_ejercicio=?");
           	  upd.setInt(1, alen.get(i).getResultado());
           	  upd.setFloat(2, alen.get(i).getNota_parcial());
           	  upd.setInt(3, alen.get(i).getAlumno().getDni());
           	  upd.setInt(4, alen.get(i).getEjer().getCod_ejercicio());
              upd.executeUpdate();    
             
            }
        	
        	PreparedStatement upd2= Cone.prepareStatement("UPDATE alumno_en_examen SET nota=? where dni=? and cod_examen=?");
        	upd2.setFloat(1,nota);
        	upd2.setInt(2, alen.get(0).getAlumno().getDni());
        	upd2.setInt(3, cod);
        	upd2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Las notas para "+alen.get(1).getAlumno().getNombre()+" han sido cargadas.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        	this.Desconectar();
	
        	
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());            
        }
		
	}
    
	public ArrayList<AlumnoEnEjercicio> getAlumnosEnEjercicios(int cod) {
		  
        try{
        	
        	this.Conectar();
        	ArrayList<AlumnoEnEjercicio> alej = new ArrayList<AlumnoEnEjercicio>();
            PreparedStatement consulta= Cone.prepareStatement("SELECT * FROM alumno_en_ejercicio_examen alej INNER JOIN alumno on alumno.dni=alej.dni INNER JOIN carrera on carrera.cod_carrera=alumno.cod_carrera where alej.cod_examen=? ");
            consulta.setInt(1, cod);
            
            resu = consulta.executeQuery();
             while(resu.next())
               {
                    //int c1 = resu.getInt("cod_examen" );
            	 	
                    int codig = resu.getInt("cod_ejercicio");
                    //int codex= resu.getInt("cod")
                    float notaParcial = resu.getFloat("nota_parcial");
                    int dni=resu.getInt("alej.dni");
                    String nombre=resu.getString("alumno.nombre");
                    String apellido=resu.getString("alumno.apellido");
                    String nombreCarrera=resu.getString("carrera.nombre");
                    Alumno al= new Alumno(dni,nombre,apellido,"","","",nombreCarrera);
                    
                   alej.add(new AlumnoEnEjercicio(al,0,notaParcial));
                   
               }
             this.Desconectar();
            return alej;

        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return null;            
        }
      
	}
    
    
}
