package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;

public class Conexao {
    private Connection con = null;
    public static Conexao db;
    
    private Conexao () {
        System.out.println("Conectando ao banco...");
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =  DriverManager.getConnection("jdbc:mysql://localhost/restaurante","root","");
            System.out.println("Conectado.");
        }
    
        catch (ClassNotFoundException ex) {
            System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        catch(SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    
    public static Conexao getConnection(){
        if(db == null){
            db = new Conexao();
        }
        
        return db;
    }
    
    public int query_insert(String table, String fields, String values){
        PreparedStatement stm;

        try {
            String sql = "INSERT INTO "+table+"("+fields+") VALUES ("+values+")";
            System.out.println(sql);
            stm = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);            
            int executeUpdate = stm.executeUpdate();
            
            if( executeUpdate > 0){
                ResultSet keys = stm .getGeneratedKeys();    
                keys.next();  
                return keys.getInt(1);
            }
                
            return 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return 0;        
    }
    
    public boolean query_update(String table, String set){
        return query_update(table,set,"1");
    }
    
    public boolean query_update(String table, String set, String where){
        Statement stm;
        
        try {
            stm = this.con.createStatement();
            String sql;
            sql = "UPDATE "+table+" SET "+set+" WHERE "+where;
            int executeUpdate = stm.executeUpdate(sql);
            return executeUpdate > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return false;        
    }
    
    public boolean query_delete(String table, String where){
        Statement stm;
        
        try {
            stm = this.con.createStatement();
            String sql;
            sql = "DELETE FROM "+table+" WHERE "+where;
            int executeUpdate = stm.executeUpdate(sql);
            return executeUpdate > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return false;        
    }
    
    public ResultSet query(String sql){
        ResultSet executeQuery = null;
        Statement stm;
        
        try {
            stm = this.con.createStatement();
            executeQuery = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return executeQuery;
    }
}