package com.example;

import java.util.Scanner;

public class UI {
   // ANSI-коды для цвета
   static final String RESET = "\u001B[0m";
   static final String GREEN = "\u001B[32m";
   static final String CYAN = "\u001B[36m";
   static final String RED = "\u001B[31m";

   public static String lineTop(int width) {
      return "╔" + "═".repeat(width) + "╗\n";
   }

   public static String lineTop(int width, int columns) {
      return "╔" + ("═".repeat(width / columns) + "╦").repeat(columns - 1) + "═".repeat(width / columns + 10) + "╗\n";
   }

   public static String lineMid(int width) {
      return "╠" + "═".repeat(width) + "╣\n";
   }

   public static String lineMid(int width, int columns) {
      return "╠" + ("═".repeat(width / columns) + "╬").repeat(columns - 1) + "═".repeat(width / columns + 10) + "╣\n";
   }

   public static String lineBottom(int width) {
      return "╚" + "═".repeat(width) + "╝\n";
   }

   public static String lineBottom(int width, int columns) {
      return "╚" + ("═".repeat(width / columns) + "╩").repeat(columns - 1) + "═".repeat(width / columns + 10) + "╝\n";
   }

   public static String progressBar(int percent) {
      return "[" + "█".repeat(percent / 10) + "░".repeat(10 - percent / 10) + "] → " + percent + "%";
   }

   public static void mostrarError(String titulo, String detalle) {
      // Добавляем значки опасности
      int width = Math.max(20, titulo.length()); // минимальная ширина — 20 символов
      width += 4; // рамки и отступы

      StringBuilder sb = new StringBuilder();

      // Центрируем заголовок
      int padding = (width - titulo.length()) / 2;
      String centeredTitle = " ".repeat(Math.max(0, padding)) + titulo;
      centeredTitle += " ".repeat(Math.max(0, width - centeredTitle.length())); // добиваем справа

      padding = (width - detalle.length()) / 2;
      String centeredDetalle = " ".repeat(Math.max(0, padding)) + detalle;
      centeredDetalle += " ".repeat(Math.max(0, width - centeredDetalle.length())); // добиваем справа

      sb.append(lineTop(width));
      sb.append("║").append(RED).append(centeredTitle).append(RESET).append("║\n");
      sb.append(lineMid(width));
      sb.append("║").append(centeredDetalle).append("║\n");
      sb.append(lineBottom(width));

      System.err.println(sb);
   }

   public static String crearMenu(String titulo, String[] opciones, int seleccionado) {

      // Определяем ширину рамки по самому длинному пункту + заголовку
      int width = titulo.length();
      for (int i = 0; i < opciones.length; i++) {
         int len = (i + 1 + ". " + opciones[i]).length();
         if (len > width)
            width = len;
      }
      width += 4; // рамки и отступы

      StringBuilder sb = new StringBuilder();

      // Центрируем заголовок
      int padding = (width - titulo.length()) / 2;
      String centeredTitle = " ".repeat(Math.max(0, padding)) + titulo;
      centeredTitle += " ".repeat(Math.max(0, width - centeredTitle.length())); // добиваем справа

      sb.append(lineTop(width));
      sb.append("║").append(CYAN).append(centeredTitle).append(RESET).append("║\n");
      sb.append(lineMid(width));

      // Пункты меню
      for (int i = 0; i < opciones.length; i++) {
         String texto = (i + 1) + ". " + opciones[i];
         int espacios = width - texto.length();
         String color = (i == seleccionado) ? GREEN : RESET;
         sb.append("║ ").append(color).append(texto).append(RESET)
               .append(" ".repeat(espacios - 1)).append("║\n");
      }

      sb.append(lineBottom(width));
      sb.append("➤ Elige una opción: ");

      return sb.toString();
   }

   public static void continuarEntre(Scanner sc) {
      System.out.println("Presiona ENTER para continuar...");
      sc.nextLine();
   }
}
