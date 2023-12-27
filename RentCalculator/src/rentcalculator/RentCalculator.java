/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rentcalculator;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author pesanth
 */
public class RentCalculator {

    /**
     * @param args the command line arguments
     */
    public Connection getConnection(){
        // TODO code application logic here
        
        try{
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:./database/rentCalc", "admin","");
            System.out.println("Connected to Database");
            return connection;

//          table name: tenants

        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Database Connection Error: \n" + ex);
            return null;
        }
    }
    
}
