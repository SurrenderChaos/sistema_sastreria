/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP;

import MD.SastreMD;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Alienware
 */
public class SastreDP {
    private String codigoSastre;
    private String cedulaSastre;
    private String nombreSastre;
    private String telefonoSastre;
    private Date nacimientoSastre;
    private String direccionSastre;
    private SastreMD smd;

    public SastreDP() {
        this.smd = new SastreMD(this);
    }

    public String getCodigoSastre() {
        return codigoSastre;
    }

    public void setCodigoSastre(String codigoSastre) {
        this.codigoSastre = codigoSastre;
    }

    public String getCedulaSastre() {
        return cedulaSastre;
    }

    public void setCedulaSastre(String cedulaSastre) {
        this.cedulaSastre = cedulaSastre;
    }

    public String getNombreSastre() {
        return nombreSastre;
    }

    public void setNombreSastre(String nombreSastre) {
        this.nombreSastre = nombreSastre;
    }

    public String getTelefonoSastre() {
        return telefonoSastre;
    }

    public void setTelefonoSastre(String telefonoSastre) {
        this.telefonoSastre = telefonoSastre;
    }

    public Date getNacimientoSastre() {
        return nacimientoSastre;
    }

    public void setNacimientoSastre(Date nacimientoSastre) {
        this.nacimientoSastre = nacimientoSastre;
    }

    public String getDireccionSastre() {
        return direccionSastre;
    }

    public void setDireccionSastre(String direccionSastre) {
        this.direccionSastre = direccionSastre;
    }

    public void insertarSastre() {
        smd.insertar();
    }

    public void eliminarSastre() {
        smd.eliminar();
    }

    public void modificarSastre() {
        smd.modificar();
    }

    public boolean verificarSastre() {
        return smd.verificar();
    }

    public Vector listarSastre() {
       return smd.listar();
    }

    public SastreDP buscarSastre() {
       return smd.buscar();
    }

    public Vector buscarParametroSastre() {
        return smd.buscarParametro();
    }
    
    @Override
    public String toString() {
        return "SastreDP{" + "codigoSastre=" + codigoSastre + ", cedulaSastre=" + cedulaSastre + ", nombreSastre=" + nombreSastre + ", telefonoSastre=" + telefonoSastre + ", nacimientoSastre=" + nacimientoSastre + '}';
    }
      
}
