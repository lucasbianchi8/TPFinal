package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entidades.Examen;
import Entidades.Usuario;

public class CatalogodeUsuarios extends DBConexion_1 {
    private ResultSet resu;

	public Usuario getUsuario(String usu, String pass) throws Exception{
		    {     Usuario u= new Usuario();
		        try 
		        {   
		        	this.Conectar();
		            String vsql="SELECT * FROM usuario where nombre_usuario=? and clave =?";
		            PreparedStatement consulta= Cone.prepareStatement(vsql);
		            consulta.setString(1, usu);
		            consulta.setString(2, pass);
		            resu = consulta.executeQuery();
		            while(resu.next())
		            {
		            int tipo_usuario = resu.getInt("tipo_usuario" );
		            int codigo_profesor = resu.getInt("codigo_profesor");
		            int dni = resu.getInt("dni");
		            u.setNombre_usuario(usu);
		            u.setClave(pass);
		            u.setTipo_Usuario(tipo_usuario);
		            u.setCodigo_profesor(codigo_profesor);
		            u.setDni(dni);
		            }
		            this.Desconectar();
		            
		            return u;
		        }
		        catch (Exception ex)
		        {
		            System.err.println("SQLException: " + ex.getMessage());
		            return null;            
		        }
		       
		   
	}

}
}
