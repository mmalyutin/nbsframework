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
package org.plazmaforge.framework.uwt.gxt.widget.cell;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.NumberCell;

/**
 * 
 * @author ohapon
 *
 * @param <N>
 */
public class XNumberCell<N extends Number> extends XAbstractCell<N> {


    /**
     * The {@link NumberFormat} used to render the number.
     */
    private final NumberFormat format;

    /**
     * The {@link SafeHtmlRenderer} used to render the formatted number as HTML.
     */
    private final SafeHtmlRenderer<String> renderer;

    /**
     * Construct a new {@link NumberCell} using decimal format and a
     * {@link SimpleSafeHtmlRenderer}.
     */
    public XNumberCell() {
      this(NumberFormat.getDecimalFormat(), SimpleSafeHtmlRenderer.getInstance());
    }

    /**
     * Construct a new {@link NumberCell} using the given {@link NumberFormat} and
     * a {@link SimpleSafeHtmlRenderer}.
     *
     * @param format the {@link NumberFormat} used to render the number
     */
    public XNumberCell(NumberFormat format) {
      this(format, SimpleSafeHtmlRenderer.getInstance());
    }

    /**
     * Construct a new {@link NumberCell} using decimal format and the given
     * {@link SafeHtmlRenderer}.
     *
     * @param renderer the {@link SafeHtmlRenderer} used to render the formatted
     *          number as HTML
     */
    public XNumberCell(SafeHtmlRenderer<String> renderer) {
      this(NumberFormat.getDecimalFormat(), renderer);
    }

    /**
     * Construct a new {@link NumberCell} using the given {@link NumberFormat} and
     * a {@link SafeHtmlRenderer}.
     *
     * @param format the {@link NumberFormat} used to render the number
     * @param renderer the {@link SafeHtmlRenderer} used to render the formatted
     *          number as HTML
     */
    public XNumberCell(NumberFormat format, SafeHtmlRenderer<String> renderer) {
      if (format == null) {
        throw new IllegalArgumentException("format == null");
      }
      if (renderer == null) {
        throw new IllegalArgumentException("renderer == null");
      }
      this.format = format;
      this.renderer = renderer;
    }

    @Override
    protected String formatValue(N value) {
	return value == null ? null : format.format(value);
    }


}
