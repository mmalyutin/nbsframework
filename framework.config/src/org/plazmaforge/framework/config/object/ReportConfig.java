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

package org.plazmaforge.framework.config.object;


/**
 * 
 * @author ohapon
 *
 */
public class ReportConfig extends UIObjectConfig implements IReportConfig {

    private static final long serialVersionUID = -8486875032502878641L;

    private String path;

    private String reportingEngine;

    private String fileName;

    private String region;

    private String country;

    private String language;

    private boolean single;

    private boolean custom;
    
    private String acceptor;
    
    public ReportConfig() {
    }

    public String getPath() {
	return path;
    }

    public void setPath(String path) {
	this.path = path;
    }

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public String getReportingEngine() {
	return reportingEngine;
    }

    public void setReportingEngine(String reportingEngine) {
	this.reportingEngine = reportingEngine;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public String getLanguage() {
	return language;
    }

    public void setLanguage(String language) {
	this.language = language;
    }

    public String getRegion() {
	return region;
    }

    public void setRegion(String region) {
	this.region = region;
    }

    public boolean isSingle() {
	return single;
    }

    public void setSingle(boolean single) {
	this.single = single;
    }

    
    public boolean isIndependent() {
	return !isSingle();
    }

    public void setIndependent(boolean independent) {
	setSingle(!independent);
    }
    
    
    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor;
    }

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    
}
