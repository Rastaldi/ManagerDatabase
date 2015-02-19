/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelBean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Rastaldi
 */
@ManagedBean(name = "LoginModelBean")
@RequestScoped
public class LoginModelBean implements Serializable {

    /**
     * Creates a new instance of LoginModelBean
     */
    public LoginModelBean() {
    }
    
    public String doLogin(String user){
        Jedis connJedis = new Jedis("localhost");
        connJedis.select(2);
        
        String matchPass = connJedis.hget("user:pass", user);
        return matchPass;
    }
}
