package com.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Batallas {
   public static void batalla(Personaje uno, Personaje dos) throws InterruptedException {
      // Сохраняем изначальное здоровье, чтобы потом восстановить
      int saludInicialUno = uno.getSalud();
      int saludInicialDos = dos.getSalud();

      System.out.println("\n⚔️ Batalla entre " + uno.getNombre() + " y " + dos.getNombre() + "!\n");

      while (true) {
         int dadoUno = new Random().nextInt(6) + 1;
         int dadoDos = new Random().nextInt(6) + 1;
         int ataqueUno = uno.getFuerza() + dadoUno;
         int ataqueDos = dos.getFuerza() + dadoDos;
         System.out.println("\n🎲 Lanzando los dados...");
         Thread.sleep(800);
         System.out
               .println(uno.getNombre() + " tira el dado y obtiene " + dadoUno + " (ataque total: " + ataqueUno + ")");
         System.out
               .println(dos.getNombre() + " tira el dado y obtiene " + dadoDos + " (ataque total: " + ataqueDos + ")");

         if (ataqueUno > ataqueDos) {
            if (dos.batalla((ataqueUno - ataqueDos))) {
               System.out.println("🔥 " + uno.getNombre() + " gana la ronda en la batalla!\n");
            } else {
               uno.aumentarFuerza();
               System.out.println("🏆 " + uno.getNombre() + " gana la batalla!");
               uno.ganar(saludInicialUno);
               dos.perder(saludInicialDos);
               return;
            }

         } else if (ataqueDos > ataqueUno) {
            if (uno.batalla((ataqueDos - ataqueUno))) {
               System.out.println("🔥 " + dos.getNombre() + " gana la ronda en la batalla!\n");
            } else {
               dos.aumentarFuerza();
               System.out.println("🏆 " + dos.getNombre() + " gana la batalla!");
               dos.ganar(saludInicialDos);
               uno.perder(saludInicialUno);
               return;
            }
         } else {
            System.out.println("🤝 Empate!\n");
         }
      }
   }

   public static String selecciónDePersonajes(ArrayList<String> nombres, String seleccionado, Scanner sc) {
      final String ANSI_GRAY = "\u001B[90m";
      final String ANSI_RESET = "\u001B[0m";
      System.out.println("Elige al luchado: ");

      if (seleccionado != null) {
         nombres.remove(seleccionado);
         System.out.println(ANSI_GRAY + "0. " + seleccionado + " (preseleccionado)" + ANSI_RESET);
      }

      // выводим всех персонажей
      for (

            int i = 0; i < nombres.size(); i++) {
         String nombre = nombres.get(i);
         System.out.println((i + 1) + ". " + nombre);
      }

      int numero;
      while (true) {
         System.out.print("👉 Ingresa el número de tu luchador: ");
         String input = sc.nextLine();
         numero = Main.safeParserInt(input);

         if (numero >= 1 && numero < nombres.size() + 1) {
            break; // корректный выбор — выходим из цикла
         } else {
            continue;
         }
      }
      return nombres.get(--numero);
   }

}
