package empleadoslabp2_2;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Empleado {
    
    protected String codigo;
    protected String nombre;
    protected Calendar fechaContratacion;
    protected double salario;
    protected int horas;
    protected String foto;
    
    public Empleado(String codigo, String nombre, double salario, String foto){
        this.codigo=codigo;
        this.nombre= nombre;
        this.salario=salario;
        fechaContratacion = Calendar.getInstance();
        this.horas=0;
        this.foto=foto;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Calendar getFechacontratacion() {
        return fechaContratacion;
    }

    public double getSalario() {
        return salario;
    }

    public int getHoras() {
        return horas;
    }

    public String getFoto() {
        return foto;
    }
    
    public void registrarHorasTrabajadas(int horas){
        if (horas <0){
            throw new IllegalArgumentException("Las horas trabajadas no pueden ser negativas");
        }
        this.horas=horas;
    }
    
    public double calcularPago(){
        int horastrabajadas;
        double pago, deduccion;
        horastrabajadas=Math.min(this.horas, 160);
        pago=(this.salario/160.0) * horastrabajadas;
        deduccion= this.salario * 0.035;
        return Math.max(0, pago-deduccion);
    }
    
    public String mostrarInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String fechaFormateada = (fechaContratacion != null) ? sdf.format(fechaContratacion.getTime()) : "N/A";

        return String.format("Código: %s | Nombre: %s | Fecha Contratación: %s | Salario: $%.2f | Horas: %d",
                codigo, nombre, fechaFormateada, salario, horas);
    }
    
}
