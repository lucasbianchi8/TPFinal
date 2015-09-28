/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucia
 */
public class Ejercicio {
    
    private int cod_ejercicio;
    private String nombre;
    private String descripcion;
    private int cant_items;
    private int porcentaje;
    private ArrayList<AlumnoEnEjercicio> listaAlumnos;

    public Ejercicio(String nombre, String descripcion, int cant_items, int porcentaje) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cant_items = cant_items;
        this.porcentaje = porcentaje;
    }
    public Ejercicio(int codigo, String nombre, String descripcion, int cant_items, int porcentaje) {
        this.cod_ejercicio=codigo;
    	this.nombre = nombre;
        this.descripcion = descripcion;
        this.cant_items = cant_items;
        this.porcentaje = porcentaje;
    }
    public Ejercicio(int codigo, String nombre, int cant_items, int porcentaje) {
        this.cod_ejercicio=codigo;
    	this.nombre = nombre;
        this.cant_items = cant_items;
        this.porcentaje = porcentaje;
    }
    

    public Ejercicio() {
		// TODO Auto-generated constructor stub
	}

	public Ejercicio(int cod) {
		this.cod_ejercicio=cod;
	}
	public int getCod_ejercicio() {
        return cod_ejercicio;
    }
	public void setCod_ejercicio(int cod){
		this.cod_ejercicio=cod;
	}

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCant_items() {
        return cant_items;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public ArrayList<AlumnoEnEjercicio> getListaAlumnos() {
        return listaAlumnos;
    }
    
   
  
   public void agregarAlumnos (List<Alumno> alumnos) {
       
   }
   
   public void buscarAlumnosEnEjercicio (int cod_examen, int cod_ejercicio){
        
    }
   
   public void cargarNota (Alumno alumno, int resultado){
        
    }

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public void setCant_items(int cant_items) {
	this.cant_items = cant_items;
}

public void setPorcentaje(int porcentaje) {
	this.porcentaje = porcentaje;
}
public String toString()
	{
		return this.getNombre();
	}
public void setListaAlumnos(ArrayList<AlumnoEnEjercicio> listaAlumnos) {
	this.listaAlumnos = listaAlumnos;
}
public void calcularNotaParcial() {
	for(int i=0; i<this.listaAlumnos.size();++i)
	{
		float nota_parcial = (listaAlumnos.get(i).getResultado()) * this.getPorcentaje() / (this.getCant_items()*10); 
		listaAlumnos.get(i).setNota_parcial(nota_parcial);
	}
	
}
	



    
}
