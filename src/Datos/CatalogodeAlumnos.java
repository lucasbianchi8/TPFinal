/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.Alumno;
import Entidades.NotaExamenAlumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Lucia
 */
public class CatalogodeAlumnos extends DBConexion_1{
    
    private ResultSet resu;
    
    public ArrayList<Alumno> buscarAlumnos() throws Exception
    {	
        try 
        {
        	this.Conectar();
        	ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        	PreparedStatement consulta= Cone.prepareStatement("SELECT * FROM alumno WHERE ingreso_directo=?");
        	consulta.setString(1, "NO");
             resu = consulta.executeQuery();
             while(resu.next())
               {
                    int dniAl = resu.getInt("dni" );
                    String nomAl = resu.getString("nombre");
                    String apeAl = resu.getString("apellido");
                    String mailAl = resu.getString("mail");
                    String ingdiAl = resu.getString("ingreso_directo");
                    String tuelAl = resu.getString("turno_eleccion");
                    String nombreC= getNombreCarrera(resu.getInt("cod_carrera"));
                    
                    Alumno al = new Alumno(dniAl, nomAl, apeAl, mailAl, tuelAl, ingdiAl,nombreC); 
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
    
    private String getNombreCarrera(int int1) {
	  if(int1==1)
	  {
		return "Traductorado de ingles";
	  }
		else return "Profesorado de ingles";
	}

	public int agregarAlumnos(ArrayList<Alumno> alumnos) throws Exception
    {
        try 
        {
        	this.Conectar();
            String insert="INSERT INTO alumno(dni, nombre, apellido, mail, ingreso_directo, turno_eleccion, cod_carrera) "+" VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ins= Cone.prepareStatement(insert);
            for (int i =0; i<alumnos.size(); i++)
            {
            int dni = alumnos.get(i).getDni();
            String nombre = alumnos.get(i).getNombre();
            String apellido = alumnos.get(i).getApellido();
            String mail = alumnos.get(i).getMail();
            String ingDire = alumnos.get(i).getIngreso_directo();
            String turnElec = alumnos.get(i).getTurno_eleccion();
            int cod_carrera= getCodigoCarrera(alumnos.get(i).getNombre_Carrera());
           
            ins.setInt(1,dni);
            ins.setString(2,nombre);
            ins.setString(3,apellido);
            ins.setString(4,mail);
            ins.setString(5,ingDire);
            ins.setString(6,turnElec);
            ins.setInt(7,cod_carrera);
            ins.executeUpdate();
            PreparedStatement ins2= Cone.prepareStatement("INSERT INTO alumno_en_carrera(cod_carrera,dni)VALUES(?,?)");
            ins2.setInt(1,cod_carrera);
            ins2.setInt(2, dni);
            ins2.executeUpdate();
            
            
             }
         
            this.Desconectar();
            return 1;
            
        }
        catch (Exception ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            return 2;  
        }
       
    }

	private int getCodigoCarrera(String nombre_Carrera) {
		int cod=0;
		if(nombre_Carrera.equals("Traductorado de ingles")) cod=1;
		else cod=2;
		return cod;
	}
    
}
