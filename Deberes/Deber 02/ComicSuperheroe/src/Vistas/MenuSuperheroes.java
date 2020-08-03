package Vistas;

import javax.swing.*;

public class MenuSuperheroes {
    private JPanel menuSuperh;
    private JButton CREARSUPERHEROEButton;
    private JButton BUSCARSUPERHEROEButton;
    private JButton VERTODOSButton;
    private JButton MATARSUPERHEROEButton;
    private JButton MENUPRINCIPALButton;
    private JButton ACTUALIZARFUERZAButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuSuperheroes");
        frame.setContentPane(new MenuSuperheroes().menuSuperh);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getMenuSuperh() {
        return menuSuperh;
    }
}
