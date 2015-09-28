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
public class AlumnoEnEjercicio {
    private Ejercicio ejer;
    public Ejercicio getCod_ejercicio() {
		return ejer;
	}

	public void setCod_ejercicio(Ejercicio ejer) {
		this.ejer = ejer;
	}

	private Alumno alumno;
    private int resultado;
    private float nota_parcial;

    public AlumnoEnEjercicio(Alumno alumno, int resultado, float nota_parcial) {
		
		this.alumno = alumno;
		this.resultado = resultado;
		this.nota_parcial = nota_parcial;
	}
public AlumnoEnEjercicio(Ejercicio ej, int resultado, float nota_parcial) {
		
		this.ejer = ej;
		this.resultado = resultado;
		this.nota_parcial = nota_parcial;
	}
public AlumnoEnEjercicio() {
		

	}

	public float getNota_parcial() {
		return nota_parcial;
	}

	public void setNota_parcial(float nota_parcial) {
		this.nota_parcial = nota_parcial;
	}

	public int getResultado() {
		return resultado;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Alumno getAlumno() {
        return alumno;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

	public Ejercicio getEjer() {
		return ejer;
	}

	public void setEjer(Ejercicio ejer) {
		this.ejer = ejer;
	}

}
