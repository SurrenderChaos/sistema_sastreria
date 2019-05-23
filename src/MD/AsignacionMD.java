package MD;

import DP.AsignacionDP;
import DP.SastreDP;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class AsignacionMD {
    private AsignacionDP adp;
    private java.sql.Connection con;
    private java.sql.ResultSet rs;

    public AsignacionMD(AsignacionDP adp) {
        this.adp = adp;
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
        String codigoSastre = this.adp.getSastre().getCodigoSastre();
        String codigoTarea = this.adp.getTareaTextil().getCodigoTarea();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String completeDateInsert = "";
        /*try {
            String fechaNac = formatter.format(sdp.getNacimientoSastre());
            completeDateInsert = "DATE(\""+ fechaNac +"\")";
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try{
            conectarDB();
            Statement stt = con.createStatement();
            stt.execute("USE SASTRERIA");
            String query = "";
            /*String query = "INSERT INTO sastre (CODIGOSASTRE,CEDULASASTRE,NOMBRESASTRE,TELEFONOSASTRE,NACIMIENTOSASTRE,DIRECCIONSASTRE)" + 
	        	    "VALUES('"+sdp.getCodigoSastre()+"','"
                    +sdp.getCedulaSastre()+"','"
                    +sdp.getNombreSastre()+"',"
                    +sdp.getTelefonoSastre()+","
                    + completeDateInsert 
                    + ",'"+sdp.getDireccionSastre()+"')";*/
            System.out.println(query);
            stt.execute(query);
            stt.close();
            }
	    catch (Exception e){
                System.out.println(e.toString());
            }

        
    }
}
