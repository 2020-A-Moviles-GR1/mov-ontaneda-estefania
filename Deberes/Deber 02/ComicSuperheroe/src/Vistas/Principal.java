package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Principal {
    private JButton CÓMICSButton;
    private JButton SUPERHÉROESButton;
    private JPanel principal;
    private JLabel comic;
    private JLabel superh;

    public Principal() {

        ImageIcon iconLogo = new ImageIcon("C:\\Users\\USER\\Documents\\EPN\\VIISEMESTRE\\Debres con fallas\\res\\comic.jpg");
        comic.setIcon(iconLogo);
        ImageIcon iconLogo2 = new ImageIcon("C:\\Users\\USER\\Documents\\EPN\\VIISEMESTRE\\Debres con fallas\\res\\superheroe.jpg");
        superh.setIcon(iconLogo2);


        CÓMICSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Principal");
        frame.setContentPane(new Principal().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public JPanel getPrincipal() {
        return principal;
    }
}
