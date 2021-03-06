/*
 * YajHFC - Yet another Java Hylafax client
 * Copyright (C) 2009 Jonas Wolz
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package example.plugin;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import yajhfc.FaxOptions;
import yajhfc.options.AbstractOptionsPanel;
import yajhfc.options.OptionsWin;

/**
 * Implements a crude and simple UI to set the three example options.
 * 
 * @author jonas
 *
 */
public class ExampleOptionsPanel extends AbstractOptionsPanel<FaxOptions> {   
    
    public ExampleOptionsPanel() {
		super(false);
	}

	JTextField textOption1, textOption2;
    
    JCheckBox checkOption3;
    
    @Override
    protected void createOptionsUI() {    
    	setLayout(new FlowLayout(FlowLayout.LEFT));
    	textOption1 = new JTextField();
    	textOption2 = new JTextField();
    	
    	checkOption3 = new JCheckBox("Option 3");
    	
    	add(new JLabel("Option 1:"));
    	add(textOption1);
    	
    	add(new JLabel("Option 2:"));
    	add(textOption2);
    	
    	add(checkOption3);
    }
    
    /* (non-Javadoc)
     * @see yajhfc.options.OptionsPage#loadSettings(yajhfc.FaxOptions)
     */
    public void loadSettings(FaxOptions foEdit) {
    	// You can just ignore foEdit
    	
        ExampleOptions eo = EntryPoint.getOptions();
        
        textOption1.setText(eo.exampleOption1);
        textOption2.setText(String.valueOf(eo.exampleOption2));
        checkOption3.setSelected(eo.exampleOption3);
    }

    /* (non-Javadoc)
     * @see yajhfc.options.OptionsPage#saveSettings(yajhfc.FaxOptions)
     */
    public void saveSettings(FaxOptions foEdit) {
    	// You can just ignore foEdit
        ExampleOptions eo = EntryPoint.getOptions();
        
        eo.exampleOption1 = textOption1.getText();
        eo.exampleOption2 = Integer.parseInt(textOption2.getText());
        eo.exampleOption3 = checkOption3.isSelected();

    }

    /* (non-Javadoc)
     * @see yajhfc.options.OptionsPage#validateSettings(yajhfc.options.OptionsWin)
     */
    public boolean validateSettings(OptionsWin optionsWin) {
        try {
            int val = Integer.parseInt(textOption2.getText());
            if (val < 1 || val > 1000) {
                JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 1000!");
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 1000!");
            return false;
        }
        
        // If you do not need to validate settings, always return true
        return true;
    }

}
