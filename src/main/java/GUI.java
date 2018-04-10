/**
 * Created by jackzet on 10/04/2018.
 */

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GUI {


    JPanel pnPanel0;
    JLabel lbLabel0;
    JComboBox cmbCombo0;
    JLabel lbLabel2;
    JTextField tfT;
    JLabel lbLabel5;
    JTextField tfText2;
    JButton btBut1;
    JLabel lbLabel6;
    JTextField tfText3;

    public GUI(){
        pnPanel0 = new JPanel();
        pnPanel0.setBorder( BorderFactory.createTitledBorder( "" ) );
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        pnPanel0.setLayout( gbPanel0 );

        lbLabel0 = new JLabel( "Projektnavn:"  );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 5;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( lbLabel0, gbcPanel0 );
        pnPanel0.add( lbLabel0 );

        String []dataCombo0 = { "Treco ", "--- Solitaire", "--- --- GUI Design",
                "--- --- Data-structure", "--- Deutche Post" };
        cmbCombo0 = new JComboBox( dataCombo0 );
        gbcPanel0.gridx = 6;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 13;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( cmbCombo0, gbcPanel0 );
        pnPanel0.add( cmbCombo0 );

        lbLabel2 = new JLabel( "Antal timer:"  );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 6;
        gbcPanel0.gridwidth = 5;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( lbLabel2, gbcPanel0 );
        pnPanel0.add( lbLabel2 );

        tfT = new JTextField( );
        gbcPanel0.gridx = 7;
        gbcPanel0.gridy = 6;
        gbcPanel0.gridwidth = 3;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( tfT, gbcPanel0 );
        pnPanel0.add( tfT );

        lbLabel5 = new JLabel( "MedarbejderID"  );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 4;
        gbcPanel0.gridwidth = 5;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( lbLabel5, gbcPanel0 );
        pnPanel0.add( lbLabel5 );

        tfText2 = new JTextField( );
        gbcPanel0.gridx = 7;
        gbcPanel0.gridy = 4;
        gbcPanel0.gridwidth = 12;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( tfText2, gbcPanel0 );
        pnPanel0.add( tfText2 );

        btBut1 = new JButton( "Tilføj note"  );
        gbcPanel0.gridx = 4;
        gbcPanel0.gridy = 8;
        gbcPanel0.gridwidth = 11;
        gbcPanel0.gridheight = 2;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( btBut1, gbcPanel0 );
        pnPanel0.add( btBut1 );

        lbLabel6 = new JLabel( "Dato:"  );
        gbcPanel0.gridx = 10;
        gbcPanel0.gridy = 6;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( lbLabel6, gbcPanel0 );
        pnPanel0.add( lbLabel6 );

        tfText3 = new JTextField( );
        gbcPanel0.gridx = 14;
        gbcPanel0.gridy = 6;
        gbcPanel0.gridwidth = 5;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( tfText3, gbcPanel0 );
        pnPanel0.add( tfText3 );

    }



}
