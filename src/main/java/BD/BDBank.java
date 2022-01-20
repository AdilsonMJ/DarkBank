package BD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public abstract class BDBank {

    //Criando conexao

//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appipoor", "root", "123456789");

    private static Connection connection = null;
    public static Connection getConnection() {
        try {
            if (connection == null) {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return connection;
    }

    //Recuperar dados de acesso do arquivo properties.
    private static Properties loadProperties(){
        try(FileInputStream fileInputStream = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e){
            throw  new DbException(e.getMessage());
        }
    }

    public static void closer(Statement stm){
        try {
            stm.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }

    }


    public static void closer(Connection connection){
        try {
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public static void closer(ResultSet rst){
        try {
            rst.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public static void closer(ResultSet rst, Connection connection, Statement stm){
        try {
            rst.close();
            stm.close();
            connection.close();
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

}
