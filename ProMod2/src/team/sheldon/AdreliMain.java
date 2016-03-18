package team.sheldon;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdreliMain {

  private boolean isRunning = true;
  private ArrayList<Person> personen = new ArrayList<>();
  private final Scanner scanner;

  public AdreliMain() {
    scanner = new Scanner(System.in);
  }

  public static void main(String[] args) throws IOException {
    AdreliMain main = new AdreliMain();

    while (main.isRunning()) {
      main.printMenu();
      String nextInput = main.getScanner().next();
      int number = Integer.parseInt(nextInput);
      switch (number) {
      case 0:
        main.setRunning(false);
        System.out.println("Ciao");
        break;
      case 1:
        Person erfasstePerson = main.erfassePerson();
        if (erfasstePerson != null) {
          main.addPerson(erfasstePerson);
        }
        break;
      case 2:
        main.displayPersons();
        break;
      case 3:
        System.out.print("Dateiname zum speichern eingeben: ");
        main.savePersons(main.getScanner().next());
        break;
      case 4:
        System.out.println("Dateiname zum laden eingeben: ");
        main.loadPersons(main.getScanner().next());
        break;
      default:
        // nothing to do here yet
      }
    }
  }

  private void displayPersons() {
    for (Person person : personen) {
      System.out.println(person);
      try {
        System.in.read();
      } catch (IOException e) {}
    }
  }

  private void addPerson(Person person) {
    personen.add(person);
  }

  private void printMenu() {
    System.out.println("ADRELI - Adressverwaltung");
    System.out.println("Wollen Sie ...");
    System.out.println("0. Programm Beenden");
    System.out.println("1. Neue Person anlegen");
    System.out.println("2. Alle Anzeigen");
    System.out.println("3. Personen speichern");
    System.out.println("4. Personen laden");
    System.out.println();
    System.out.println("Bitte tippen Sie ein Menupunkt und bestaetigen mit Enter [0-5]:");
  }

  private Person erfassePerson() {
    Person neuePerson = null;
    System.out.println("Geben Sie bitte die Daten ein:");
    System.out.print("Name: ");

    String name = getScanner().next();
    System.out.print("Vorname: ");
    String vorname = getScanner().next();
    
    if (name != null && vorname != null) {
      neuePerson = new Person(name, vorname);
    }

    return neuePerson;
  }
  
  public void savePersons(String filename) throws IOException {
    List<String> lines = new ArrayList<>();
    
    for (Person person : personen) {
      lines.add(person.toFileString());
    }
    
    Path file = Paths.get(filename);
    Files.write(file, lines, Charset.forName("UTF-8"));
  }

  public void loadPersons(String filename) throws IOException {
    Path file = Paths.get(filename);
    List<String> allLines = Files.readAllLines(file,Charset.forName("UTF-8"));
    for (String string : allLines) {
      personen.add(new Person(string));
    }
  }
  
  /**
   * @return the isRunning
   */
  public boolean isRunning() {
    return isRunning;
  }

  /**
   * @param isRunning
   *          the isRunning to set
   */
  public void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }

  /**
   * @return the scanner
   */
  public Scanner getScanner() {
    return scanner;
  }
}
