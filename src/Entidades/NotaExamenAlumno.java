/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Lucia
 */
public class NotaExamenAlumno {

    public NotaExamenAlumno(float nota, String condicion, Alumno alumno) {
        this.nota = nota;
        this.condicion = condicion;
        this.alumno = alumno;
        
    }
    
    private float nota;
    private String condicion;
    private Alumno alumno;
   

	public float getNota() {
        return nota;
    }

    public String getCondicion() {
        return condicion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

	public void setNota(float nota) {
		this.nota = nota;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
    
    
    
}
