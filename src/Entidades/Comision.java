/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;

import Datos.CatalogodeComisiones;

/**
 *
 * @author Lucia
 */
public class Comision {
    
    private int cod_comision;
    private String nombre;
    private String descripcion;
    private Examen examen;
    private ArrayList<Profesor> profes;

    public ArrayList<Profesor> getProfes() {
		return profes;
	}



	public void setProfes(ArrayList<Profesor> profes) {
		this.profes = profes;
	}



	public void setCod_comision(int cod_comision) {
		this.cod_comision = cod_comision;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public void setExamen(Examen examen) {
		this.examen = examen;
	}



	public Comision(int cod_comision,String nombre, String descripcion, Examen examen) {
        this.cod_comision = cod_comision;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.examen = examen;
    }
    
    

    public Comision(int codC, String nombre2, String descripcion2) {
    	this.cod_comision = codC;
        this.nombre = nombre2;
        this.descripcion = descripcion2;
	}



	public Examen getExamen() {
        return examen;
    }

    public int getCod_comision() {
        return cod_comision;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
   /* 
    public void agregarComision ()
    {
        CatalogodeComisiones cd = new CatalogodeComisiones();
        cd.agregarComision(this.getCod_comision(), this.getDescripcion(), this.getNombre(), this.getExamen().getCod_examen());
    }
    */
}
