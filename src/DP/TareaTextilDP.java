/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP;
import MD.TareaTextilMD;
import java.util.Date;
import java.util.Vector;



/**
 *
 * @author Alienware
 */
public class TareaTextilDP {
    private String codigoTarea;
    private Date fechaLimiteTarea;
    private String tipoTarea;
    private String descripcionTarea;
    private TareaTextilMD ttmd;

    public TareaTextilDP() {
        this.ttmd = new TareaTextilMD(this);
    }
    

    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public Date getFechaLimiteTarea() {
        return fechaLimiteTarea;
    }

    public void setFechaLimiteTarea(Date fechaLimiteTarea) {
        this.fechaLimiteTarea = fechaLimiteTarea;
    }

    public String getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }
    
    public void insertarTarea(){
        ttmd.insertar();
    }
    
    public void modificarTarea(){
        ttmd.modificar();
    }
    
    public void eliminarTarea(){
        ttmd.eliminar();
    }
    
    public Vector listarTareas(){
        return ttmd.listar();
    }
    
    public TareaTextilDP buscarTarea(){
        return ttmd.buscar();
    }
    
    public Vector buscarParametroTarea(){
        return ttmd.buscarParametro();
    }
    
    public boolean verificarTarea() {
        return ttmd.verificar();
    }

    @Override
    public String toString() {
        return "TareaTextilDP{" + "codigoTarea=" + codigoTarea + ", fechaLimiteTarea=" + fechaLimiteTarea + ", tipoTarea=" + tipoTarea + ", descripcionTarea=" + descripcionTarea + '}';
    }

    
    
    
    
    
    
    
}
