package com.example;

import java.util.Random;

public class Personaje {
   private static int nextId = 1;
   private final int id;
   private String nombre;
   private int fuerza;
   private int salud;
   private int victorias;
   private int derrotas;

   public Personaje(String nombre) {
      this.id = nextId++;
      this.nombre = nombre;
      this.fuerza = new Random().nextInt(5, 11);
      this.salud = new Random().nextInt(20, 30);
      this.victorias = 0;
      this.derrotas = 0;
   }

   public Personaje(String nombre, int fuerza, int salud, int victorias, int derrotas) {
      this.id = nextId++;
      this.nombre = nombre;
      this.fuerza = fuerza;
      this.salud = salud;
      this.victorias = victorias;
      this.derrotas = derrotas;
   }

   public int getId() {
      return id;
   }

   public String getNombre() {
      return nombre;
   }

   public int getFuerza() {
      return fuerza;
   }

   public int getSalud() {
      return salud;
   }

   public int getDerrotas() {
      return derrotas;
   }

   public int getVictorias() {
      return victorias;
   }

   public void setSalud(int salud) {
      this.salud = salud;
   }

   public void aumentarFuerza() {
      this.fuerza++;
   }

   public void aumentarSalud() {
      this.salud++;
   }

   public void ganar(int saludInicial) {
      this.salud = saludInicial;
      victorias++;
      aumentarFuerza();
      aumentarSalud();
   }

   public void perder(int saludInicial) {
      this.salud = saludInicial;
      derrotas++;
   }

   public boolean batalla(int num) {
      this.salud -= num;
      return (this.salud > 0);
   }

   public String toString() {
      final String RESET = "\u001B[0m";
      // final String GREEN = "\u001B[32m";
      final String CYAN = "\u001B[36m";

      int maxLen = Math.max(
            Math.max(nombre.length(), String.valueOf(fuerza).length()),
            String.valueOf(salud).length());
      int width = Math.max(20, maxLen + 15); // минимальная ширина — 20 символов

      String lineTop = "╔" + "═".repeat(width - 2) + "╗\n";
      String lineMid = "╠" + "═".repeat(width - 2) + "╣\n";
      String lineBottom = "╚" + "═".repeat(width - 2) + "╝";
      String titulo = "🛡️ Personaje 🛡️";

      int padding = ((width + 2) - titulo.length()) / 2;
      String centeredTitle = " ".repeat(Math.max(0, padding)) + titulo;
      centeredTitle += " ".repeat(Math.max(0, (width + 2) - centeredTitle.length())); // добиваем справа

      StringBuilder sb = new StringBuilder();
      sb.append(lineTop);
      sb.append("║").append(CYAN).append(centeredTitle).append(RESET).append("║\n");
      sb.append(lineMid);
      sb.append(String.format("║ %-9s %-" + (width - 14) + "s ║\n", "Nombre:", nombre));
      sb.append(String.format("║ %-9s %-" + (width - 14) + "d ║\n", "Fuerza:", fuerza));
      sb.append(String.format("║ %-9s %-" + (width - 14) + "d ║\n", "Salud:", salud));
      sb.append(lineBottom);

      return sb.toString();
   }

   public String toFileString() {
      return nombre + ";" + fuerza + ";" + salud + ";" + victorias + ";" + derrotas;
   }
}
