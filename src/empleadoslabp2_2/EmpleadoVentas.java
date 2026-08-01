package empleadoslabp2_2;

import java.util.Calendar;

/**
 *
 * @author srjul
 */
public class EmpleadoVentas extends Empleado {

    private double[] ventasMensuales = new double[12];
    private double tasaComision;
    private int anioRegistro;

    public EmpleadoVentas(String codigo, String nombre, double salario, String foto) {
        super(codigo, nombre, salario, foto);
        this.tasaComision = 0.05;
        this.anioRegistro = Calendar.getInstance().get(Calendar.YEAR);
    }

    public double[] getVentasMensuales() {
        return ventasMensuales;
    }

    public double getTasaComision() {
        return tasaComision;
    }

    public void registrarVentas(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("Las ventas no pueden ser negativas");
        }
        Calendar ahora = Calendar.getInstance();
        int anioActual = ahora.get(Calendar.YEAR);
        if (anioActual != this.anioRegistro) {
            this.ventasMensuales = new double[12];
            this.anioRegistro = anioActual;
        }
        int mesActual = ahora.get(Calendar.MONTH);
        ventasMensuales[mesActual] += monto;
    }

    public double calcularComision() {
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        return ventasMensuales[mesActual] * tasaComision;
    }

    @Override
    public double calcularPago() {
        int horasTrabajadas = Math.min(this.horas, 160);
        double pago = (this.salario / 160.0) * horasTrabajadas;
        return pago + calcularComision();
    }

    public double calcularVentasAnuales() {
        double total = 0;
        for (double venta : ventasMensuales) {
            total += venta;
        }
        return total;
    }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() + " Ventas anuales: " + calcularVentasAnuales();
    }

}