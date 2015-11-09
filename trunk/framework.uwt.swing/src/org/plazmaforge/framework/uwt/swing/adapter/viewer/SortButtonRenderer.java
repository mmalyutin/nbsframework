package org.plazmaforge.framework.uwt.swing.adapter.viewer;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class SortButtonRenderer implements TableCellRenderer {

    /**
     * Useful constant indicating NO sorting.
     */
    public static final int NONE = 0;

    /**
     * Useful constant indicating ASCENDING (that is, arrow pointing down) sorting in the table.
     */
    public static final int DOWN = 1;

    /**
     * Useful constant indicating DESCENDING (that is, arrow pointing up) sorting in the table.
     */
    public static final int UP = 2;

    /**
     * The current pressed column (-1 for no column).
     */
    private int pressedColumn = -1;

    /**
     * The three buttons that are used to render the table header cells.
     */
    private JButton normalButton;

    /**
     * The three buttons that are used to render the table header cells.
     */
    private JButton ascendingButton;

    /**
     * The three buttons that are used to render the table header cells.
     */
    private JButton descendingButton;

    /**
     * Used to allow the class to work out whether to use the buttuns
     * or labels. Labels are required when using the aqua look and feel cos the
     * buttons won't fit.
     */
    private boolean useLabels;

    /**
     * The normal label (only used with MacOSX).
     */
    private JLabel normalLabel;

    /**
     * The ascending label (only used with MacOSX).
     */
    private JLabel ascendingLabel;

    /**
     * The descending label (only used with MacOSX).
     */
    private JLabel descendingLabel;

    /**
     * Creates a new button renderer.
     */
    public SortButtonRenderer() {

        this.pressedColumn = -1;
        this.useLabels = UIManager.getLookAndFeel().getID().equals("Aqua");

        final Border border = UIManager.getBorder("TableHeader.cellBorder");

        if (this.useLabels) {
            this.normalLabel = new JLabel();
            this.normalLabel.setHorizontalAlignment(SwingConstants.LEADING);

            this.ascendingLabel = new JLabel();
            this.ascendingLabel.setHorizontalAlignment(SwingConstants.LEADING);
            this.ascendingLabel.setHorizontalTextPosition(SwingConstants.LEFT);
            this.ascendingLabel.setIcon(new BevelArrowIcon(BevelArrowIcon.DOWN, false, false));

            this.descendingLabel = new JLabel();
            this.descendingLabel.setHorizontalAlignment(SwingConstants.LEADING);
            this.descendingLabel.setHorizontalTextPosition(SwingConstants.LEFT);
            this.descendingLabel.setIcon(new BevelArrowIcon(BevelArrowIcon.UP, false, false));

            this.normalLabel.setBorder(border);
            this.ascendingLabel.setBorder(border);
            this.descendingLabel.setBorder(border);
        }
        else {
            this.normalButton = new JButton();
            this.normalButton.setMargin(new Insets(0, 0, 0, 0));
            this.normalButton.setHorizontalAlignment(SwingConstants.LEADING);

            this.ascendingButton = new JButton();
            this.ascendingButton.setMargin(new Insets(0, 0, 0, 0));
            this.ascendingButton.setHorizontalAlignment(SwingConstants.LEADING);
            this.ascendingButton.setHorizontalTextPosition(SwingConstants.LEFT);
            this.ascendingButton.setIcon(new BevelArrowIcon(BevelArrowIcon.DOWN, false, false));
            this.ascendingButton.setPressedIcon(new BevelArrowIcon(BevelArrowIcon.DOWN, false, true));

            this.descendingButton = new JButton();
            this.descendingButton.setMargin(new Insets(0, 0, 0, 0));
            this.descendingButton.setHorizontalAlignment(SwingConstants.LEADING);
            this.descendingButton.setHorizontalTextPosition(SwingConstants.LEFT);
            this.descendingButton.setIcon(new BevelArrowIcon(BevelArrowIcon.UP, false, false));
            this.descendingButton.setPressedIcon(new BevelArrowIcon(BevelArrowIcon.UP, false, true));

            this.normalButton.setBorder(border);
            this.ascendingButton.setBorder(border);
            this.descendingButton.setBorder(border);

        }

    }

    /**
     * Returns the renderer component.
     *
     * @param table      the table.
     * @param value      the value.
     * @param isSelected selected?
     * @param hasFocus   focussed?
     * @param row        the row.
     * @param column     the column.
     * @return the renderer.
     */
    public Component getTableCellRendererComponent(final JTable table,
                                                   final Object value,
                                                   final boolean isSelected,
                                                   final boolean hasFocus,
                                                   final int row, final int column) {

        if (table == null) {
            throw new NullPointerException("Table must not be null.");
        }

        final JComponent component;
        final SwingTableModel model = (SwingTableModel) table.getModel();
        final int cc = table.convertColumnIndexToModel(column);
        final boolean isSorting = (model.getSortColumn() == cc);
        final boolean isAscending = model.isAscending();

        final JTableHeader header = table.getTableHeader();
        final boolean isPressed = (cc == this.pressedColumn);

        if (this.useLabels) {
            final JLabel label = getRendererLabel(isSorting, isAscending);
            label.setText((value == null) ? "" : value.toString());
            component = label;
        }
        else {
            final JButton button = getRendererButton(isSorting, isAscending);
            button.setText((value == null) ? "" : value.toString());
            //button.getModel().setPressed(isPressed);
            //button.getModel().setArmed(isPressed);
            
            //button.getModel().setPressed(true);
            button.getModel().setArmed(true);

            component = button;
        }

        if (header != null) {
            //component.setForeground(header.getForeground());
            //component.setBackground(header.getBackground());
            //component.setFont(header.getFont());
        }
        return component;
    }

    /**
     * Returns the correct button component.
     *
     * @param isSorting whether the render component represents the sort column.
     * @param isAscending whether the model is ascending.
     * @return either the ascending, descending or normal button.
     */
    private JButton getRendererButton(final boolean isSorting, final boolean isAscending) {
        if (isSorting) {
            if (isAscending) {
                return this.ascendingButton;
            }
            else {
                return this.descendingButton;
            }
        }
        else {
            return this.normalButton;
        }
    }

    /**
     * Returns the correct label component.
     *
     * @param isSorting whether the render component represents the sort column.
     * @param isAscending whether the model is ascending.
     * @return either the ascending, descending or normal label.
     */
    private JLabel getRendererLabel(final boolean isSorting, final boolean isAscending) {
        if (isSorting) {
            if (isAscending) {
                return this.ascendingLabel;
            }
            else {
                return this.descendingLabel;
            }
        }
        else {
            return this.normalLabel;
        }
    }

    /**
     * Sets the pressed column.
     *
     * @param column the column.
     */
    public void setPressedColumn(final int column) {
        this.pressedColumn = column;
    }


}
