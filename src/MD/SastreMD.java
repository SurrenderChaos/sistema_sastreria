/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MD;

import DP.SastreDP;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author Alienware
 */
public class SastreMD {
    private SastreDP sdp;
    private java.sql.Connection con;
    private java.sql.ResultSet rs;

    public SastreMD(SastreDP sdp) {
        this.sdp = sdp;
    }
    
    public void conectarDB(){
        InputStream input;
        
        try {
            input = new FileInputStream("configuracion.properties");
            Properties prop = new Properties();
            prop.load(input);
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa!"); 
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
	
    public void desconectarDB(){
        try
        {
            con.close();
        }
        catch (Exception e){}
    }
    
    public void insertar(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String completeDateInsert = "";
        try {
            String fechaNac = formatter.format(sdp.getNacimientoSastre());
            completeDateInsert = "DATE(\""+ fechaNac +"\")";
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            conectarDB();
            Statement stt = con.createStatement();
            stt.execute("USE SASTRERIA");
            String query = "INSERT INTO sastre (CODIGOSASTRE,CEDULASASTRE,NOMBRESASTRE,TELEFONOSASTRE,NACIMIENTOSASTRE,DIRECCIONSASTRE)" + 
	        	    "VALUES('"+sdp.getCodigoSastre()+"','"
                    +sdp.getCedulaSastre()+"','"
                    +sdp.getNombreSastre()+"',"
                    +sdp.getTelefonoSastre()+","
                    + completeDateInsert 
                    + ",'"+sdp.getDireccionSastre()+"')";
            System.out.println(query);
            stt.execute(query);
            stt.close();
            }
	    catch (Exception e){
                System.out.println(e.toString());
            }

        
    }
    public void eliminar(){
        try{
            conectarDB();
            Statement stt = con.createStatement();
            stt.execute("USE SASTRERIA");

            stt.execute("DELETE FROM sastre " + 
                        "WHERE CODIGOSASTRE='"+sdp.getCodigoSastre()+"'");

            stt.close();
            }
        catch (Exception exc){}

        
    }
    public void modificar(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String completeDateInsert = "";
        try {
            String fechaNac = formatter.format(sdp.getNacimientoSastre());
            completeDateInsert = "DATE(\""+ fechaNac +"\")";
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try{
            conectarDB();
            Statement stt = con.createStatement();
            stt.execute("USE SASTRERIA");

            stt.execute("UPDATE sastre SET CEDULASASTRE = '"+sdp.getCedulaSastre()+"', NOMBRESASTRE = '"+sdp.getNombreSastre()+"', TELEFONOSASTRE = '"+sdp.getTelefonoSastre()+"', NACIMIENTOSASTRE = "+completeDateInsert+",DIRECCIONSASTRE = '"+sdp.getDireccionSastre()+"'   "+
                                    "WHERE CODIGOSASTRE='"+sdp.getCodigoSastre()+"'");

            stt.close();
            }
        catch (Exception exc){
            System.out.println(exc.toString());
        }

        
    }
    public boolean verificar(){
        try{
            conectarDB();
            Statement stt = con.createStatement();
            stt.execute("USE SASTRERIA");
            ResultSet res = stt.executeQuery("SELECT * FROM sastre");
            while (res.next()){
                if(this.sdp.getCodigoSastre().equals(res.getString(1)))
                    return true;
	        	 
	        }
	        res.close();
	        stt.close();
            
             
        }catch (Exception ex) {
            
        }
        return false;

    }
    
    public Vector  listar(){
        conectarDB();
	Vector listaSastres = new Vector();
		
	try{
		Statement stt = con.createStatement();
	        stt.execute("USE SASTRERIA");
	         
	        this.rs = stt.executeQuery("SELECT * FROM sastre");
	        while (this.rs.next()){
                    SastreDP sastreAInsertar = new SastreDP();
                    sastreAInsertar.setCodigoSastre(rs.getString(1));
                    sastreAInsertar.setCedulaSastre(rs.getString(2));
                    sastreAInsertar.setNombreSastre(rs.getString(3));
                    sastreAInsertar.setTelefonoSastre(rs.getString(4));
                    sastreAInsertar.setNacimientoSastre(rs.getDate(5));
                    sastreAInsertar.setDireccionSastre(rs.getString(6));
                    listaSastres.add(sastreAInsertar); 
	        }
	        this.rs.close();
	        stt.close();
		}
	    catch (Exception e){
                System.out.println(e.toString());
            }
	
        
	return listaSastres;

        
    }
    
    public SastreDP buscar(){
        conectarDB();
        SastreDP sastreAux = new SastreDP();
        
        try{
            Statement stt = con.createStatement();
            stt.execute("USE SASTRERIA");

            this.rs = stt.executeQuery("SELECT * FROM sastre WHERE CODIGOSASTRE='"+sdp.getCodigoSastre()+"' ");

            while (this.rs.next()){
                sastreAux.setCodigoSastre(this.rs.getString(1));
                sastreAux.setCedulaSastre(this.rs.getString(2));
                sastreAux.setNombreSastre(this.rs.getString(3));
                sastreAux.setTelefonoSastre(this.rs.getString(4));
                sastreAux.setNacimientoSastre(this.rs.getDate(5));
                sastreAux.setDireccionSastre(this.rs.getString(6));
            }
            this.rs.close();
            stt.close();
            }
        catch (Exception exc){
            System.out.println(exc.toString());
        }
        
        return sastreAux;
        
    }
    public Vector buscarParametro(){
        return new Vector();
        
    }
      
}
