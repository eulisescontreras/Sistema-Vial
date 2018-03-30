
package proyecto;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


public class Conexion {
    
    
	public static Connection hacerConexion(String user,String pass) {
		String url="jdbc:postgresql://localhost:5432/auxiliovial20";
		Connection conexion;
		
		try{
			Class.forName("org.postgresql.Driver");
		}catch (ClassNotFoundException e1){
			System.out.println("Error 1:"+e1.toString());
			JOptionPane.showMessageDialog(null, "Error1: "+e1);
            return null;
		}
		       
	     try{
	         conexion=DriverManager.getConnection(url,"postgres","23795053");
	     }catch(SQLException e){
	         System.out.println("Error 2:"+e.toString());
	         JOptionPane.showMessageDialog(null, "El Usuario: "+user+" No es correcto intente de nuevo.");
	         return null;
	      }
		return conexion;
	}
	
        //para consultas en sql con executeQuery
	public static ResultSet obtenerResulset(String sql,Connection conexion){
		ResultSet resultado=null;
		Statement buscar;
		
		try{
    
            buscar=conexion.createStatement(resultado.TYPE_SCROLL_INSENSITIVE,resultado.CONCUR_UPDATABLE);
            resultado=buscar.executeQuery(sql);
        }catch(SQLException e){
            System.out.println("Error Consulta:"+e.toString());
            return null;
        }
		return resultado;
	}
	
        //para eliminaciones, inserciones,etc en la base de datos
	public static int ejecutarSQL(String sql,Connection conexion){
		Statement buscar;
                
                System.out.print(sql);
		try{
			buscar=conexion.createStatement();
			return buscar.executeUpdate(sql);
	     }catch(SQLException e){
	         System.out.println("Error sql:"+e.toString());
	         JOptionPane.showMessageDialog(null, "Error sql: "+e);
                 return 0;
             }
	}
}
