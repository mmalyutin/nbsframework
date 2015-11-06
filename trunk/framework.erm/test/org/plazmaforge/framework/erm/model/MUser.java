package org.plazmaforge.framework.erm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MUser extends MModel  {

    private String userName;
    
    private String password;
    
    private Date registerDate;

    private List<MUserRole> roles;
    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public List<MUserRole> getRoles() {
	if (roles == null) {
	    roles = new ArrayList<MUserRole>();
	}
        return roles;
    }

    public void addRole(MUserRole role) {
	role.setUser(this);
	getRoles().add(role);
    }
    
    public void removeRole(MUserRole role) {
	getRoles().remove(role);
    }
    
    public void setRoles(List<MUserRole> roles) {
        this.roles = roles;
    }

    public String toString() {
	return "User[" + toPropertiesString() + "]";
    }
    
    public String toPropertiesString() {
	return super.toPropertiesString() + ", userName=" + getUserName()+ ", password=" + getPassword(); 
    }
    
}
