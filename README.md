# ⚔️ Battle Simulator — Java Project

An educational **Java** project that simulates battles between characters and manages their statistics using data structures such as `HashMap`, `ArrayList`, and `Set`.  
It includes file input/output, a simple text-based interface, and an interactive menu.

---

## 🚀 Features

- 🧙‍♂️ Create and store characters (`Personaje`) in a `HashMap`  
- ⚔️ Simulate battles between characters (`Batallas`)  
- 📊 Track and display statistics (`Estadisticas`)  
- 💾 Read and save data from/to `src/main/resources/personaje.txt`  
- 🧭 Simple console-based user interface (`UI.java`)  

---

## 🧩 Project Structure

```
src/
 └── main/
      ├── java/com/example/
      │    ├── Main.java          # Entry point of the program
      │    ├── Personaje.java     # Character class with attributes and methods
      │    ├── Batallas.java      # Battle logic between characters
      │    ├── Estadisticas.java  # Statistics tracking and sorting
      │    └── UI.java            # Text interface and menu logic
      └── resources/
           └── personaje.txt      # Example or saved character data
 └── test/                        # (optional) unit tests
pom.xml                           # Maven configuration file
target/                           # Compiled classes
```

---

## ⚙️ Technologies

- **Java 17+**  
- **Maven** for dependency management  
- Utility classes: `Scanner`, `BufferedReader`, `PrintWriter`, `HashMap`, `ArrayList`  
- **OOP** (Object-Oriented Programming) principles  

---

## 🧰 Installation & Run

1. Clone the repository:
   ```bash
   git clone https://github.com/AlexYad/BattleSimulator.git
   cd BattleSimulator
   ```

2. Compile with Maven:
   ```bash
   mvn clean compile
   ```

3. Run the project:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.Main"
   ```

   *(or simply press “Run” on `Main.java` in VS Code / IntelliJ)*

---

## 📊 Example Output

```
╔════════════╦════════════╦════════════╦════════════╦══════════════════════╗
║ Nombre     ║ Victorias  ║ Derrotas   ║ Total      ║ WinRatio             ║
╠════════════╬════════════╬════════════╬════════════╬══════════════════════╣
║ Isa        ║ 5          ║ 1          ║ 6          ║ [████████░░] → 83%   ║
║ Alex       ║ 8          ║ 2          ║ 10         ║ [████████░░] → 80%   ║
║ Maxi       ║ 3          ║ 3          ║ 6          ║ [█████░░░░░] → 50%   ║
║ Inna       ║ 2          ║ 4          ║ 6          ║ [███░░░░░░░] → 33%   ║
║ Pepe       ║ 1          ║ 9          ║ 10         ║ [█░░░░░░░░░] → 10%   ║
╚════════════╩════════════╩════════════╩════════════╩══════════════════════╝
```

---

## 💡 Future Improvements

- Export statistics to `.json` or `.csv`  
- Add random character generation  
- Build a graphical interface (JavaFX)  
- Implement save/load system  

---

## 🧑‍💻 Author

Developed by **Alex** as a Java programming practice project.  
License: **MIT**
