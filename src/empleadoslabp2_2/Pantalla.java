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
        getContentPane().setBackground(Color.DARK_GRAY);

        JButton registrarEmpleado = new JButton("Registrar Empleado");
        JButton buscarEmpleado = new JButton("Buscar Empleado");
        JButton salir = new JButton("Salir");
        JButton[] listaBotones = {registrarEmpleado, buscarEmpleado, salir};
        Font fuenteBotones = new Font("Times New Roman", Font.BOLD, 30);

for(int i= 0;i<listaBotones.length;i++){
    listaBotones[i].setPreferredSize(new Dimension(400, 200));
    listaBotones[i].setBackground(Color.white);
    listaBotones[i].setFont(fuenteBotones);
}

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        gbc.gridy = 0;
        add(registrarEmpleado, gbc);
        gbc.gridy = 1;
        add(buscarEmpleado, gbc);
        gbc.gridy = 2;
        add(salir, gbc);

        registrarEmpleado.addActionListener(e -> {

        })

        buscarEmpleado.addActionListener(e -> {


        })

        salir.addActionListener(e -> {
            System.exit(0);
        })
        setVisible(true);
    }
}