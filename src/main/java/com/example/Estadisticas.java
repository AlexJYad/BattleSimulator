package com.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Estadisticas {

   public static void mostrarTabla(HashMap<String, Personaje> personajesMap) {
      ArrayList<Personaje> personajes = new ArrayList<>(personajesMap.values());

      // Считаем общую статистику
      int totalBatallas = 0;
      double totalRatio = 0.0;
      int count = 0;

      for (Personaje p : personajes) {
         int batallas = p.getVictorias() + p.getDerrotas();
         if (batallas > 0) {
            totalRatio += (double) p.getVictorias() / batallas;
            count++;
         }
         totalBatallas += batallas;
      }
      double promedioRatio = count > 0 ? totalRatio / count : 0;

      // Сортируем по коэффициенту побед (убывание)
      personajes.sort((p1, p2) -> {
         double ratio1 = (p1.getVictorias() + p1.getDerrotas() > 0)
               ? (double) p1.getVictorias() / (p1.getVictorias() + p1.getDerrotas())
               : 0;

         double ratio2 = (p2.getVictorias() + p2.getDerrotas() > 0)
               ? (double) p2.getVictorias() / (p2.getVictorias() + p2.getDerrotas())
               : 0;

         return Double.compare(ratio2, ratio1); // убывание
      });

      // Заголовок таблицы
      System.out.printf(UI.lineTop(60, 5));
      System.out.printf("║ %-10s ║ %-10s ║ %-10s ║ %-10s ║ %-20s ║\n",
            "Nombre", "Victorias", "Derrotas", "Total", "WinRatio");
      System.out.printf(UI.lineMid(60, 5));

      // Вывод каждого персонажа
      for (Personaje p : personajes) {
         int total = p.getVictorias() + p.getDerrotas();
         double ratio = total > 0 ? (double) p.getVictorias() / total : 0;
         System.out.printf("║ %-10s ║ %-10s ║ %-10s ║ %-10s ║ %-20s ║\n",
               p.getNombre(), p.getVictorias(), p.getDerrotas(), total, UI.progressBar((int) (ratio * 100)));
      }
      System.out.printf(UI.lineBottom(60, 5));

      // Общая статистика
      System.out.println("\n--- Estadísticas generales ---");
      System.out.printf("Total de batallas: %d\n", totalBatallas);
      System.out.printf("Promedio de Win Ratio: %.2f%%\n", promedioRatio * 100);
   }
}
