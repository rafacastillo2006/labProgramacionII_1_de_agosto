
package empleadoslabp2_2;

/**
 *
 * @author mario
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

public class Empresa {

    private final String nombre;
    private final List<Empleado> empleados = new ArrayList<>();

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void registrarEmpleado(Empleado empleado) {
        if (existeCodigo(empleado.getCodigo())) {
            throw new IllegalStateException("Ya existe un empleado registrado con el código: " + empleado.getCodigo());
        }
        empleados.add(empleado);
    }

    private boolean existeCodigo(String codigo) {
        for (Empleado e : empleados) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    public Empleado buscarPorCodigo(String codigo) {
        for (Empleado e : empleados) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return e;
            }
        }
        throw new NoSuchElementException("No se encontró ningún empleado con el código: " + codigo);
    }

    public void registrarHorasTrabajadas(String codigo, int horas) {
        Empleado empleado = buscarPorCodigo(codigo);
        empleado.registrarHorasTrabajadas(horas);
    }

    public void registrarVentas(String codigo, double monto) {
        Empleado empleado = buscarPorCodigo(codigo);
        if (!(empleado instanceof EmpleadoVentas)) {
            throw new IllegalArgumentException(
                    "El empleado " + codigo + " (" + empleado.getNombre() + ") no es de tipo Ventas.");
        }
        ((EmpleadoVentas) empleado).registrarVentas(monto);
    }

    public void actualizarFechaFinContrato(String codigo, Calendar nuevaFecha) {
        Empleado empleado = buscarPorCodigo(codigo);
        if (!(empleado instanceof EmpleadoTemporal)) {
            throw new IllegalArgumentException(
                    "El empleado " + codigo + " (" + empleado.getNombre() + ") no es de tipo Temporal.");
        }
        ((EmpleadoTemporal) empleado).actualizarFechaFinContrato(nuevaFecha);
    }

    public double calcularPagoMensual(String codigo) {
        Empleado empleado = buscarPorCodigo(codigo);
        return empleado.calcularPago();
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reporte de empleaos-").append(nombre).append("\n");


        int estandar = 0, temporal = 0, ventas = 0;

        sb.append("Empleados Standard ");
        for (Empleado e : empleados) {
            if (e.getClass() == Empleado.class) {
                estandar++;
                sb.append(detalleEmpleado(e)).append("\n");
            }
        }
        sb.append("Empleados que son temporales");
        for (Empleado e : empleados) {
            if (e instanceof EmpleadoTemporal) {
                temporal++;
                sb.append(detalleEmpleado(e)).append("\n");
            }
        }
        sb.append("Empleados en ventas");
        for (Empleado e : empleados) {
            if (e instanceof EmpleadoVentas) {
                ventas++;
                sb.append(detalleEmpleado(e)).append("\n");
            }
        }

        sb.append("Resumen de empleados");
        sb.append(String.format("Empleados estándar : %d%n", estandar));
        sb.append(String.format("Empleados temporales: %d%n", temporal));
        sb.append(String.format("Empleados de ventas : %d%n", ventas));
        sb.append(String.format("Total de empleados  : %d%n", empleados.size()));
        return sb.toString();
    }

    private String detalleEmpleado(Empleado e) {
        return String.format("%s%n   Horas trabajadas: %d | Salario base: %.2f | Pago calculado: %.2f",
                e.mostrarInfo(), e.getHoras(), e.getSalario(), e.calcularPago());
    }
}