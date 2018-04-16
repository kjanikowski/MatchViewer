package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Mecz;

public class create {
	
	public static final String DB_URL = "jdbc:sqlite:mecze.db";
    private Connection connection;
//    private Statement stat=null;
    
    public create(String s, ArrayList<Mecz> mecz){
    	lacz();
    	createDB();
    	insert(s, mecz);
    	
    }
	
    
    
	void lacz() {
		try {
			connection =  DriverManager.getConnection(DB_URL); 
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	void createDB() {
		
        String mecz = "CREATE TABLE IF NOT EXISTS mecz (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	dr1 string NOT NULL,\n"
                + "	dr2 string NOT NULL,\n"
                + "	wynik1 integer,\n"
                + "	wynik2 integer NOT NULL,\n"
                + "	liga integer NOT NULL,\n"
                + " FOREIGN KEY(liga) REFERENCES liga(id)\n"
                + ");";
        
        
        String liga = "CREATE TABLE IF NOT EXISTS liga (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	nazwa string NOT NULL,\n"
                + " UNIQUE(nazwa)"
                + ");";
		
        
        try {
        	Statement stat = connection.createStatement();
        	stat.execute(mecz);
        	stat.execute(liga);
        }catch(SQLException e){
        	e.getMessage();
        }
		
	}
	

	
	void insert(String s, ArrayList<Mecz> mecz) {
		

		

		
    	String liga = "INSERT OR IGNORE INTO liga(nazwa) VALUES(?)";
    	
    	String obcy = "SELECT id FROM liga WHERE nazwa LIKE \""+ s +"\"";
    	
    	String spotkanie = "INSERT INTO mecz(dr1,dr2,wynik1,wynik2,liga) VALUES(?,?,?,?,?)";
    	
	    ResultSet rs=null;
    	
    	
    	try {
			PreparedStatement pStatement = connection.prepareStatement(liga);
			pStatement.setString(1, s);
			pStatement.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			Statement stat = connection.createStatement();
			rs = stat.executeQuery(obcy);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
    		
			PreparedStatement pStatement = connection.prepareStatement(spotkanie);
			
			for(int i =0 ; i < mecz.size(); i++) {
			
			
			pStatement.setString(1, mecz.get(i).getDr1());
			pStatement.setString(2, mecz.get(i).getDr2());
			pStatement.setInt(3, mecz.get(i).getWynik1());
			pStatement.setInt(4, mecz.get(i).getWynik2());
			pStatement.setInt(5, rs.getInt(1));
			pStatement.executeUpdate();	
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
