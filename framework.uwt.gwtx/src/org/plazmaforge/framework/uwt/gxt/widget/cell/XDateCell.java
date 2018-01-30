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

import java.util.Date;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.TimeZone;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;

/**
 * 
 * @author ohapon
 *
 */
public class XDateCell extends XAbstractCell<Date> {


    private final DateTimeFormat format;

    private final SafeHtmlRenderer<String> renderer;

    private final TimeZone timeZone;

    /**
     * Construct a new {@link DateCell} using the format
     * {@link PredefinedFormat#DATE_FULL} and a {@link SimpleSafeHtmlRenderer}.
     */
    public XDateCell() {
      this(DateTimeFormat.getFormat(PredefinedFormat.DATE_FULL),
          SimpleSafeHtmlRenderer.getInstance(), null);
    }

    /**
     * Construct a new {@link DateCell} using the format
     * {@link PredefinedFormat#DATE_FULL} and a {@link SimpleSafeHtmlRenderer}.
     *
     * @param renderer a non-null {@link SafeHtmlRenderer} used to render the
     *          formatted date as HTML
     */
    public XDateCell(SafeHtmlRenderer<String> renderer) {
      this(DateTimeFormat.getFormat(PredefinedFormat.DATE_FULL), renderer, null);
    }

    /**
     * Construct a new {@link DateCell} using the specified format and a
     * {@link SimpleSafeHtmlRenderer}.
     *
     * @param format the {@link DateTimeFormat} used to render the date
     */
    public XDateCell(DateTimeFormat format) {
      this(format, SimpleSafeHtmlRenderer.getInstance(), null);
    }

    /**
     * Construct a new {@link DateCell} using the specified format and the given
     * {@link SafeHtmlRenderer}.
     *
     * @param format the {@link DateTimeFormat} used to render the date
     * @param renderer a non-null {@link SafeHtmlRenderer} used to render the
     *          formatted date
     */
    public XDateCell(DateTimeFormat format, SafeHtmlRenderer<String> renderer) {
      this(format, renderer, null);
    }

    /**
     * Construct a new {@link DateCell} using the specified format and time zone.
     *
     * @param format the {@link DateTimeFormat} used to render the date
     * @param timeZone the {@link TimeZone} used to render the date, or null to
     *          use the default behavior for the local time zone and the rendered
     *          date. See {@link DateTimeFormat#format(Date)} and
     *          {@link Date#getTimezoneOffset()}
     */
    public XDateCell(DateTimeFormat format, TimeZone timeZone) {
      this(format, SimpleSafeHtmlRenderer.getInstance(), timeZone);
    }

    /**
     * Construct a new {@link DateCell} using the specified format, the given
     * {@link SafeHtmlRenderer}, and the specified time zone.
     *
     * @param format the {@link DateTimeFormat} used to render the date
     * @param renderer a non-null {@link SafeHtmlRenderer} used to render the
     *          formatted date
     * @param timeZone the {@link TimeZone} used to render the date, or null to
     *          use the default behavior for the local time zone and the rendered
     *          date. See {@link DateTimeFormat#format(Date)} and
     *          {@link Date#getTimezoneOffset()}
     */
    public XDateCell(DateTimeFormat format, SafeHtmlRenderer<String> renderer,
        TimeZone timeZone) {
      if (format == null) {
        throw new IllegalArgumentException("format == null");
      }
      if (renderer == null) {
        throw new IllegalArgumentException("renderer == null");
      }
      this.format = format;
      this.renderer = renderer;
      this.timeZone = timeZone;
    }

     
    @Override
    protected String formatValue(Date value) {
	return value == null ? null : format.format(value);
    }    
    
}
