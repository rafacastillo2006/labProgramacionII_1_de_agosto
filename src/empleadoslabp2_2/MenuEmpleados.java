package empleadoslabp2_2;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;


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

        JPanel panelSuperior = new JPanel(new BorderLayout(20,0));
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

        JPanel panelDatos = new JPanel(new GridLayout(2, 1, 5, 5));
        panelDatos.setOpaque(false);

        JLabel lblNombre = new JLabel("Empleado: " + (empleadoActual != null ? empleadoActual.getNombre() : "N/A"));
        lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombre.setForeground(Color.WHITE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String fechaFormateada = "N/A";
        if (empleadoActual != null && empleadoActual.getFechacontratacion() != null) {
            fechaFormateada = sdf.format(empleadoActual.getFechacontratacion().getTime());

        }

        String detalles = String.format("Código: %s | Contratación: %s | Salario Base: $%.2f",
                empleadoActual != null ? empleadoActual.getCodigo() : "N/A",
                fechaFormateada,
                empleadoActual != null ? empleadoActual.getSalario() : 0.0);

        JLabel lblDetalles = new JLabel(detalles);
        lblDetalles.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDetalles.setForeground(Color.LIGHT_GRAY);

        panelDatos.add(lblNombre);
        panelDatos.add(lblDetalles);
        panelSuperior.add(foto, BorderLayout.WEST);
        panelSuperior.add(panelDatos, BorderLayout.CENTER);

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

        JPanel panelSalida = new  JPanel(new FlowLayout(FlowLayout.RIGHT,30,15));
        JButton volver = new JButton("Menu Principal");
        volver.setFont(fuenteBTNs);
        volver.setBackground(Color.RED);
        panelSalida.setBackground(Color.darkGray);
        panelSalida.add(volver);
        add(panelSalida, BorderLayout.SOUTH);

        registrarHoras.addActionListener(e -> {
            String entrada = JOptionPane.showInputDialog(
                    this,
                    "Horas acumuladas actuales:" + empleadoActual.getHoras() + "Ingresar horas trabajadas: ");

            if (entrada != null && !entrada.trim().isEmpty()){
                try{
                    int horas = Integer.parseInt(entrada);
                    empleadoActual.registrarHorasTrabajadas(horas);
                    JOptionPane.showMessageDialog(this, "Horas acumuladas actuales: " + empleadoActual.getHoras());
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Ingrese un numero valido.");
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Cuidado", JOptionPane.WARNING_MESSAGE);
                }
                }});

        calcularPago.addActionListener(e -> {
            double pago = empleadoActual.calcularPago();
            JOptionPane.showMessageDialog(this,
                    "Pago calculado, empleado: " + empleadoActual.getNombre() + ": $" + String.format("%.2f", pago),
                    "Cálculo de Pago", JOptionPane.INFORMATION_MESSAGE);
        });

        mostrarInfo.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, empleadoActual.mostrarInfo().toString(), "Información del Empleado", JOptionPane.INFORMATION_MESSAGE);
        });

        accionVentas.addActionListener(e -> {
            if (empleadoActual instanceof EmpleadoVentas) {
                String entrada = JOptionPane.showInputDialog(this, "Ingresar el monto de la venta:");
                if (entrada != null && !entrada.trim().isEmpty()) {
                    try {
                        double monto = Double.parseDouble(entrada);
                        ((EmpleadoVentas) empleadoActual).registrarVentas(monto);
                        JOptionPane.showMessageDialog(this, "Venta registrada. ", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Opcion solamente para vendedores. ", "Opción No Disponible", JOptionPane.WARNING_MESSAGE);
            }
        });

        volver.addActionListener(e -> {
            new Pantalla();
            dispose();
        });
        revalidate();
        repaint();
setVisible(true);
    }
}
