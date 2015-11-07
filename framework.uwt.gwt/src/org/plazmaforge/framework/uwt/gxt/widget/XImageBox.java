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

package org.plazmaforge.framework.uwt.gxt.widget;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.IconSupport;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class XImageBox extends BoxComponent implements IconSupport {

    private AbstractImagePrototype icon;
    private El iconEl;
    private String text;
    
    //private El textEl;

    /**
     * Creates a new shortcut.
     */
    public XImageBox() {

    }

    /**
     * Creates a new shortcut.
     * 
     * @param id the shortcut id
     * @param text the shortcut text
     */
    public XImageBox(String id, String text) {
      setId(id);
      //setText(text);
    }

    /**
     * Adds a selection listener.
     * 
     * @param listener the listener to add
     */
    public void addSelectionListener(SelectionListener<? extends ComponentEvent> listener) {
      addListener(Events.Select, listener);
    }

    public AbstractImagePrototype getIcon() {
      return icon;
    }

    /**
     * Returns the shortcuts text.
     * 
     * @return the text
     */
    public String getText() {
      return text;
    }

    @Override
    public void onComponentEvent(ComponentEvent ce) {
      super.onComponentEvent(ce);
      if (ce.getEventTypeInt() == Event.ONCLICK) {
        onClick(ce);
      }
    }

    /**
     * Removes a previously added listener.
     * 
     * @param listener the listener to be removed
     */
    public void removeSelectionListener(SelectionListener<? extends ComponentEvent> listener) {
      removeListener(Events.Select, listener);
    }

    public void setIcon(AbstractImagePrototype icon) {
      if (rendered) {
        iconEl.setInnerHtml("");
        iconEl.appendChild((Element) icon.createElement().cast());
      }
      this.icon = icon;

    }

    public void setIconStyle(String icon) {
      setIcon(IconHelper.create(icon, 48, 48));

    }

//    public void setText(String text) {
//      this.text = text;
//      if(textEl != null){
//        textEl.update(Util.isEmptyString(text) ? "&#160;" : text);
//      }
//    }

    protected void onClick(ComponentEvent ce) {
      ce.stopEvent();
      fireEvent(Events.Select, ce);
    }

    @Override
    protected void onRender(Element target, int index) {
      super.onRender(target, index);
      setElement(DOM.createElement("dt"), target, index);
      El a = el().createChild("<a href='#'></a>");
      iconEl = a.createChild("<div><img src='" + GXT.BLANK_IMAGE_URL + "' /></div>");
      
      //textEl = a.createChild("<div></div>");

      
      el().updateZIndex(0);
      sinkEvents(Event.ONCLICK);
      if (icon != null) {
        setIcon(icon);
      }
      
      //setText(text);
    }

}
