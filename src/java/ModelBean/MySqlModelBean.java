/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelBean;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Rastaldi
 */
@ManagedBean
@SessionScoped
public class MySqlModelBean {
    
    @Resource(name="java:/MySqlDS")
	private DataSource ds;
 
	
    /**
     * Creates a new instance of MySqlModelBean
     */
    public MySqlModelBean() {
        
    }
    
    public static void conexionMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("El driver para conectar java con Mysql no esta instalado");
		e.printStackTrace();
        }
        Connection connection = null;
 
	try {
		connection = DriverManager
		.getConnection("jdbc:mysql://localhost:3306/manage_database","root", "");
 
	} catch (SQLException e) {
		System.out.println("Connection MySql Failed!");
		e.printStackTrace();
		return;
	}
        }
    
    public void createTableMySql(String nameNewTable, String nameFila, String tipoDato, List tipo) throws SQLException{
        
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/portal_daw", "root", "");
        java.sql.Statement st = conexion.createStatement();
        st.executeUpdate("CREATE TABLE " + nameNewTable + " ("+ nameFila + " "+ tipoDato + " " + tipo + ")" );
    }
    
    public void deleteTableMySql(String nameTable) throws SQLException{
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/portal_daw", "root", "");
        java.sql.Statement st = conexion.createStatement();
        st.executeUpdate("DROP TABLE " + nameTable + " ");
    }
    
    public void insertTableMySql(String nameTable, String nameFila, String valorInsert) throws SQLException{
         Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/portal_daw", "root", "");
        java.sql.Statement st = conexion.createStatement();
        st.executeUpdate("INSERT INTO " + nameTable + " (" + nameFila + ") VALUES (" + valorInsert + ")");
    }

}
