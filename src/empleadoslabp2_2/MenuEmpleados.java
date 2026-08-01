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

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT,25,15));
        panelSuperior.setBackground(Color.darkGray);

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

        String infoTexto = String.format(
                "Empleado: %s\nCódigo: %s | Contratación: %s | Salario Base: $%.2f",
                empleadoActual.getNombre(),
                empleadoActual.getCodigo(),
                empleadoActual.getFechacontratacion(),
                empleadoActual.getSalario());

        JTextArea textoDatos = new JTextArea(infoTexto);
        textoDatos.setFont(new Font("Arial", Font.BOLD, 14));
        textoDatos.setForeground(Color.WHITE);
        textoDatos.setOpaque(false);
        textoDatos.setEditable(false);

        panelSuperior.add(foto);
        panelSuperior.add(textoDatos);
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel(new GridLayout(2,2,25,25));
        panelOpciones.setBackground(Color.darkGray);
        panelOpciones.setBorder(BorderFactory.createEmptyBorder(50,80,50,80));

        JButton registrarHoras = new JButton("Registrar horas");
        JButton calcularPago  = new JButton("Calcular Pago");
        JButton mostrarInfo = new  JButton("Ver Información");
        JButton accionVentas = new JButton("Registrar Ventas");

        Font fuenteBTNs = new Font("Times New Roman", Font.BOLD, 14);
        JButton[] botonesMenu = {registrarHoras, calcularPago, mostrarInfo, accionVentas};

        for (int i  = 0; i < botonesMenu.length; i++) {
            botonesMenu[i].setFont(fuenteBTNs);
            botonesMenu[i].setBackground(Color.WHITE);
            panelOpciones.add(botonesMenu[i]);
        }

        add(panelOpciones,  BorderLayout.CENTER);

setVisible(true);
    }
}
