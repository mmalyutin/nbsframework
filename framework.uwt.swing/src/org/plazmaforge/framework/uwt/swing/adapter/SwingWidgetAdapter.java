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

package org.plazmaforge.framework.uwt.swing.adapter;

import javax.swing.JScrollPane;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.TypedEvent;
import org.plazmaforge.framework.uwt.swing.widget.XDesktopItem;
import org.plazmaforge.framework.uwt.swing.widget.XTitlePanel;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;

public abstract class SwingWidgetAdapter extends SwingAbstractAdapter {

    
    
    protected java.awt.Component getComponent(Object delegate) {
	return (java.awt.Component) delegate;
    }

    protected java.awt.Container getContainer(Object delegate) {
	return (java.awt.Container) delegate;
    }

    
    /**
     * Return real content of UI object 
     * We can't override this method
     * @param delegate
     * @return
     */
    protected final java.awt.Container getContent(Object delegate) {
	if (delegate == null) {
	    return null;
	}
	if (delegate instanceof javax.swing.JFrame) {
	    return ((javax.swing.JFrame) delegate).getContentPane();
	} else 	if (delegate instanceof javax.swing.JDialog) {
	    return ((javax.swing.JDialog) delegate).getContentPane();
	} else 	if (delegate instanceof XTitlePanel) {
	    return ((XTitlePanel) delegate).getContentPane();
	} else 	if (delegate instanceof XDesktopItem) {
	    return ((XDesktopItem) delegate).getContent();
	}

	return (java.awt.Container) delegate;
    }

    /**
     * Return real component of UI object
     * We can override this method for any adapter
     * @param delegate
     * @return
     */
    protected java.awt.Component getViewComponent(Object delegate) {
   	return delegate == null ? null : (java.awt.Component) delegate;
    }
    
    /**
     * Return real content of UI object
     * We can override this method for any adapter
     * @param delegate
     * @return
     */
    protected java.awt.Container getViewContent(Object delegate) {
   	return (java.awt.Container) getViewComponent(delegate);
    }


    
    protected javax.swing.JComponent getJComponent(Object delegate) {
	return (javax.swing.JComponent) delegate;
    }
    
    
    
    
	
    
    /**
     * Return <code>JTable</code> of <code>JScrollPane</code>
     * @param delegate
     * @return
     */
    protected javax.swing.JTable getJTable(Object delegate) {
   	return (javax.swing.JTable) getScrollComponent(delegate);
    }

    /**
     * Return <code>JTree</code> of <code>JScrollPane</code>
     * @param delegate
     * @return
     */
    protected javax.swing.JTree getJTree(Object delegate) {
   	return (javax.swing.JTree) getScrollComponent(delegate);
    }

    /**
     * Return content of <code>JScrollPane</code>
     * @param delegate
     * @return
     */
    protected java.awt.Component getScrollComponent(Object delegate) {
   	return delegate == null ? null : ((JScrollPane) delegate).getViewport().getView();
    }

    
    
    
    public void disposeDelegate(UIObject parent, UIObject element) {

    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	super.setProperty(element, name, value);
    }

