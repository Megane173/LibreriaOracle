/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAOs.Libro;
import DAOs.TableModel;
import Service.LibreriaService;
import Vista.VistaLibreria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author PC1Ry
 */
public class LibreriaController {

    private final LibreriaService ls = new LibreriaService();
    private final Scanner sc = new Scanner(System.in);
    private final VistaLibreria vista = new VistaLibreria();

    
    public LibreriaController(){
        iniciarEventos();
        vista.setVisible(true);
    }
    
    private void iniciarEventos(){
        
        String [] acciones = {"Mostrar todas las filas"};
        
        vista.llenarAcciones(acciones);
        vista.llenarTablasDeAccion(ls.getTablesNames());
        vista.setVisibleBotonAccionComleja(false);
        vista.getEjecutar().addActionListener(e->{
            String accion= vista.getSelectedAccion();
            switch(accion){
                case "Mostrar todas las filas":
                    showAll((String) vista.getSelectedTable());
                    break;
            }
        });
    }
    
    public void showAll(String tabla){
        
        tabla=tabla.toLowerCase();
        if(tabla.isEmpty()){
            vista.showError("No selecciono la tabla a la cual le quiere obtener todas las filas");
        }else{
            List resultados;
            switch(tabla){
                
                case "libro":
                    resultados = new ArrayList<TableModel>(ls.getAllBooks());
                    vista.llenarTabla(resultados, ls.getColumns(resultados));
                    break;
                case "editorial":
                    resultados = new ArrayList<TableModel>(ls.getAllEditorial());
                    vista.llenarTabla(resultados, ls.getColumns(resultados));
                    break;
                case "autor":
                    resultados = new ArrayList<TableModel>(ls.getAllAutor());
                    vista.llenarTabla(resultados, ls.getColumns(resultados));
                    break;
                case "pais":
                    resultados = new ArrayList<TableModel>(ls.getAllPais());
                    vista.llenarTabla(resultados, ls.getColumns(resultados));
                    break;
            }
        }
    }
    
    
    
    //*************************************************************************
    //------------------------Controlador pasado-------------------------------

