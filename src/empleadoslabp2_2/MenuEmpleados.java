package empleadoslabp2_2;

import javax.swing.*;
import java.awt.*;


public class MenuEmpleados extends JFrame {

    private Empleado empleadoActual;

    public MenuEmpleados(Empleado empleado) {

        this.empleadoActual = empleado;

        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20,20));

        JPanel panelSuperior = new JPanel(new FlowLayout.LEFT,25,15);
        panelSuperior.setBackground(Color.white);

        JLabel foto = new JLabel();
        foto.setPreferredSize(new Dimension(90,90));
        foto.setBorder(BorderFactory.createLineBorder(Color.black));

        if (empleadoActual != null && !empleadoActual.getFoto().isEmpty()) {
            ImageIcon icon = new ImageIcon(new ImageIcon(empleadoActual.getFoto())
                    .getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
            foto.setIcon(icon);
        }
        else{
            foto.setText("Sin fotografia");
            foto.setForeground(Color.white);
            foto.setHorizontalAlignment(SwingConstants.CENTER);
        }

    }
}
