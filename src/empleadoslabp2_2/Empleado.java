
package labProgramacionII_1_de_agosto;

import java.util.Calendar;
import java.util.Date;
public class Empleado {
    protected String codigo;
    protected String nombre;
    protected Date Fechacontratacion;
    protected double salario;
    protected int horas;
    protected String foto;
    
    public Empleado(String codigo, String nombre, Date Fechacontratacion,double salario, String foto){
        this.codigo=codigo;
        this.nombre= nombre;
        if (Fechacontratacion != null) {
        this.Fechacontratacion = Fechacontratacion;
        } else {
            this.Fechacontratacion = new Date();
        }
        this.salario=salario;
        this.horas=0;
        this.foto=foto;
        }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechacontratacion() {
        return Fechacontratacion;
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
    public String mostrarinfo(){
        return "Codigo: "+ codigo + " Nombre: " +nombre + "Contratacion: "+Fechacontratacion;
    }
}