    @Override
    public Object getProperty(UIObject element, String name) {
	return super.getProperty(element, name);
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Listeners
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected java.util.EventListener getListener(Widget widget, Listener listener) {
	return (java.util.EventListener) widget.findListenerDelegate(listener);
    }

    protected java.util.EventListener getListener(Widget widget, Listener listener, int index) {
	return (java.util.EventListener) widget.findListenerDelegate(listener, index);
    }

    // KEY DOWN
    protected java.awt.event.KeyListener createKeyDownListener(Widget widget, final Listener listener) {
	java.awt.event.KeyListener xListener = new java.awt.event.KeyAdapter() {

		@Override
		public void keyPressed(java.awt.event.KeyEvent e) {
	            listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }

    
    // KEY UP
    protected java.awt.event.KeyListener createKeyUpListener(Widget widget, final Listener listener) {
	java.awt.event.KeyListener xListener = new java.awt.event.KeyAdapter() {

		@Override
		public void keyReleased(java.awt.event.KeyEvent e) {
	            listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }
    
    
    protected java.awt.event.KeyListener getKeyListener(Widget widget, Listener listener) {
	return (java.awt.event.KeyListener) getListener(widget, listener);
    }

    protected java.awt.event.KeyListener getKeyListener(Widget widget, Listener listener, int index) {
	return (java.awt.event.KeyListener) getListener(widget, listener, index);
    }



    // MOUSE DOWN
    protected java.awt.event.MouseListener createMouseDownListener(Widget widget, final Listener listener) {
	java.awt.event.MouseListener xListener = new java.awt.event.MouseAdapter() {

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
	            listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }

    
    // MOUSE UP
    protected java.awt.event.MouseListener createMouseUpListener(Widget widget, final Listener listener) {
	java.awt.event.MouseListener xListener = new java.awt.event.MouseAdapter() {

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
		    listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }


    // MOUSE CLICK
    protected java.awt.event.MouseListener createMouseClickListener(Widget widget, final Listener listener) {
	java.awt.event.MouseListener xListener = new java.awt.event.MouseAdapter() {

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
		    // Single Click
		    if (e.getClickCount() != 1) {
			return;
		    }
		    listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }

    
    // MOUSE DOUBLE CLICK
    protected java.awt.event.MouseListener createMouseDoubleClickListener(Widget widget, final Listener listener) {
	java.awt.event.MouseListener xListener = new java.awt.event.MouseAdapter() {

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
		    // Double Click
		    if (e.getClickCount() != 2) {
			return;
		    }
		    listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }
    
    
    protected java.awt.event.MouseListener getMouseListener(Widget widget, Listener listener) {
	return (java.awt.event.MouseListener) getListener(widget, listener);
    }

    protected java.awt.event.MouseListener getMouseListener(Widget widget, Listener listener, int index) {
	return (java.awt.event.MouseListener) getListener(widget, listener, index);
    }


    
    // ACTION CLICK
    protected java.awt.event.ActionListener createActionListener(Widget widget, final Listener listener) {
	java.awt.event.ActionListener xListener = new java.awt.event.ActionListener() {

		@Override
		public void  actionPerformed(java.awt.event.ActionEvent e) {
		    listener.handleEvent(createEvent(e));
		}
	    };
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    
    protected java.awt.event.ActionListener getActionListener(Widget widget, final Listener listener) {
	return (java.awt.event.ActionListener) widget.findListenerDelegate(listener);
    }

    
    
    // MOUSE MOVE
    protected java.awt.event.MouseListener createMouseMoveListener(Widget widget, final Listener listener) {
	java.awt.event.MouseListener xListener = new java.awt.event.MouseAdapter() {

		@Override
		public void mouseMoved(java.awt.event.MouseEvent e) {
		    listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }

    
    // MOUSE IN
    protected java.awt.event.MouseListener createMouseInListener(Widget widget, final Listener listener) {
	java.awt.event.MouseListener xListener =  new java.awt.event.MouseAdapter() {

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
		    listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }


    // MOUSE OUT
    protected java.awt.event.MouseListener createMouseOutListener(Widget widget, final Listener listener) {
	java.awt.event.MouseListener xListener =  new java.awt.event.MouseAdapter() {

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
		    listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }
    
    
    // FOCUS IN
    protected java.awt.event.FocusListener createFocusInListener(Widget widget, final Listener listener) {
	java.awt.event.FocusListener xListener = new java.awt.event.FocusAdapter() {

		@Override
		public void focusGained(java.awt.event.FocusEvent e) {
		    listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }


    // FOCUS OUT
    protected java.awt.event.FocusListener createFocusOutListener(Widget widget, final Listener listener) {
	java.awt.event.FocusListener xListener = new java.awt.event.FocusAdapter() {

		@Override
		public void focusLost(java.awt.event.FocusEvent e) {
		    listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }
    
    
    protected java.awt.event.FocusListener getFocusListener(Widget widget, Listener listener) {
	return (java.awt.event.FocusListener) getListener(widget, listener);
    }


    
    // LIST SELECTION LISTENER
    protected javax.swing.event.ListSelectionListener createListSelectionListener(Widget widget, final Listener listener) {
	javax.swing.event.ListSelectionListener xListener = new javax.swing.event.ListSelectionListener() {

		@Override
		public void  valueChanged(javax.swing.event.ListSelectionEvent e) {
		    listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }

    protected javax.swing.event.ListSelectionListener getListSelectionListener(Widget widget, Listener listener) {
	return (javax.swing.event.ListSelectionListener) getListener(widget, listener);
    }

    
    // TREE SELECTION LISTENER
    protected javax.swing.event.TreeSelectionListener createTreeSelectionListener(Widget widget, final Listener listener) {
	javax.swing.event.TreeSelectionListener xListener = new javax.swing.event.TreeSelectionListener() {

		@Override
		public void  valueChanged(javax.swing.event.TreeSelectionEvent e) {
		    listener.handleEvent(createEvent(e));
		}
	    };
	    widget.assignListener(listener, xListener);
	    return xListener;
    }
    
    protected javax.swing.event.TreeSelectionListener getTreeSelectionListener(Widget widget, Listener listener) {
	return (javax.swing.event.TreeSelectionListener) getListener(widget, listener);
    }

    
    // M_ENTER (MOUSE DOUBLE CLICK)
    protected java.awt.event.MouseListener createMEnterListener(Widget widget, final Listener listener) {
	java.awt.event.MouseListener xListener = new java.awt.event.MouseAdapter() {

	    @Override
	    public void mouseClicked(java.awt.event.MouseEvent e) {
		// Double Click
		if (e.getClickCount() != 2) {
		    return;
		}
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }

    // K_ENTER (KEY ENTER [13])
    protected java.awt.event.KeyListener createKEnterListener(Widget widget, final Listener listener) {
	java.awt.event.KeyListener xListener = new java.awt.event.KeyAdapter() {

	    @Override
	    public void keyTyped(java.awt.event.KeyEvent e) {
		if ('\n' != e.getKeyChar() ){
		   return; 
		}
		listener.handleEvent(createEvent(e));
	    }
	};
	widget.assignListener(listener, xListener);
	return xListener;
    }
    
    
    
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Events
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    /**
     * Create UWT Event by Swing <code>KeyEvent</code>
     * @param e
     * @return
     */
    protected Event createEvent(java.awt.event.KeyEvent e) {
        Event event = new Event();
        event.setKeyCode(e.getKeyCode());
        event.setCharacter(e.getKeyChar());
        event.setStateMask(e.getModifiers());
        return event;
    }

    /**
     * Create UWT Event by Swing <code>MouseEvent</code>
     * @param e
     * @return
     */
    protected Event createEvent(java.awt.event.MouseEvent e) {
        Event event = new Event();
        event.setButton(e.getButton());
        event.setX(e.getX());
        event.setY(e.getY());
        event.setStateMask(e.getModifiers());
        event.setCount(e.getClickCount());
        
        return event;
    }
    
    /**
     * Convert state mask from Swing to UWT
     * @return
     */
    protected int getStateMask(int xStateMask) {
	int stateMask = 0;
	if ((xStateMask & java.awt.event.InputEvent.SHIFT_MASK) != 0) {
	    stateMask |= TypedEvent.SHIFT_MASK;
	}
	if ((xStateMask & java.awt.event.InputEvent.CTRL_MASK) != 0) {
	    stateMask |= TypedEvent.CTRL_MASK;
	}
	if ((xStateMask & java.awt.event.InputEvent.META_MASK) != 0) {
	    stateMask |= TypedEvent.META_MASK;
	}
	if ((xStateMask & java.awt.event.InputEvent.ALT_MASK) != 0) {
	    stateMask |= TypedEvent.ALT_MASK;
	}
	return stateMask;
    }
    
    /**
     * Create UWT Event by Swing <code>FocusEvent</code>
     * @param e
     * @return
     */
    protected Event createEvent(java.awt.event.FocusEvent e) {
        Event event = new Event();
        return event;
    }

    /**
     * Create UWT Event by Swing <code>ActionEvent</code>
     * @param e
     * @return
     */
    protected Event createEvent(java.awt.event.ActionEvent e) {
        Event event = new Event();
        //event.button = e.getButton();
        //event.x = e.getX();
        //event.y = e.getY();
        //event.stateMask = e.stateMask;
        //event.count = e.getClickCount();
        
        return event;
    }

    /**
     * Create UWT Event by Swing <code>ListSelectionEvent</code>
     * @param e
     * @return
     */
    protected Event createEvent(javax.swing.event.ListSelectionEvent e) {
        Event event = new Event();
        event.setIndex(e.getFirstIndex());
        return event;
    }
    
    /**
     * Create UWT Event by Swing <code>TreeSelectionEvent</code>
     * @param e
     * @return
     */
    protected Event createEvent(javax.swing.event.TreeSelectionEvent e) {
        Event event = new Event();
        //event.index = e.getFirstIndex();
        return event;
    }

    

}

