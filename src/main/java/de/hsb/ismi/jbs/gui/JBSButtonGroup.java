/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

/**
 * A ButtonGroup for check-boxes enforcing that at least one remains selected.
 * 
 * When the group has exactly two buttons, deselecting the last selected one
 * automatically selects the other.
 * 
 * When the group has more buttons, deselection of the last selected one is denied.<br><br>
 * 
 * Original code taken from: <a href="http://stackoverflow.com/a/14892517">Stackoverflow | Tibi</a>
 * @author Kevin Kuegler
 * @version 1.00
 *
 */
public class JBSButtonGroup extends ButtonGroup {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5572863094402947727L;
	private final Set<ButtonModel> selected = new HashSet<>();

    @Override
    public void setSelected(ButtonModel model, boolean b) {
        if (b && !this.selected.contains(model) ) {
            select(model, true);
        } else if (!b && this.selected.contains(model)) {
            if (this.buttons.size() == 3 && this.selected.size() == 2) {
                select(model, false);
                AbstractButton other = this.buttons.get(0).getModel() == model ? this.buttons.get(1) : this.buttons.get(0);
                select(other.getModel(), true);
            } else if (this.selected.size() > 2) {
                this.selected.remove(model);
                model.setSelected(false);
            }
        }
    }

    /**
     * 
     * @param model
     * @param b
     */
    private void select(ButtonModel model, boolean b) {
        if (b) {
            this.selected.add(model);
        } else {
            this.selected.remove(model);
        }
        model.setSelected(b);
    }

    @Override
    public boolean isSelected(ButtonModel m) {
        return this.selected.contains(m);
    }

    /**
     * 
     * @param buttons
     */
    public void addAll(AbstractButton... buttons) {
        for (AbstractButton button : buttons) {
            add(button);
        }
    }

    @Override
    public void add(AbstractButton button) {
        if (button.isSelected()) {
            this.selected.add(button.getModel());
        }
        super.add(button);
    }
}