package empleadoslabp2_2;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Pantalla extends JFrame {

    private static Empresa empresa = new Empresa("Empresa Programación 2");

    public Pantalla() {
        setTitle("Gestor de Empleados - " + empresa.getNombre());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.DARK_GRAY);

        JButton registrarEmpleado = new JButton("Registrar Empleado");
        JButton buscarEmpleado = new JButton("Buscar Empleado");
        JButton generarReporte = new JButton("Generar Reporte");
        JButton salir = new JButton("Salir");
        JButton[] listaBotones = {registrarEmpleado, buscarEmpleado, generarReporte, salir};
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
        gbc.gridy = 3;
        add(generarReporte, gbc);
        gbc.gridy = 4;
        add(salir, gbc);

        registrarEmpleado.addActionListener(e -> {
            String[] tipos = {"Estandar", "Ventas", "Temporal"};
            String seleccionTipo = (String) JOptionPane.showInputDialog(
                    this,
                    "Tipo de empleado a registrar:",
                    "Tipo de Empleado",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tipos,
                    tipos[0]
            );

            if (seleccionTipo != null) {
                try {
                    String codigo = JOptionPane.showInputDialog(this, "Crear código para el empleado:");
                    if (codigo == null || codigo.trim().isEmpty()) return;

                    String nombre = JOptionPane.showInputDialog(this, "Ingresar nombre del empleado:");
                    if (nombre == null || nombre.trim().isEmpty()) return;

                    String salarioStr = JOptionPane.showInputDialog(this, "ingresar salario base:");
                    if (salarioStr == null || salarioStr.trim().isEmpty()) return;
                    double salario = Double.parseDouble(salarioStr);

                    String foto = JOptionPane.showInputDialog(this, "Fotografia:");
                    if (foto == null) foto = "";

                    Empleado nuevoEmpleado = null;

                    if (seleccionTipo.equals("Estándar")) {
                        nuevoEmpleado = new Empleado(codigo, nombre, salario, foto);
                    } else if (seleccionTipo.equals("Ventas")) {
                        nuevoEmpleado = new EmpleadoVentas(codigo, nombre, salario, foto);

                    } else if (seleccionTipo.equals("Temporal")) {
                        String mesesStr = JOptionPane.showInputDialog(this, "Duración del contrato (meses):");
                        int meses = Integer.parseInt(mesesStr);

                        Calendar fechaFin = Calendar.getInstance();
                        fechaFin.add(Calendar.MONTH, meses);

                        nuevoEmpleado = new EmpleadoTemporal(codigo, nombre, salario, foto, fechaFin);
                    }

                    if (nuevoEmpleado != null) {
                        empresa.registrarEmpleado(nuevoEmpleado);
                        JOptionPane.showMessageDialog(this, "Empleado Registrado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error en los datos numéricos ingresados.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al Registrar", JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        buscarEmpleado.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this, "Ingrese el código del empleado a buscar:");
            if (codigo != null && !codigo.trim().isEmpty()) {
                try {
                    Empleado empEncontrado = empresa.buscarPorCodigo(codigo);
                    new MenuEmpleados(empEncontrado);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Empleado no encontrado", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        salir.addActionListener(e -> {
            System.exit(0);
        });

        generarReporte.addActionListener(e -> {
            String reporte = empresa.generarReporte();
            JTextArea textArea = new JTextArea(reporte);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 300));

            JOptionPane.showMessageDialog(this, scrollPane, "Reporte Empresa", JOptionPane.INFORMATION_MESSAGE);
        });
        setVisible(true);
    }
}