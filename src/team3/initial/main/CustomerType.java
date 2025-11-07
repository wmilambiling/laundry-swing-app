
package team3.initial.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustomerType extends JFrame implements ActionListener{

    JRadioButton walkinButton;
    JRadioButton deliveryButton;
    //ImageIcon walkinIcon;
    //ImageIcon deliveryIcon;

    CustomerType(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        //walkinIcon = new ImageIcon("walkin.png");
        //deliveryIcon = new ImageIcon("delivery.png");

        walkinButton = new JRadioButton("Walk-In");
        deliveryButton = new JRadioButton("Delivery");

        ButtonGroup group = new ButtonGroup();
        group.add(walkinButton);
        group.add(deliveryButton);

        walkinButton.addActionListener(this);
        deliveryButton.addActionListener(this);


        //walkinButton.setIcon(walkinIcon);
        //deliveryButton.setIcon(deliveryIcon);


        this.add(walkinButton);
        this.add(deliveryButton);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==walkinButton) {
            System.out.println("Walk-In customer wants a laundry service.");
        }
        else {
            System.out.println("Customer wants his/her laundry delivered.");
        }
    }
}

