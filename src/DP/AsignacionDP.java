package DP;

import MD.AsignacionMD;
import java.util.Vector;

public class AsignacionDP {
    private SastreDP sastre;
    private TareaTextilDP tareaTextil;
    private AsignacionMD amd;

    public AsignacionDP() {
        this.amd = new AsignacionMD(this);
    }

    public SastreDP getSastre() {
        return sastre;
    }

    public void setSastre(SastreDP sastre) {
        this.sastre = sastre;
    }

    public TareaTextilDP getTareaTextil() {
        return tareaTextil;
    }

    public void setTareaTextil(TareaTextilDP tareaTextil) {
        this.tareaTextil = tareaTextil;
    }
    
    public Vector listarSastres() {
       return sastre.listarSastre();
    }
    
    public Vector listarTareas() {
       return tareaTextil.listarTareas();
    }
    
    public void insertar(){
        amd.insertar();
    }
    
}
