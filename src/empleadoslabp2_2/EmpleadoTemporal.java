package empleadoslabp2_2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author srjul
 */
public class EmpleadoTemporal extends Empleado {
    
    private Calendar fechaFin;
    
    public EmpleadoTemporal(String codigo, String nombre, double salario, String foto, Calendar fechaFin) {
        super(codigo, nombre, salario, foto);
        this.fechaFin = fechaFin;
    }
    
    public void actualizarFechaFinContrato(Calendar nf) {
        this.fechaFin = nf;
    } 
    
    private boolean esIgual(Calendar c1, Calendar c2) {
        if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) {
            return false;
        } 
        
        if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH)) {
            return false;
        }
        return c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }
    
    @Override
    public double calcularPago(){
        
        int horastrabajadas;
        double pago, deduccion;
        horastrabajadas=Math.min(this.horas, 160);
        
        Calendar fechaActual = Calendar.getInstance(); 
       
        if (fechaActual.before(fechaFin) || (esIgual(fechaActual, fechaFin))) { 
            return (this.salario/160.0) * horastrabajadas;
        }
        
        return 0;
    }
    
    @Override
    public String mostrarInfo() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formato.format(fechaFin.getTime());
        return super.mostrarInfo().concat(" Fecha Final de Contratación: " + fechaFormateada);
    }
    
}
