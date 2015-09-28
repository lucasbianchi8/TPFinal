/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Datos.CatalogodeExamenes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucia
 */
public class Carrera {
    
    private CatalogodeExamenes exdata;
    private int cod_carrera;
    private String nombre;
    private String descripcion;

    public void setCod_carrera(int cod_carrera) {
        this.cod_carrera = cod_carrera;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
    public Carrera(int cod_carrera){
    	this.cod_carrera=cod_carrera;
    }

    public Carrera(int codCa, String nomCa, String desCa) {
        
        this.setCod_carrera(codCa);
        this.setNombre(nomCa);
        this.setDescripcion(desCa);
    }

    public int getCod_carrera() {
        return cod_carrera;
    }
    public String getNombre(){
    	return nombre;
    }
    
    
    
}