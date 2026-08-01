
package empleadoslabp2_2;


import java.util.Calendar;

public class Main {


    public static void main(String[] args) {

        Empleado nuevo = new Empleado("12345", "Rafael Castillo", Calendar.getInstance().getTime(),15000,"");
new MenuEmpleados(nuevo);

    }
    
}
