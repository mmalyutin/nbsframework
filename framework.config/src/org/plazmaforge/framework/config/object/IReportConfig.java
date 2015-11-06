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
public interface IReportConfig extends IUIObjectConfig {

    String getReportingEngine();

    void setReportingEngine(String reportingEngine);

    String getFileName();

    void setFileName(String fileName);

    String getPath();

    void setPath(String folder);

    String getRegion();

    void setRegion(String region);

    String getCountry();

    void setCountry(String country);

    String getLanguage();

    void setLanguage(String language);

    boolean isSingle();

    void setSingle(boolean single);
    
    boolean isIndependent();

    void setIndependent(boolean independent);
    
    String getAcceptor();
    
    void setAcceptor(String scceptor);
    
    boolean isCustom();

    void setCustom(boolean custom);

}
