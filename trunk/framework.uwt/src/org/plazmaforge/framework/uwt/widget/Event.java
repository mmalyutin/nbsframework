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

package org.plazmaforge.framework.uwt.widget;

/**
 * General UI event 
 * 
 * @author ohapon
 *
 */
public class Event {

    private Widget widget;
    
    private Object delegate;    
    
    private String type;
    
    private int detail;
    
    private Widget item;
    
    private int index;
    
    //private GC gc;
    
    private int x;
    
    private int y;
    
    private int width;
    
    private int height;
    
    private int count;
    
    private int time;
    
    private int button;
    
    private char character;
    
    private int keyCode;
    
    private int stateMask;
    
    private int start;
    
    private int end;
    
    private String text;
    
    private boolean doit;
    
    private Object data;

    ////
    
    private int device;
    
    
    public Event() {
    }


    public Event(String type) {
	super();
	this.type = type;
    }


    public Widget getWidget() {
        return widget;
    }


    public void setWidget(Widget widget) {
        this.widget = widget;
    }


    public Object getDelegate() {
        return delegate;
    }


    public void setDelegate(Object delegate) {
        this.delegate = delegate;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public int getDetail() {
        return detail;
    }


    public void setDetail(int detail) {
        this.detail = detail;
    }


    public Widget getItem() {
        return item;
    }


    public void setItem(Widget item) {
        this.item = item;
    }


    public int getIndex() {
        return index;
    }


    public void setIndex(int index) {
        this.index = index;
    }


    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }


    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }


    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public int getHeight() {
        return height;
    }


    public void setHeight(int height) {
        this.height = height;
    }


    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }


    public int getTime() {
        return time;
    }


    public void setTime(int time) {
        this.time = time;
    }


    public int getButton() {
        return button;
    }


    public void setButton(int button) {
        this.button = button;
    }


    public char getCharacter() {
        return character;
    }


    public void setCharacter(char character) {
        this.character = character;
    }


    public int getKeyCode() {
        return keyCode;
    }


    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }


    public int getStateMask() {
        return stateMask;
    }


    public void setStateMask(int stateMask) {
        this.stateMask = stateMask;
    }


    public int getStart() {
        return start;
    }


    public void setStart(int start) {
        this.start = start;
    }


    public int getEnd() {
        return end;
    }


    public void setEnd(int end) {
        this.end = end;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public boolean isDoit() {
        return doit;
    }


    public void setDoit(boolean doit) {
        this.doit = doit;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public int getDevice() {
        return device;
    }


    public void setDevice(int device) {
        this.device = device;
    }

    
    
    
    
    
}
