package empleadoslabp2_2;

import javax.swing.*;
import java.awt.*;

public class Pantalla extends JFrame {

    public Pantalla() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.DARK_GRAY); // Color de fondo visible

        JButton registrarEmpleado = new JButton("Registrar Empleado");
        JButton buscarEmpleado = new JButton("Buscar Empleado");
        JButton[] listaBotones = {registrarEmpleado, buscarEmpleado};
        Font fuenteBotones = new Font("Times New Roman", Font.BOLD, 30);

for(int i= 0;i<listaBotones.length;i++){
    listaBotones[i].setPreferredSize(new Dimension(400, 200));
    listaBotones[i].setBackground(Color.white);
    listaBotones[i].setFont(fuenteBotones);
}

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen/espaciado entre botones
        gbc.gridx = 0; // Columna 0

        gbc.gridy = 0;
        add(registrarEmpleado, gbc);

        gbc.gridy = 1;
        add(buscarEmpleado, gbc);

        setVisible(true);
    }
}