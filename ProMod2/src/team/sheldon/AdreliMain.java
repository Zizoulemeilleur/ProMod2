package team.sheldon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdreliMain {

  private boolean isRunning = true;
  private ArrayList<Person> personen = new ArrayList<>();
  private final Scanner scanner;

  public AdreliMain() {
    scanner = new Scanner(System.in);
  }

  public static void main(String[] args) {
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
    System.out.println("...");
    System.out.println();
    System.out.println("Bitte tippen Sie ein Menupunkt und bestaetigen mit Enter [0-5]:");
  }

  private Person erfassePerson() {
    Person neuePerson = null;
    System.out.println("Geben Sie bitte die Daten ein:");
    System.out.print("Name: ");

    String name = getScanner().next();
    if (name != null) {
      neuePerson = new Person(name);
    }

    return neuePerson;
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
