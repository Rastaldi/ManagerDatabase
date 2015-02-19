package ControllerBean;


import ModelBean.LoginModelBean;


import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rastaldi
 */
@ManagedBean (name="LoginControllerBean")
@RequestScoped

public class LoginControllerBean implements Serializable {
    
    private String user;
    private String pass;
//    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private final LoginModelBean loginModelBean;
    private FacesMessage msg;
    
    public LoginControllerBean() {
        //
        loginModelBean = new LoginModelBean();
        faceContext = FacesContext.getCurrentInstance();
//        httpServletRequest = (HttpServletRequest)faceContext.getExternalContext().getRequest();
    }
    
    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public FacesMessage getMsg() {
        return msg;
    }

    public void setMsg(FacesMessage msg) {
        this.msg = msg;
    }
    
    
    
    public String login(){
        
        String matchPass = loginModelBean.doLogin(user);
       if(matchPass != null && matchPass.equals(pass)){
           //lo guardamos en sesion
//         httpServletRequest.getSession().setAttribute("sesionUsuario", user);
           //mandamos un mensaje de confirmacion
          msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", user);
           return "index";
       }else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Credenciales no válidas");
            return "login";
       }
       
    }
    
    public String registroView(){
        return "WEB-INF/sources/view/registro.xhtml";
    }
}