    /*
    public void bucleDeProceso() {

        String input = "inicio";
        Scanner sc = new Scanner(System.in);
        while (!input.equals("0")) {
            mostrarMenu();
            System.out.println("Digite el caracter asociado a la accion que quiere realizar: ");
            input = sc.nextLine();
            switch (input) {
                case "8":
                    insertPais();
                    break;
                case "7":
                    insertEditorial();
                    break;
                case "6":
                    insertAutor();
                    break;
                case "5":
                    insertBook();
                    break;
                case "4":
                    showAllPais();
                    break;
                case "3":
                    showAllEditorial();
                    break;
                case "2":
                    showAllAutor();
                    break;
                case "1":
                    showAllBooks();
                    break;
                case "0":
                    salir();
                    break;
                default:
                    funcionDesconocida();
                    break;
            }
        }
    }
    
    //-------------------------------------------------------------------------
    //Funciones disponibles para el ususario y mostradas por el controlador
    //-------------------------------------------------------------------------
    
    public void funcionDesconocida() {
        System.out.println("--Error: Digito un caracter que no esta asociado a ninguna funcion");
    }

    public void showAllAutor() {
        String resultado = "";
        try {
            resultado = listEntidadesToString(ls.getAllAutor());
        } catch (Exception e) {
            System.out.println("Error: \n" + e.getMessage());
        }
        System.out.println(resultado);
    }

    public void showAllEditorial() {
        String resultado = "";
        try {
            resultado = listEntidadesToString(ls.getAllEditorial());
        } catch (Exception e) {
            System.out.println("Error: \n" + e.getMessage());
        }
        System.out.println(resultado);
    }

    public void showAllPais() {
        String resultado = "";
        try {
            resultado = listEntidadesToString(ls.getAllPais());
        } catch (Exception e) {
            System.out.println("Error: \n" + e.getMessage());
        }
        System.out.println(resultado);
    }

    public void showAllBooks() {
        String resultado = "";
        try {
            resultado = listEntidadesToString(ls.getAllBooks());
        } catch (Exception e) {
            System.out.println("Error: \n" + e.getMessage());
        }
        System.out.println(resultado);
    }

    public void insertBook() {

        mostrarMsgInsercion("Libro");
        System.out.println("-Atencion: Para ingresar el Autor y Editorial del Libro primero deberas haberlas\n insertado en la BD");
        String[] columnas = {"el isbn del libro", "el nombre del libro (*)",
            "el año de publicacion del libro (*)", "el genero del libro",
            "el idioma del libro (*)", "la descripcion del libro", "la Editorial del libro",
            "el numero de autores del Libro", "el autor del libro, puede ingresar su id en la BD o su nombre (*)"};

        String texto="";
        Object input = null;
        Object[] parametros = new Object[7];
        int numAutores = 0;

        //Se recolectan los parametros necesarios
        for (int i = 0; i < parametros.length; i++) {
            texto = "Digite por favor " + columnas[i] + ": ";
            System.out.println(texto);

            switch (i) {
                case 0, 3, 5:
                    input = sc.nextLine();
                    break;
                case 1, 4:
                    input = leerTextoObligatorio(texto);
                    break;
                case 6:
                    input=leerIntOTexto(texto, false);
                    break;
                case 2:
                    input = leerIntObligatorio(texto);
                    break;
            }
            if (input.equals("*")) {
                accionCanceladaMSG();
                return;
            }
            parametros[i] = input;
        }
        texto = "Digite por favor " + columnas[7] + ": ";
        System.out.println(texto);
        input = leerIntObligatorio(texto);
        if(input.equals("*")){
            accionCanceladaMSG();
            return;
        }else{
            numAutores= (int) input;
        }
        if (numAutores == 0) {
            System.out.println("Error: No existe un LIBRO SIN AUTORES!! Al menos, aqui no");
            return;
        }

        Object[] autores = new Object[numAutores];

        showAllAutor();
        System.out.println("\n-Aqui tiene informacion de los autores dentro de la base de datos,\n le podrian ser utiles para digitar los autores a continuacion.");

        for (int i = 0; i < autores.length; i++) {

            texto = "¿Digitara el autor n°" + i + " por ID o por Nombre?: ";
            texto += "\n Digite (1) si es por ID y (2) si es por nombre";
            System.out.println(texto);
            input = leerIntObligatorio(texto);
            if(input.equals("*")){
                accionCanceladaMSG();
                return;
            }
            switch ((int) input) {

                case 1:
                    texto = "Digite el ID del autor: ";
                    System.out.println(texto);
                    autores[i] = leerIntObligatorio(texto);
                    break;
                case 2:
                    texto = "Digite el nombre del autor: ";
                    System.out.println(texto);
                    autores[i] = leerTextoObligatorio(texto);
                    break;
            }
        }
        parametros[6]=autores;

        //Se realiza la ejecucion SQL
        String exitoso = "";
        try {
            exitoso = ls.insertBook(parametros, autores);
        } catch (Exception e) {
            System.out.println("--Error: \n" + e.getMessage());
        }
        if (exitoso.equals("1")) {
            System.out.println("--Insercion exitosa");
        } else {
            System.out.println("--Falla en la insercion del libro");
        }
    }

    public void insertAutor() {

        mostrarMsgInsercion("Autor");
        System.out.println("-Atencion: Para ingresar la nacionalidad del libro primero\n deberas haber ingresado el Pais en la BD\n");

        String[] columnas = {"el nombre del autor (*)", "la nacionalidad del autor (*)",
            "el año de nacimiento del autor", "una descripcion del autor"};

        String texto;
        Object input = null;
        Object[] parametros = new Object[4];

        //Se recolectan los parametros necesarios
        for (int i = 0; i < 4; i++) {

            texto = "Digite por favor " + columnas[i] + ": ";
            System.out.println(texto);

            switch (i) {
                case 0:
                    input = leerTextoObligatorio(texto);
                    break;
                case 1:
                    input = leerIntObligatorio(texto);
                    break;
                case 2:
                    input = leerInt(texto);
                    break;
                case 3:
                    input = sc.nextLine();
                    break;
            }
            if (input.equals("*")) {
                accionCanceladaMSG();
                return;
            }
            parametros[i] = input;
        }

        //Se realiza la ejecucion SQL
        String resultado = "";
        try {
            resultado = ls.insertAutor(parametros);
        } catch (Exception e) {
            System.out.println("Error: \n" + e.getMessage());
        }

        System.out.println("\n" + resultado);
    }

    public void insertPais() {

        mostrarMsgInsercion("Pais");

        String[] columnas = {"el nombre del pais (*)", "el sufijo telefonico del pais (*)",
            "una descripcion del pais"};

        String texto;
        Object input = null;
        Object[] parametros = new Object[3];

        //Se recolectan los parametros necesarios
        for (int i = 0; i < parametros.length; i++) {

            texto = "Digite por favor " + columnas[i] + ": ";
            System.out.println(texto);

            switch (i) {
                case 0, 1:
                    input = leerTextoObligatorio(texto);
                    break;
                case 2:
                    input = sc.nextLine();
                    break;
            }
            if (input.equals("*")) {
                accionCanceladaMSG();
                return;
            }
            parametros[i] = input;
        }

        //Se realiza la ejecucion SQL
        String resultado = "";
        try {
            resultado = ls.insertPais(parametros);
        } catch (Exception e) {
            System.out.println("Error: \n" + e.getMessage());
        }

        System.out.println("\n" + resultado);
    }

    public void insertEditorial() {
        mostrarMsgInsercion("Editorial");
        System.out.println("-Atencion: Para ingresar el pais de la cede central de la Editorial,\n primero, deberas haber ingresado el Pais en la BD\n");

        String[] columnas = {"el nombre de la Editorial (*)", "el año en que se fundo el editorial (*)",
            "nombre de pais donde se encuentra su cede central"};

        String texto;
        Object input = null;
        Object[] parametros = new Object[3];

        //Se recolectan los parametros necesarios
        for (int i = 0; i < 3; i++) {

            texto = "Digite por favor " + columnas[i] + ": ";
            System.out.println(texto);

            switch (i) {
                case 0:
                    input = leerTextoObligatorio(texto);
                    break;
                case 1:
                    input = leerIntObligatorio(texto);
                    break;
                case 2:
                    input = sc.nextLine();
                    break;
            }
            if (input.equals("*")) {
                accionCanceladaMSG();
                return;
            }
            parametros[i] = input;           
        }

        //Se realiza la ejecucion SQL
        String resultado = "";
        try {
            resultado = ls.insertEditorial(parametros);
        } catch (Exception e) {
            System.out.println("Error: \n" + e.getMessage());
        }

        System.out.println("\n" + resultado);
    }

    private void salir() {
        System.out.println("\nSaliendo...");
    }

    //-------------------------------------------------------------------------
    //Funciones que ayudan a leer parametros para consultas
    //-------------------------------------------------------------------------
    public String leerTextoObligatorio(String texto) {

        String input;
        boolean seguir;
        do {
            input = sc.nextLine();
            
            if (input.isEmpty()) {
                System.out.println("-Error: No digito nada en una columna obligatoria");
                System.out.println(texto);
                seguir = true;
            } else {
                seguir = false;
            }
        } while (seguir);

        return input;
    }

    public String leerTexto() {

        String input = sc.nextLine();

        return input;
    }

    public Object leerIntOTexto(String texto, boolean obligatorio) {

        String input;
        Integer inputInt;
        boolean seguir = false;

        do {
            input = sc.nextLine();
            if (input.equals("*")) {
                return "*";
            }
            if (input.isEmpty() && obligatorio) {
                seguir = true;
                System.out.println("Error: No digito nada en una casilla obligatoria");
                System.out.println(texto);
            } else {
                seguir = false;
            }
        } while (seguir);

        //Ahora si ve que es un numero lo parsea
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException e) {
            return input;
        }

    }

    public Object leerIntObligatorio(String texto) {

        int input = -1;
        String inputTexto;
        boolean seguir = false;
        do {

            //Si no se digita nada, da error
            inputTexto = sc.nextLine();
            if (inputTexto.equals("*")) {
                return "*";
            }
            if (inputTexto.isEmpty()) {
                System.out.println("-Error: No digito nada en una columna obligatoria");
                System.out.println(texto);
                seguir = true;
            } else {
                try {
                    input = Integer.parseInt(inputTexto);
                    seguir=false;
                } catch (NumberFormatException e) {
                    System.out.println("-Error: Digito algo que no es un numero O es un numero muy grande");
                    System.out.println(texto);
                    seguir = true;
                }
            }
        } while (seguir);

        return input;
    }

    public Object leerInt(String texto) {

        String input;
        Integer inputInt = null;
        boolean seguir = false;

        do {
            input = sc.nextLine();
            if (input.equals("*")) {
                return "*";
            }
            if (input.isEmpty()) {
                return "";
            } else {
                try {
                    inputInt = Integer.valueOf(input);
                    seguir=false;
                } catch (Exception e) {
                    System.out.println("Error: Digito algo que no es un numero O digito un numero muy grande");
                    System.out.println(texto);
                    seguir = true;
                }
            }
        } while (seguir);

        return inputInt;
    }

    //-------------------------------------------------------------------------
    //Funciones que hacen en buena parte de la Vista
    //-------------------------------------------------------------------------

    public void mostrarMenu() {

        String menu = "Menu de acciones: presione el numero de la accion que quiere realizar";
        menu += "\n (8) Insertar un pais en la Libreria";
        menu += "\n (7) Insertar una editorial en la Libreria";
        menu += "\n (6) Insertar un autor en la Libreria";
        menu += "\n (5) Insertar un libro en la Libreria";
        menu += "\n (4) Listar todos los paises guardados en la Liberia";
        menu += "\n (3) Listar todas las editoriales en la Liberia";
        menu += "\n (2) Listar todos los autores en la Liberia";
        menu += "\n (1) Listar todos los libros en la Liberia";
        menu += "\n (0) Salir";
        System.out.println(menu);
    }

    public void mostrarMsgInsercion(String entidad) {

        System.out.println("A continuacion, se le preguntara informacion para insertar un " + entidad + " en la BD,\n si quiere cancelar la insercion digite un '*'");
        System.out.println("(Si una casilla tiene '(*)', significa que es obligatoria.");
        System.out.println("Si quiere cancelar la accion digite '*')\n");
    }

    public String listEntidadesToString(List resultado) {

        List<Object> listaEntidad = new ArrayList<>(resultado);

        String texto = "";

        for (int i = 0; i < listaEntidad.size(); i++) {

            Object entidad = listaEntidad.get(i);
            texto += "\n" + entidad.toString() + "\n";
        }

        return texto;
    }

    public void accionCanceladaMSG() {

        System.out.println("Cancelando accion...");
    }
    */
}
