/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerBean;

import ModelBean.RedisModelBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Rastaldi
 */
@ManagedBean (name="RedisControllerBean")
@SessionScoped

public class RedisControllerBean implements Serializable{
    List result;

    //declaramos las variable scon las que trabajara nuestro bean q vendran de la vista
     String nameSet;
    String txtSetRedis;
    String nameKeyRemove;
    String nameRemoveSet;
    String valorRemoveSet;
    RedisModelBean redisModelBean;
    Set <String> resultAllKeys;
    List resultKey;
    List <String> AllKeys;
    String nameExtraeKey;
    String nameHash;
    String nameKeyHash;
    String nameValueHash;
    String modoList;
    String nameList;
    String nameValueList;        
    Long indexList;
    FacesMessage msg;
            
    /**
     * Creates a new instance of RedisControllerBean
     */
    public RedisControllerBean() {
        redisModelBean = new RedisModelBean();
        resultAllKeys = redisModelBean.extraerKeysRedis();

        resultKey = new ArrayList();
    }

    public String getNameSet() {
        return nameSet;
    }

    public void setNameSet(String nameSet) {
        this.nameSet = nameSet;
    }

    public String getTxtSetRedis() {
        return txtSetRedis;
    }

    public void setTxtSetRedis(String txtSetRedis) {
        this.txtSetRedis = txtSetRedis;
    }

    public String getNameKeyRemove() {
        return nameKeyRemove;
    }

    public void setNameKeyRemove(String nameKeyRemove) {
        this.nameKeyRemove = nameKeyRemove;
    }

    public String getNameRemoveSet() {
        return nameRemoveSet;
    }

    public void setNameRemoveSet(String nameRemoveSet) {
        this.nameRemoveSet = nameRemoveSet;
    }

    public String getValorRemoveSet() {
        return valorRemoveSet;
    }

    public void setValorRemoveSet(String valorRemoveSet) {
        this.valorRemoveSet = valorRemoveSet;
    }

    public Set<String> getResultAllKeys() {
        return resultAllKeys;
    }

    public void setResultAllKeys(Set<String> resultAllKeys) {
        this.resultAllKeys = resultAllKeys;
    }
   
    public RedisModelBean getRedisModelBean() {
        return redisModelBean;
    }

    public void setRedisModelBean(RedisModelBean redisModelBean) {
        this.redisModelBean = redisModelBean;
    }

    public List getResultKey() {
        return resultKey;
    }

    public void setResultKey(List resultKey) {
        this.resultKey = resultKey;
    }

   

    public String getNameExtraeKey() {
        return nameExtraeKey;
    }

    public void setNameExtraeKey(String nameExtraeKey) {
        this.nameExtraeKey = nameExtraeKey;
    }

    public String getNameHash() {
        return nameHash;
    }

    public void setNameHash(String nameHash) {
        this.nameHash = nameHash;
    }

    public String getNameKeyHash() {
        return nameKeyHash;
    }

    public void setNameKeyHash(String nameKeyHash) {
        this.nameKeyHash = nameKeyHash;
    }

    public String getNameValueHash() {
        return nameValueHash;
    }

    public void setNameValueHash(String nameValueHash) {
        this.nameValueHash = nameValueHash;
    }

    public String getModoList() {
        return modoList;
    }

    public void setModoList(String modoList) {
        this.modoList = modoList;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public String getNameValueList() {
        return nameValueList;
    }

    public void setNameValueList(String nameValueList) {
        this.nameValueList = nameValueList;
    }

    public Long getIndexList() {
        return indexList;
    }

    public void setIndexList(Long indexList) {
        this.indexList = indexList;
    }

    public FacesMessage getMsg() {
        return msg;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }

 //result

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }
    
   
    
    
    
    
    //aqui añadimos un set a redis + un campo
     public String addSet(){
        
        if (redisModelBean.addRedisSet(nameSet, txtSetRedis)){
            //redisModelBean.addRedisSet(nameSet, txtSetRedis);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Action sucessfull.");
        }else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Debe introducir valores.");
        }
            return "index";
            //return "";
       }
     //borramos una key
     public String removeKeyRedis() {
         redisModelBean.removeKey(nameKeyRemove);
         return "index";
     }
     //borramos un campo especifico de un set
     public String removeSetRedis(){
         redisModelBean.removeSet(nameRemoveSet, valorRemoveSet);
         return "index";
     }
       //aqui le mandamos a la vista todo lo que hay en nuestra base de datos.
//     public String verListRedis(){
//         resultAllKeys = redisModelBean.verListRedis();
//         resultSet = resultAllKeys.get(1);
//         resultHash = resultAllKeys.get(2);
//         resultList = resultAllKeys.get(3);
//         //aqui pasamos el result
////         System.out.println(resultAllKeys.get(0));
////         System.out.println(resultAllKeys.get(1));
////         System.out.println(resultAllKeys.get(3));
////         System.out.println(resultHash.toString());
//         
//         return null;
//     }
     
     public String addHash(){
         redisModelBean.addHashRedis(nameHash, nameKeyHash, nameValueHash);
         return "index";
     }
     
     public String deleteHash(){
         redisModelBean.deleteHashRedis(nameHash, nameKeyHash, nameValueHash);
         return "index";
     }
     
     public void addList(){
         redisModelBean.addListRedis(modoList, nameList, nameValueList);
     }
     
     public void addListIndex(){
         redisModelBean.addListIndexRedis(indexList, nameList, nameValueList);
     }
     
     public void deleteList(){
         redisModelBean.deleteListRedis(indexList, nameList, nameValueList);
     }
     
     
     
     //extraemos todas las keys para poder crear los buttons con el nombre de cada tabla
     public List<String> extraerKeys() {
         resultAllKeys = redisModelBean.extraerKeysRedis();
           for(String s : resultAllKeys) {
               System.out.println("+++ " + s + " +++++\n");
               AllKeys.add(s);
           }
        return AllKeys; 
     }
    
     
     public List extraerKey(String nameExtraeKey){
         result = redisModelBean.extraerKeyRedis(nameExtraeKey);
         return result;
     }
}
