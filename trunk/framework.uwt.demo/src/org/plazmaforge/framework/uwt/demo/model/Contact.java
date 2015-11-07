/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package org.plazmaforge.framework.uwt.demo.model;

import java.util.Date;

public class Contact extends Dictionary {
    
    private static final long serialVersionUID = 814808878521352620L;

    /**
     * Company name
     */
    private String company;
    
    /**
     * Job Title (position)
     */
    private String jobTitle;
    
    /**
     * Email address
     */
    private String email;
    
    /**
     * Postal address
     */
    private String address;
    
    /**
     * Phone
     */
    private String phone;
    
    /**
     * Web page
     */
    private String web;
    
    
    /**
     * Birth date
     */
    private Date birthDate;
    

    public Contact() {
	super();
    }

    public Contact(String id) {
	super(id);
    }

    public Contact(String id, String name) {
	super(id, name);
    }


    public Contact(String id, String name, Date birthDate) {
	super(id, name);
	this.birthDate = birthDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    
    
    

}
