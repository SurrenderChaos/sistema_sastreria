/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MD;
import DP.SastreDP;
import DP.TareaTextilDP;
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
public class TareaTextilMD {
    private TareaTextilDP ttdp;
    private java.sql.Connection con;
    private java.sql.ResultSet rs;

    public TareaTextilMD(TareaTextilDP ttdp) {
        this.ttdp = ttdp;
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
            String fechalimite = formatter.format(ttdp.getFechaLimiteTarea());
            completeDateInsert = "DATE(\""+ fechalimite +"\")";
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            conectarDB();
            Statement stt = con.createStatement();
            stt.execute("USE SASTRERIA");
            String query = "INSERT INTO tarea (CODIGOTAREA,FECHALIMITE,TIPOTAREA,DESTAREA)" + 
	        	    "VALUES('"+ttdp.getCodigoTarea()+"','"
                   
                    + completeDateInsert 
                    + ",'"+ttdp.getTipoTarea()+"',"
                    +ttdp.getDescripcionTarea()+"')";
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

            stt.execute("DELETE FROM tarea " + 
                        "WHERE CODIGOTAREA='"+ttdp.getCodigoTarea()+"'");

            stt.close();
            }
        catch (Exception exc){}

        
    }
     
     public void modificar(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String completeDateInsert = "";
        try {
            String fechalimite = formatter.format(ttdp.getFechaLimiteTarea());
            completeDateInsert = "DATE(\""+ fechalimite +"\")";
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try{
            conectarDB();
            Statement stt = con.createStatement();
            stt.execute("USE SASTRERIA");

            stt.execute("UPDATE tarea SET FECHALIMITE = '"+ttdp.getFechaLimiteTarea()+"', TIPOTAREA = '"+ttdp.getTipoTarea()+"', DESTAREA = '"+ttdp.getDescripcionTarea()+"'");

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
            ResultSet res = stt.executeQuery("SELECT * FROM tarea");
            while (res.next()){
                if(this.ttdp.getCodigoTarea().equals(res.getString(1)))
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
	Vector listaTareas = new Vector();
		
	try{
		Statement stt = con.createStatement();
	        stt.execute("USE SASTRERIA");
	         
	        this.rs = stt.executeQuery("SELECT * FROM tarea");
	        while (this.rs.next()){
                    TareaTextilDP sastreAInsertar = new TareaTextilDP();
                    sastreAInsertar.setCodigoTarea(rs.getString(1));
                    sastreAInsertar.setFechaLimiteTarea(rs.getDate(2));
                    sastreAInsertar.setTipoTarea(rs.getString(3));
                    sastreAInsertar.setDescripcionTarea(rs.getString(4));
               
                    listaTareas.add(sastreAInsertar); 
	        }
	        this.rs.close();
	        stt.close();
		}
	    catch (Exception e){
                System.out.println(e.toString());
            }
	
        
	return listaTareas;

        
    }
    
    public TareaTextilDP buscar(){
        conectarDB();
        TareaTextilDP tareaAux = new TareaTextilDP();
        
        try{
            Statement stt = con.createStatement();
            stt.execute("USE SASTRERIA");

            this.rs = stt.executeQuery("SELECT * FROM tarea WHERE CODIGOTAREA='"+ttdp.getCodigoTarea()+"' ");

            while (this.rs.next()){
                tareaAux.setCodigoTarea(this.rs.getString(1));
                tareaAux.setFechaLimiteTarea(this.rs.getDate(2));
                tareaAux.setTipoTarea(this.rs.getString(3));
                tareaAux.setDescripcionTarea(this.rs.getString(4));
                
            }
            this.rs.close();
            stt.close();
            }
        catch (Exception exc){
            System.out.println(exc.toString());
        }
        
        return tareaAux;
        
    }
    
    public Vector buscarParametro(){
        return new Vector();
        
    }
     
     
     
     
     
     
     
    
}
