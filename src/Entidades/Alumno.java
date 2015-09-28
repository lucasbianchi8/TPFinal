/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Lucas
 */
public class Alumno {
    
    private int dni;
    private String nombre;
    private String apellido;
    private String mail;
    private String turno_eleccion;
    private String ingreso_directo;
    private String usuario;
    private String contrasenia;
    private Carrera carrera;
    private String nombre_carrera;
    public Alumno(){}

    public Alumno(int dniAl, String nomAl, String apeAl, String mailAl, String turelAl, String ingdiAl, String nombreC) {
        this.dni = dniAl;
        this.nombre= nomAl;
        this.apellido= apeAl;
        this.mail = mailAl;
        this.ingreso_directo= ingdiAl;
        this.turno_eleccion = turelAl;
        this.nombre_carrera=nombreC;
        
    }
    
    public Alumno(int dniAl, String nomAl, String apeAl) {
        this.dni = dniAl;
        this.nombre= nomAl;
        this.apellido= apeAl;       
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public String getTurno_eleccion() {
        return turno_eleccion;
    }

    public String getIngreso_directo() {
        return ingreso_directo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTurno_eleccion(String turno_eleccion) {
        this.turno_eleccion = turno_eleccion;
    }

    public void setIngreso_directo(String ingreso_directo) {
        this.ingreso_directo = ingreso_directo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Alumno(int dni, String nombre, String apellido, String mail, String turno_eleccion, String ingreso_directo, String usuario, String contrasenia) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.turno_eleccion = turno_eleccion;
        this.ingreso_directo = ingreso_directo;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }
    
    public Alumno(int dniAl, String nomAl, String apeAl, String mailAl,
			String tuelAl, String ingdiAl) {
    	this.dni = dniAl;
        this.nombre= nomAl;
        this.apellido= apeAl;
        this.mail = mailAl;
        this.ingreso_directo= ingdiAl;
        this.turno_eleccion = tuelAl;
	}

	public String toString()
	{
		return this.getApellido() +" "+  this.getNombre();
	}

	public void setNombre_Carrera(String string) {
		this.nombre_carrera=string;
		
	}
    public String getNombre_Carrera(){
    	return this.nombre_carrera;
    }
    
}
