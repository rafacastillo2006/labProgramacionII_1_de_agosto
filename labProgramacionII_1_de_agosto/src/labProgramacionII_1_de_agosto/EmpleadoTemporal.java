
package labProgramacionII_1_de_agosto;

import java.util.Date;

public class EmpleadoTemporal extends Empleado {
    private Date fechafin;
    
    public EmpleadoTemporal(String codigo, String nombre, Date Fechacontratacion, double salario, String foto, Date fechafin){
        super(codigo,nombre, Fechacontratacion, salario, foto);
        this.fechafin=fechafin;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }
    
    @Override
    public double calcularPago(){
        Date fechaactual= new Date();
        
        if(fechafin !=null && !fechaactual.after(fechafin)){
            int horastrabajadas=Math.min(this.horas, 160);
            return (this.salario/160.0)* horastrabajadas;
        }
        return 0;
    }
    
    @Override
    public String mostrarinfo(){
        return super.mostrarinfo()+ "Fin de contrato: "+fechafin;
    }
    
}
