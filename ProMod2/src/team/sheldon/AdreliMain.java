package team.sheldon;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Die Hauptklasse
 *
 */
public class AdreliMain {

  /**
   * Ist die Applikation noch am laufen, oder soll beendet werden?
   */
  private boolean isRunning = true;
  /**
   * Die in-memory gepufferted Personen
   */
  private ArrayList<Person> personen = new ArrayList<>();
  /**
   * Ein Scanner, der in der ganzen applikation benutzt wird.
   */
  private final Scanner scanner;

  /**
   * Constructor.
   */
  public AdreliMain() {
    scanner = new Scanner(System.in);
  }

  public static void main(String[] args) throws IOException {
    // Applikation wird erstellt.
    AdreliMain main = new AdreliMain();

    // und solange in einer endlosschleife bis das isRunning flag auf `false`
    // gesetzt wird.
    // TODO Exception Handling
    while (main.isRunning()) {
      // Pro Schleife wird immer als erstes das Menu ausgegeben
      main.printMenu();

      // und dann der User Input abgefragt
      String nextInput = main.getScanner()
          .next();
      // TODO Exception Handling des parsens
      int number = Integer.parseInt(nextInput);

      // und dann ausgewertet.
      switch (number) {
      case 0:
        // `isRunning` wird auf false gesetzt, und bei der naechsten Schleife
        // das Programm somit beendet.
        main.setRunning(false);
        System.out.println("Ciao");
        break;
      case 1:
        // Person wird erfasst und dem Array hinzugefuegt
        Person erfasstePerson = main.erfassePerson();
        // TODO exception handling
        if (erfasstePerson != null) {
          main.addPerson(erfasstePerson);
        }
        break;
      case 2:
        // Alle Personen werden angezeigt
        main.displayPersons();
        break;
      case 3:
        System.out.print("Dateiname zum speichern eingeben: ");
        // TODO Exception handling
        String filenameForSave = main.getScanner()
            .next();
        main.savePersons(filenameForSave);
        break;
      case 4:
        System.out.println("Dateiname zum laden eingeben: ");
        // TODO exception handling
        main.loadPersons(main.getScanner()
            .next());
        break;
      case 5:
        System.out.println("Dateiname zum loeschen eingeben: ");
        // TODO Exception handling
        main.deleteFile(main.getScanner()
            .next());
        break;
      default:
        // Wenn der Benutzerinput nicht passt, dann wird nichts gemacht.
        // TODO Fehler anzeigen
      }
    }
  }

  /**
   * Delete the given file.
   * 
   * @param filename
   *          the given filename/path
   */
  private void deleteFile(String filename) {
    // TODO Exception Handling
    File file = new File(filename);
    if (file.delete()) {
      System.out.println("Datei: " + filename + " wurde geloescht.");
    } else {
      System.out.println("Loeschen der Datei " + filename + " hat leider nicht geklappt.");
    }
  }

  /**
   * Displays all Persons.
   */
  private void displayPersons() {
    for (Person person : personen) {
      System.out.println(person);
      try {
        // Der Benutzer muss nach jeder gezeigten Person mit `ENTER`
        // bestaetigen.
        System.in.read();
      } catch (IOException e) {
      }
    }
  }

  /**
   * Add a Person to the `personen` Array.
   * 
   * @param person
   *          the person to add
   */
  private void addPerson(Person person) {
    personen.add(person);
  }

  /**
   * Print the commands menu to the console.
   */
  private void printMenu() {
    System.out.println("ADRELI - Adressverwaltung");
    System.out.println("Wollen Sie ...");
    System.out.println("0. Programm Beenden");
    System.out.println("1. Neue Person anlegen");
    System.out.println("2. Alle Anzeigen");
    System.out.println("3. Personen speichern");
    System.out.println("4. Personen laden");
    System.out.println("5. Personen Datei loeschen");
    System.out.println();
    System.out.println("Bitte tippen Sie ein Menupunkt und bestaetigen mit Enter [0-5]:");
  }

  /**
   * Record all properties for a person and create a new person.
   * 
   * @return the newly created person.
   */
  private Person erfassePerson() {
    Person neuePerson = null;
    System.out.println("Geben Sie bitte die Daten ein:");
    System.out.print("Name: ");

    String name = getScanner().next();
    System.out.print("Vorname: ");
    String vorname = getScanner().next();

    // TODO Exception Handling
    if (name != null && vorname != null) {
      neuePerson = new Person(name, vorname);
    }

    return neuePerson;
  }

  /**
   * Saves the in-memory `personen` to the given file.
   * 
   * @param filename
   *          the filename
   * @throws IOException
   */
  public void savePersons(String filename) throws IOException {
    List<String> lines = new ArrayList<>();

    for (Person person : personen) {
      // Create a serialized String of each person
      lines.add(person.toSerializedString());
    }

    Path file = Paths.get(filename);
    // TODO Zuerst file loeschen?
    // TODO Exception Handling
    // Write all serialized strings to the given file.
    Files.write(file, lines, Charset.forName("UTF-8"));
  }

  /**
   * Load the persons from the given file. Each file should represent a given
   * serialized person string.
   * 
   * @param filename
   *          the file to load
   * @throws IOException
   */
  public void loadPersons(String filename) throws IOException {
    Path file = Paths.get(filename);
    // TODO Exception Handling
    List<String> allLines = Files.readAllLines(file, Charset.forName("UTF-8"));
    for (String line : allLines) {
      // Create Persons via the given serialized strings and add them to the
      // `personen` array.
      // TODO Exception Handling
      personen.add(new Person(line));
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
