/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerBean;

import ModelBean.MySqlModelBean;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Rastaldi
 */
@ManagedBean
@SessionScoped
public class MySqlControllerBean {
    
    MySqlModelBean mySqlModelBean;
    String nameTable;
    String nameFila;
    String tipoDato;
    List<String> tipo;
    String valorInsert;
    /**
     * Creates a new instance of MySqlControllerBean
     */
    public MySqlControllerBean() {
        mySqlModelBean = new MySqlModelBean();
    }

    public MySqlModelBean getMySqlModelBean() {
        return mySqlModelBean;
    }

    public void setMySqlModelBean(MySqlModelBean mySqlModelBean) {
        this.mySqlModelBean = mySqlModelBean;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getNameFila() {
        return nameFila;
    }

    public void setNameFila(String nameFila) {
        this.nameFila = nameFila;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public List<String> getTipo() {
        return tipo;
    }

    public void setTipo(List<String> tipo) {
        this.tipo = tipo;
    }

    public String getValorInsert() {
        return valorInsert;
    }

    public void setValorInsert(String valorInsert) {
        this.valorInsert = valorInsert;
    }
    
    
    
    
    
    
    
    public String createTable() throws SQLException{
        mySqlModelBean.createTableMySql(nameTable, nameFila, tipoDato, tipo);
        return "index";
    }
    
    public String deleteTable() throws SQLException{
        mySqlModelBean.deleteTableMySql(nameTable);
        return "index";
    }
    
    public String insertTable() throws SQLException{
        mySqlModelBean.insertTableMySql(nameTable, nameFila, valorInsert);
        return "index";
    }
}
