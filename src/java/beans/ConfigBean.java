
package beans;

import java.io.Serializable;


public class ConfigBean implements Serializable{
    
    private String emailAdm = "julio.mariotto@ufpr.br";

    public String getEmailAdm() {
        return emailAdm;
    }

    public void setEmailAdm(String emailAdm) {
        this.emailAdm = emailAdm;
    }

    public ConfigBean() {
    }
    
}
