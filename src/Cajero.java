import java.util.Date;
import java.util.Scanner;

public class Cajero {

    static double cantidadDisponible = 10000;
    static String ultimoMovimiento = "No se han registrado movimientos ";
//MENU DEL CAJERO
    public static void main(String[] args) {
        Scanner menu = new Scanner(System.in);
        String mensajeMenu = "BIENVENIDO AL CAJERO DEL BANCO RATA%n" +
                "Seleccione un número para indicar la operación deseada%n" +
                "1 -> Retirar dinero%n" +
                "2 -> Realizar depósito%n" +
                "3 -> Ver estado de cuenta%n" +
                "4 -> Quejas%n" +
                "5 -> Ver último movimiento%n" +
                "6 -> Hablar con un asesor%n" +
                "7 -> Salir del cajero%n";

        boolean solicitudTermino = false;
        int conteoErrores = 0;

//EJECUCIÓN SEGÚN ELECCIÓN DEL MENÚ
        do {
            System.out.printf(mensajeMenu);
            int eleccionMenu = menu.nextInt();

            switch (eleccionMenu) {
                case 1 -> {
                    retirarDinero();
                    System.out.println("**********************************************");
                }
                case 2 -> {
                    depositoDinero();
                    System.out.println("**********************************************");
                }
                case 3 -> {
                    System.out.println("ESTADO DE CUENTA \n  " +
                            "Tienes $" + cantidadDisponible + " disponible");
                    System.out.println("**********************************************");
                }
                case 4 -> solicitudTermino = true; //No contestar y finalizar sesión
                case 5 -> {
                    System.out.println(ultimoMovimiento);//Llamar a variable ultimo movimiento
                    System.out.println("**********************************************");
                }
                case 6 -> {
                    System.out.println("No hay asesores disponibles en este momento. Solicitud fuera de horario de atencion.");
                    System.out.println("**********************************************");
                }
                case 7 -> {
                    System.out.println("CERRANDO SESIÓN");
                    System.out.println("**********************************************");
                    solicitudTermino = true;
                }
                default -> {
                    System.out.println("ERROR: Opción desconocida");
                    System.out.println("**********************************************");
                    conteoErrores = conteoErrores + 1;
                    if (conteoErrores >= 3) {
                        solicitudTermino = true;
                    }
                }
            }
        } while (solicitudTermino == false);

    }
//FUNCIONES QUE SE LLAMAN EN EL DO- WHILE
    static Scanner scanner = new Scanner(System.in);


    public static void retirarDinero() {
        System.out.println("Cantidad disponible para retiro " + cantidadDisponible);
        System.out.println("Ingrese cantidad que desea retirar");
        double cantidad = scanner.nextDouble();

        if (cantidad > cantidadDisponible || cantidad > 6000 || cantidad % 50 != 0 || cantidad % 100 != 0) {
            System.out.println("No es posible realizar el retiro, recuerde que: \n  " +
                    "- No se puede retirar más de $6000 \n  " +
                    "- Solo se puede retirar múltiplos de 50 y 100 ");
        } else {
            cantidadDisponible -= cantidad;
            ultimoMovimiento = "Se ha hecho un retiro por: " + cantidad + " " + new Date();
            System.out.println("Ha realizado un retiro exitosamente ");
            System.out.println("Cantidad disponible: " + cantidadDisponible);
        }
    }

     public static void  depositoDinero(){
         System.out.println("Seleccione un número para indicar dónde desea realizar el depósito \n"
                 + "1 -> Cuenta de cheques \n"
                 + "2 -> Tarjeta de crédito (Pagar tarjeta) \n"
                 + "3 -> Cuenta de terceros \n");

            int eleccionSubMenu = scanner.nextInt();
             switch (eleccionSubMenu){
                 case 1: //(sumar la cantidad a nuestra cuenta).
                     // Solo se puede depositar en múltiplos de 50 y 100.
                     System.out.println("Ingrese cantidad que desee depositar");
                     double abonoCuenta = scanner.nextDouble();
                     if (abonoCuenta % 50 == 0 || abonoCuenta % 100 == 0) {
                         cantidadDisponible += abonoCuenta;
                         System.out.println("Abono realizado con éxito");
                         ultimoMovimiento = "Se ha hecho un abono a la Cuenta por: " + abonoCuenta + " " + new Date();
                     } else {
                         System.out.println("Sólo es posible depositar multiplos de 50 y 100" );
                     }
                     break;
                 case 2: //(restar la cantidad).
                     // Hacer pago de $200.10 No retirar una cantidad mayor a la disponible
                     System.out.println("Presenta una deuda de $200.10 en su tarjeta de crédito \n"  +
                             "Ingrese la cantidad que desea abonar en su tarjeta");
                     double abonoTarjeta = scanner.nextDouble();
                     if(abonoTarjeta < cantidadDisponible){
                         cantidadDisponible -= abonoTarjeta;
                         ultimoMovimiento = "Se ha hecho un retiro por: " + abonoTarjeta + " " + new Date();
                     } else {
                         System.out.println("No es posible retirar de su cuenta un monto superior al disponible");
                     }
                     break;
                 case 3://  (restar la cantidad)
                     // Hacer transferencia de $666.22 No retirar una cantidad mayor a la disponible.
                     System.out.println("Ingrese la cantidad que desee transferir a la cuenta n°********");
                     double transferencia = scanner.nextDouble();
                     if(transferencia < cantidadDisponible){
                         cantidadDisponible -= transferencia;
                         ultimoMovimiento = "Se ha hecho un retiro por: " + transferencia + " " + new Date();
                     } else {
                         System.out.println("No es posible retirar de su cuenta un monto superior al disponible");
                     }
                     break;
             }


        }


}