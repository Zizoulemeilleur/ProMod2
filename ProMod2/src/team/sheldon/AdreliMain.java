package team.sheldon;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
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

		// und solange in einer endlosschleife bis das isRunning flag auf
		// `false`
		// gesetzt wird.
		// TODO Exception Handling
		while (main.isRunning()) {
			// Pro Schleife wird immer als erstes das Menu ausgegeben
			main.printMenu();
			try {

				// und dann der User Input abgefragt
				String nextInput = main.getScanner().next();
				// TODO Exception Handling des parsens
				int number = Integer.parseInt(nextInput);

				// und dann ausgewertet.
				switch (number) {
				case 0:
					// `isRunning` wird auf false gesetzt, und bei der naechsten
					// Schleife
					// das Programm somit beendet.
					main.setRunning(false);
					System.out.println("Ciao");
					break;
				case 1:
					// Person wird erfasst und dem Array hinzugefuegt
					boolean weiterePersonHinzufuegen = true;

					while (weiterePersonHinzufuegen) {
						Person erfasstePerson = main.erfassePerson();

						System.out.println(erfasstePerson);
						System.out.println("Stimmt's? (J/N)");

						String allesOk = main.getScanner().next();
						while (!allesOk.matches("J|N")) {
							System.out.print("Nur `J` und `N` sind erlaubt: ");
						}

						boolean korrigieren = allesOk.equals("N");

						if (korrigieren) {
							main.erfassePerson(erfasstePerson);
						}

						if (erfasstePerson != null) {
							main.addPerson(erfasstePerson);
						}

						System.out.print("Weitere Person hinzufuegen? J/N:");
						String next = main.getScanner().next();

						while (!next.matches("J|N")) {
							System.out.print("Nur `J` und `N` sind erlaubt: ");
						}

						if (next.equals("N")) {
							weiterePersonHinzufuegen = false;
						}
					}

					break;
				case 2:
					// Alle Personen werden angezeigt
					main.displayPersons();
					break;
				case 3:
					System.out.print("Dateiname zum speichern eingeben: ");
					// TODO Exception handling
					String filenameForSave = main.getScanner().next();
					main.savePersons(filenameForSave);
					break;
				case 4:
					System.out.println("Dateiname zum laden eingeben: ");
					// TODO exception handling
					main.loadPersons(main.getScanner().next());
					break;
				case 5:
					System.out.println("Dateiname zum loeschen eingeben: ");
					// TODO Exception handling
					main.deleteFile(main.getScanner().next());
					break;
				case 6:
					System.out.println("in-memory Records sortieren");
					main.sortiere();
					break;

				default:
					// Wenn der Benutzerinput nicht passt, dann wird nichts
					// gemacht.
					// TODO Fehler anzeigen
				}
			} catch (Exception ex) {
				System.out.println("Ups, da ist etwas schief gelaufen :-(");
				System.out.println(ex.getMessage());

			}
		}
	}

	/**
	 * Delete the given file.
	 * 
	 * @param filename
	 *            the given filename/path
	 */
	private void deleteFile(String filename) {
		// TODO Exception Handling
		File file = new File(filename);
		if (file.delete()) {
			System.out.println("Datei: " + filename + " wurde geloescht.");
		} else {
			System.out.println("Loeschen der Datei " + filename
					+ " hat leider nicht geklappt.");
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
	 *            the person to add
	 */
	private void addPerson(Person person) {
		personen.add(person);
	}

	/**
	 * Print the commands menu to the console.
	 */
	private void printMenu() {
		System.out.println("ADRELI - Adressverwaltung");
		System.out.println(" ");
		System.out.println("Wollen Sie ...");
		System.out.println("0. Programm Beenden");
		System.out.println("1. Neue Person anlegen");
		System.out.println("2. Alle Anzeigen");
		System.out.println("3. Personen speichern");
		System.out.println("4. Personen laden");
		System.out.println("5. Personen Datei loeschen");
		System.out.println("6. Personen sortieren");
		System.out.println();
		System.out
				.println("Bitte tippen Sie ein Menupunkt und bestaetigen mit Enter [0-6]:");
	}

	/**
	 * Ueberladene Methode zum erfassen einer Person, ohne bereits eine
	 * existierende zu haben.
	 * 
	 * @return
	 */
	private Person erfassePerson() {
		return erfassePerson(null);
	}

	/**
	 * Methode zum erfassen einer Person, bei der auf einer bereits
	 * existierenden Person aufgebaut wird.
	 * 
	 * @param existierendePerson
	 *            die existierende Person; kann null sein, falls eine neue
	 *            Person angelegt wird
	 * @return die neue oder modifizierte Person
	 */
	private Person erfassePerson(Person exist) {
		Person neuePerson = null;
		System.out.println("Geben Sie bitte die Daten ein:");
		System.out.println(" ");
		// Scanner wird aufgerufen, gibt der Variable name einen Wert des Types
		// String .next() legt fest dass die Methode bis zur Eingabe wartet und
		// nicht weitergeht.
		if (exist != null)
			System.out.println("Alter Wert: " + exist.getVorname());
		System.out.print("Vorname: ");
		String vorname = erfasseVorname();
		if (exist != null)
			System.out.println("Alter Wert: " + exist.getName());
		System.out.print("Name: ");
		String name = erfasseName();
		if (exist != null)
			System.out.println("Alter Wert: " + exist.getAnrede());
		System.out.print("Anrede: ");
		String anrede = erfasseAnrede();
		if (exist != null)
			System.out.println("Alter Wert: " + exist.getStraße());
		System.out.print("Straße: ");
		String straße = erfasseStrasse();
		if (exist != null)
			System.out.println("Alter Wert: " + exist.getPLZ());
		System.out.print("PLZ: ");
		String plz = erfassePLZ();
		if (exist != null)
			System.out.println("Alter Wert: " + exist.getOrt());
		System.out.print("Ort: ");
		String ort = erfasseOrt();
		if (exist != null)
			System.out.println("Alter Wert: " + exist.getTelefon());
		System.out.print("Telefon: ");
		String telefon = erfasseTelefon();
		if (exist != null)
			System.out.println("Alter Wert: " + exist.getFax());
		System.out.print("Fax: ");
		String fax = erfasseFax();
		if (exist != null)
			System.out.println("Alter Wert: " + exist.getBemerkung());
		System.out.print("Bemerkung: ");
		String bemerkung = erfasseBemerkung();

		// Mehtode immer klammern, klein geschrieben, "NEW" ist ein reserviertes
		// Keyword
		// TODO Exception Handling
		if (name != null && vorname != null && anrede != null && straße != null
				&& plz != null && ort != null && telefon != null && fax != null
				&& bemerkung != null) {
			neuePerson = new Person(name, vorname, anrede, straße, plz, ort,
					telefon, fax, bemerkung);
		}

		return neuePerson;
	}

	private String erfasseVorname() {
		String vorname = null;
		vorname = getScanner().next();

		while (!vorname.matches("[A-Z][a-z]*")) {
			System.out
					.println("Nur führende Großbuchstaben und folgende Kleinbuchstaben erlaubt!");
			System.out.println("Vorname: ");
			vorname = getScanner().next();
		}
		return vorname;
	}

	private String erfasseName() {
		String name = null;
		name = getScanner().next();

		// while (abcdf.length() < 3) {
		while (!name.matches("[A-Z][a-z]*")) {
			System.out
					.println("Nur führende Großbuchstaben und folgende Kleinbuchstaben erlaubt!");
			System.out.print("Name: ");
			name = getScanner().next();
		}

		return name;
	}

	private String erfasseAnrede() {
		String anrede = null;
		anrede = getScanner().next();

		// while (abcdf.length() < 3) {
		while (!anrede.matches("Herr|Frau")) {
			System.out.println("Bitte entweder Herr oder Frau angeben!");
			System.out.print("Anrede: ");
			anrede = getScanner().next();
		}
		return anrede;
	}

	private String erfasseStrasse() {
		String strasse = null;
		getScanner().nextLine();
		strasse = getScanner().nextLine();

		// while (abcdf.length() < 3) {
		while (!strasse.matches("[A-Z][a-z]*\\s[0-9]*")) {
			System.out
					.println("Bitte Straßenname und Hausnr angeben bsp: Musterstrasse 5!");
			System.out.print("Straße: ");
			strasse = getScanner().nextLine();

		}

		return strasse;
	}

	private String erfassePLZ() {
		String plz = null;
		plz = getScanner().next();

		// while (abcdf.length() < 3) {
		while (!plz.matches("[0-9]{5}")) {
			System.out.println("Zahlen von 0-9 und 5 stellen sind erlaubt!");
			System.out.print("PLZ: ");
			plz = getScanner().next();
		}

		return plz;
	}

	private String erfasseOrt() {
		String ort = null;
		getScanner().nextLine();
		ort = getScanner().nextLine();

		// while (abcdf.length() < 3) {
		while (!ort.matches("[A-Z][a-z]*\\s[A-Za-z]*\\s[A-Za-z]*")) {
			System.out
					.println("Bitte den korrekten Ortsnamen angeben bsp: Frankfurt o. Frankfurt am Main");
			System.out.print("Ort: ");
			ort = getScanner().nextLine();
		}

		return ort;
	}

	private String erfasseTelefon() {
		String telefon = null;
		telefon = getScanner().next();

		// while (abcdf.length() < 3) {
		while (!telefon.matches("[0-9].{3,14}")) {
			System.out
					.println("Nur Zahlen von 0-9 und 3-14 stellen sind erlaubt!");
			System.out.print("Telefon: ");
			telefon = getScanner().next();
		}

		return telefon;
	}

	private String erfasseFax() {
		String fax = null;
		fax = getScanner().next();

		// while (abcdf.length() < 3) {
		while (!fax.matches("[0-9].{3,14}")) {
			System.out
					.println(" NurZahlen von 0-9 und 3-14 stellen sind erlaubt!");
			System.out.print("Fax: ");
			fax = getScanner().next();
		}

		return fax;
	}

	private String erfasseBemerkung() {
		String bemerkung = null;
		bemerkung = getScanner().next();

		// while (abcdf.length() < 3) {
		while (!bemerkung.matches("[^;]{4,300}")) {
			System.out.println("Bitte Bemerkung korrekt eingeben !");
			System.out.print("Bemerkung: ");
			bemerkung = getScanner().next();
		}

		return bemerkung;
	}

	/**
	 * Saves the in-memory `personen` to the given file.
	 * 
	 * @param filename
	 *            the filename
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
	 *            the file to load
	 * @throws Exception
	 * @throws IOException
	 */
	public void loadPersons(String filename) throws Exception {
		Path file = Paths.get(filename);
		// TODO Exception Handling
		try {
			List<String> allLines = Files.readAllLines(file,
					Charset.forName("UTF-8"));
			for (String line : allLines) {
				// Create Persons via the given serialized strings and add them
				// to
				// the
				// `personen` array.
				// TODO Exception Handling
				personen.add(new Person(line));
			}
		} catch (Exception ex) {
			throw new Exception("Beim laden ist etwas schief gelaufen");
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
	 *            the isRunning to set
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

	private void sortiere() {
		Collections.sort(personen);
	}
}