/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelBean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Rastaldi
 */
@ManagedBean(name = "CheckboxTipoMysql")

public class CheckboxTipoMysql {

    private String[] tipoSeleccionado;
    private List<String> tipo;

    public CheckboxTipoMysql() {
        
        
        tipo = new ArrayList<String>();
        tipo.add("PK");
        tipo.add("NN");
        tipo.add("Null");
        tipo.add("AI");
        
    }

    public String[] getTipoSeleccionado() {
        return tipoSeleccionado;
    }

    public void setTipoSeleccionado(String[] tipoSeleccionado) {
        this.tipoSeleccionado = tipoSeleccionado;
    }

    public List<String> getTipo() {
        return tipo;
    }

    public void setTipo(List<String> tipo) {
        this.tipo = tipo;
    }
    
    
}
