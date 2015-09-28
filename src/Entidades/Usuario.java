package Entidades;

public class Usuario {

	private String nombre_usuario;
	private String clave;
	private int tipo_Usuario;
	private int codigo_profesor;
	private int dni;
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getTipo_Usuario() {
		return tipo_Usuario;
	}
	public void setTipo_Usuario(int tipo_Usuario) {
		this.tipo_Usuario = tipo_Usuario;
	}
	public int getCodigo_profesor() {
		return codigo_profesor;
	}
	public void setCodigo_profesor(int codigo_profesor) {
		this.codigo_profesor = codigo_profesor;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public Usuario(String nombre_usuario, String clave, int tipo_Usuario) 
	{
		this.nombre_usuario = nombre_usuario;
		this.clave = clave;
		this.tipo_Usuario = tipo_Usuario;
	
	}
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	
	
	
}
