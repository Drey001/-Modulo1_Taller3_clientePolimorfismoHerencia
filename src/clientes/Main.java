package clientes;

import clases.Clientes;
import clases.Empresas;
import clases.Personas;
import clases.Producto;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

    static ArrayList<Clientes> clientes = new ArrayList<>();
    public static void main(String[] args) {
        String menu =
                        "********************************************************************\n" +
                        "         * 1 Agregar cliente\n" +
                        "         * 2 Editar cliente\n" +
                        "         * 3 Eliminar cliente\n" +
                        "         * 4 Agregar productos\n" +
                        "         * 5 Consultar clientes con documento y tipo de documento \n" +
                        "         * 0 salir de la aplicacion \n" +
                        "********************************************************************\n";

        Scanner in = new Scanner(System.in);
        //Uso clico do while() para iniciar menu
        //@Deprecated(since = "1.2", forRemoval = true)
        Integer opMenu = new Integer(0);
        /**
         * Segun el concepto de polimorfismo un objeto puede tener multiples formas
         * con la estructura de clase que se entrega por favor implementar el concepto de poliformismo
         */
        do {

            System.out.println(menu);
            opMenu = in.nextInt();
            switch (opMenu.toString()) {
                case "1":
                    Integer opMenu2;
                    do {
                        System.out.println("Ingrese tipo de cliente :\n" +
                                "1. Empresa \n" +
                                "2. Persona \n" +
                                "3. Salir");
                        opMenu2 = in.nextInt();
                           switch (opMenu2.toString()){
                               case "1":
                                   clientes.add(crearEmpresa());
                                   opMenu2 = 3;
                                   break;
                               case "2":
                                   clientes.add(crearPersona() );
                                   opMenu2 = 3;
                                   break;
                               case "3":
                                   break;
                               default:
                                   System.out.println("Valor no válido \n");
                                   opMenu2 = in.nextInt();
                           }
                    } while (!opMenu2.toString().equals("3"));
                    break;
                case "2":
                    System.out.println("Ingrese la siguiente información: \n");
                    char tipoCliente = ' ';
                    int indice = buscaCliente(getCadena("Tipo ID cliente"),
                            getCadenaNum("Número de documento"));
                    if (indice != 99999 ){
                        tipoCliente = getTipoCliente(clientes.get(indice));
                        if (tipoCliente == 'E'){
                            updateEmpresa(indice);
                        }else if(tipoCliente == 'P'){
                            updatePersona(indice);
                        }
                    }else{
                        System.out.println("Cliente no existe");
                    }
                    break;
                case "3":
                    System.out.println("Ingrese los siguientes datos: ");
                    tipoCliente = '*';
                    indice = buscaCliente(getCadena("Tipo ID cliente"),
                            getCadenaNum("Número de documento"));
                    if (indice != 99999 ){
                        clientes.remove(indice);
                        System.out.println("Cliente eliminado exitosamente");
                    }else{
                        System.out.println("El cliente no existe ");
                    }
                    break;
                case "4":
                    System.out.println("Ingrese los siguientes datos: ");
                    tipoCliente = '*';
                    indice = buscaCliente(getCadena("Tipo ID cliente"),
                            getCadenaNum("Número de documento"));
                    if (indice != 99999 ){
                        System.out.println("Cliente " + clientes.get(indice).getNombre() + " seleccionado");
                        asociarProductos(indice);
                    }else{
                        System.out.println("El cliente no existe ");
                    }
                    break;
                case "5":
                    System.out.println("Ingrese los siguientes datos: ");
                    tipoCliente = '*';
                    indice = buscaCliente(getCadena("Tipo ID cliente"),
                            getCadenaNum("Número de documento"));
                    if (indice != 99999 ){
                        System.out.println(clientes.get(indice).toString());
                    }else{
                        System.out.println("El cliente no existe ");
                    }
                    break;
                case "0":
                    System.out.println("Muchas gracias por usar nuestra app, hasta luego");
                    break;
                default:
                    System.out.println("El valor ingresado no es una opcion de menu");
                    break;
            }
        } while (!opMenu.toString().equals("0"));
        in.close();
    }
    private static Clientes crearEmpresa(){
        System.out.println("Por favor ingrese los siguientes datos: ");
        Clientes cliente = new Empresas( getCadena("nombre"), getCadenaNum("telefono")
        , getCadena("dirección"), getCadena("Tipo documento"), getCadenaNum("Numero ID"),
                getCadena("Representante" ));
        System.out.println("Cliente tipo Empresa creado satisfactoriamente");
        return cliente;
    }
    private static Clientes crearPersona(){
        System.out.println("Por favor ingrese los siguientes datos: \n");
        Clientes cliente = new Personas(getCadena("Nombre"), getCadenaNum("telefono")
                , getCadena("dirección"), getCadenaNum("Cédula"),
                getCadenaNum("Celular"));
        System.out.println("Cliente tipo Persona creado satisfactoriamente");
        return cliente;
    }
    private static String getCadena(String mensaje){
        Scanner scan = new Scanner(System.in);
        System.out.println(mensaje);
        return scan.nextLine();
    }
    private static String getCadenaNum(String mensaje){
        Scanner scan2 = new Scanner(System.in);
        String valor;
        boolean esNume=true;
        do{
            System.out.println("Ingrese el/la: " + mensaje);
            valor = scan2.nextLine();
            esNume = validarNumerico(valor);
        }while(!esNume);
        return valor;
    }
    private static boolean validarNumerico(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
    }
    private static char getTipoCliente(Clientes cliente){
        char instancia = '*';
        if ( cliente instanceof Empresas);
            instancia = 'E';
        if (cliente instanceof Personas);
            instancia = 'P';
        return instancia;
    }

    private static int buscaCliente(String tipoId, String numId){
        int indice = 99999;
        for ( int i = 0; i < clientes.size(); i++){
            if (clientes.get(i) instanceof Empresas){
               Empresas empresas = ((Empresas) clientes.get(i));
               if ( tipoId.equals(empresas.getTipDoc()) && ( numId.equals(empresas.getDocumento()))){
                   indice = i;
                   break;
               }
            }else if (clientes.get(i) instanceof Personas){
                Personas personas = ((Personas) clientes.get(i));
                if ( numId.equals(personas.getCedula()) && (tipoId.toUpperCase().equals("CC"))){
                    indice = i;
                }
            }
        }
        return indice;
    }

    private static void asociarProductos(int indice){
        String conti;
        List<Producto> productos = new ArrayList<>();
        do{
            Producto producto = new Producto(getCadena("Nombre"),getCadena("Caracteristicas"),
                    getCadena("Condiciones"));
            productos.add(producto);
            //clientes.get(indice).addProductos(producto);
            conti = getCadena("Desea continuar S/N");
        }while(conti.toUpperCase().equals("S"));
        clientes.get(indice).setProductos(productos);
        System.out.println(clientes.get(indice).getProductos());
    }

    private static void updateEmpresa(int indice){
        String conti1 = "0";
        Empresas empresa = ((Empresas) clientes.get(indice));
        do{
            System.out.println("Seleccione el parámetro a actualizar: \n" +
                    "***************************\n" +
                    "1. Nombre \n" +
                    "2. Teléfono \n" +
                    "3. Direccion \n" +
                    "4. Tipo Documento \n" +
                    "5. Documento \n" +
                    "6. Representante \n" +
                    "0. Salir \n" +
                    "***************************\n");
            conti1 = getCadenaNum("Ingrese opción: ");
            switch(conti1){
                case "1":
                    empresa.setNombre(getCadena("Ingrese el nuevo nombre"));
                    break;
                case "2":
                    empresa.setTelefono(getCadenaNum("Ingrese el nuevo teléfono"));
                    break;
                case "3":
                    empresa.setDireccion(getCadena("Ingrese nueva dirección"));
                    break;
                case "4":
                    empresa.setTipDoc(getCadena("Ingrese nuevo tipo de documento"));
                    break;
                case "5":
                    empresa.setDocumento(getCadenaNum("Ingrese nuevo documento"));
                    break;
                case "6":
                    empresa.setRepresentante(getCadena("Ingrese nuevo representante"));
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opcion no válida");
                    break;
            }
        }while(!conti1.equals("0"));
    }
    private static void updatePersona(int indice){
        String conti1 = "0";
        Personas persona = ((Personas) clientes.get(indice));
        do{
            System.out.println("Seleccione el parámetro a actualizar: \n" +
                    "******************** \n" +
                    "1. Nombre \n" +
                    "2. Teléfono \n" +
                    "3. Direccion \n" +
                    "4. Cédula \n" +
                    "5. Celular \n" +
                    "0. Salir \n" +
                    "******************** \n");
            conti1 = getCadenaNum("Ingrese opción: ");
            switch(conti1){
                case "1":
                    persona.setNombre(getCadena("Ingrese nuevo nombre"));
                    break;
                case "2":
                    persona.setTelefono(getCadenaNum("Ingrese nuevo teléfono"));
                    break;
                case "3":
                    persona.setDireccion(getCadena("Ingrese nueva dirección"));
                    break;
                case "4":
                    persona.setCedula(getCadenaNum("Ingrese nueva cédula"));
                    break;
                case "5":
                    persona.setCelular(getCadenaNum("Ingrese nuevo celular"));
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opcion no válida");
                    break;
            }
        }while(!conti1.equals("0"));
    }
}
