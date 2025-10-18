package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

   public static HashMap<String, Personaje> personajesMap = new HashMap<>();
   public static Scanner sc = new Scanner(System.in);

   public static void main(String[] args) throws InterruptedException, IOException {

      boolean salir = false;
      String ruta = "src/main/resources/personaje.txt";

      try {
         System.out.println("Longing . . . ");
         BufferedReader br = new BufferedReader(new FileReader(ruta));
         String linea = br.readLine();
         while (linea != null) {
            String[] partes = linea.split(";");
            String nombre = partes[0];
            int fuerza = Integer.parseInt(partes[1]);
            int salud = Integer.parseInt(partes[2]);
            int victorias = Integer.parseInt(partes[3]);
            int derrotas = Integer.parseInt(partes[4]);
            personajesMap.put(nombre, new Personaje(nombre, fuerza, salud, victorias, derrotas));
            linea = br.readLine();
         }
         br.close();
      } catch (Exception e) {
         personajesMap = new HashMap<>();
      }

      String[] menu = { "Crear personaje", "Iniciar batalla", "Ver personajes", "Ver estadísticas", "Salir del juego" };
      int seleccionado = 0; // например, подсветка второго пункта

      String menuStr = UI.crearMenu("MENÚ PRINCIPAL", menu, seleccionado);

      while (!salir) {
         System.out.print(menuStr);

         String option = sc.nextLine();
         int opción = safeParserInt(option);

         switch (opción) {
            case 1:
               System.err.printf("Introduce el nombre del personaje: ");
               String nombre = sc.nextLine();
               if (!personajesMap.containsKey(nombre)) {
                  personajesMap.put(nombre, new Personaje(nombre));
                  System.out.printf("%n Personaje add %n");
               } else {
                  System.out.printf("%n Personaje existe %n");
               }
               UI.continuarEntre(sc);
               break;
            case 2:
               Personaje personajeUno;
               Personaje personajeDos;
               if (personajesMap.size() < 2) {
                  System.out.println("No hay suficientes personajes para la batalla");
                  UI.continuarEntre(sc);
                  break;
               } else if (personajesMap.size() == 2) {
                  Iterator<Personaje> it = personajesMap.values().iterator();
                  personajeUno = it.next();
                  personajeDos = it.next();
               } else {
                  ArrayList<String> nombres = new ArrayList<>(personajesMap.keySet());

                  personajeUno = personajesMap.get(Batallas.selecciónDePersonajes(nombres, null, sc));
                  personajeDos = personajesMap
                        .get(Batallas.selecciónDePersonajes(nombres, personajeUno.getNombre(), sc));
               }
               Batallas.batalla(personajeUno, personajeDos);
               UI.continuarEntre(sc);
               break;
            case 3:
               for (Personaje personaje : personajesMap.values()) {
                  System.out.println(personaje);
               }
               break;
            case 4:
               Estadisticas.mostrarTabla(personajesMap);
               UI.continuarEntre(sc);
               break;
            case 5:
               salir = true;
               break;

            case -1:
               UI.continuarEntre(sc);
               break;

            default:
               UI.mostrarError("Opción  inválida", "Inténtalo de nuevo!");
               UI.continuarEntre(sc);
               break;
         }
      }

      PrintWriter pw = new PrintWriter(new FileWriter(ruta));
      for (Personaje personaje : personajesMap.values()) {
         pw.println(personaje.toFileString());
      }
      pw.close();
      sc.close();
   }

   // -------- Funcsenas --------//

   public static int safeParserInt(String string) {
      try {
         return Integer.parseInt(string);
      } catch (NumberFormatException e) {
         UI.mostrarError("NOT A NUMERO", "Inténtalo de nuevo!");
         return -1;
      }

   }

}