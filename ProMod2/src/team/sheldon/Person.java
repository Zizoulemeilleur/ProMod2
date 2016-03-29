package team.sheldon;

/**
 * A Person ...
 */
public class Person implements Comparable<Person> {

	/**
	 * Eine abfolge von speziellen Sonderzeichen, die verwendet werden, um die
	 * einzelnen Attribute einer Person in einem String zu Trennen. Bsp: { name:
	 * 'Bryant, vorname: 'Kobe', alter: 38 } => "Bryant%%%Kobe%%%38"
	 */
	public static final String SEPARATOR = "%%%";
	private String name;
	private String vorname;
	private String anrede;
	private String straße;
	private String plz;
	private String ort;
	private String telefon;
	private String fax;
	private String bemerkung;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            the name
	 * @param vorname
	 *            the vorname
	 */
	public Person(String name, String vorname, String anrede, String straße,
			String plz, String ort, String telefon, String fax, String bemerkung) {
		this.setName(name);
		this.setVorname(vorname);
		this.setAnrede(anrede);
		this.setStraße(straße);
		this.setPLZ(plz);
		this.setOrt(ort);
		this.setTelefon(telefon);
		this.setFax(fax);
		this.setBemerkung(bemerkung);
	}

	/**
	 * Constructor to create a person via a serialized string. Each property has
	 * to be separated by the {@link #SEPARATOR}.
	 * 
	 * @param fromFileString
	 *            the serialized string representing a person
	 */
	public Person(String fromFileString) {
		String[] strings = fromFileString.split(SEPARATOR);
		// TODO Exception Handling
		this.setName(strings[0]);
		this.setVorname(strings[1]);
		this.setAnrede(strings[2]);
		this.setStraße(strings[3]);
		this.setPLZ(strings[4]);
		this.setOrt(strings[5]);
		this.setTelefon(strings[6]);
		this.setFax(strings[7]);
		this.setBemerkung(strings[8]);

	}

	@Override
	public String toString() {
		return "[ " + "Name:" + getName() + "\n" + "Vorname: " + getVorname()
				+ "\n" + "Anrede: " + getAnrede() + "\n" + "Straße: "
				+ getStraße() + "\n" + "PLZ:" + getPLZ() + "\n" + "Ort:"
				+ getOrt() + "\n" + "Telefon:" + getTelefon() + "\n" + "Fax:"
				+ getFax() + "\n" + "Bemerkung:" + getBemerkung() + "]";
	}

	/**
	 * Serializes this person to a string.
	 * 
	 * @return the serialized string
	 */
	public String toSerializedString() {
		// TODO all properties
		return getName() + SEPARATOR + getVorname() + SEPARATOR + getAnrede()
				+ SEPARATOR + getStraße() + SEPARATOR + getPLZ() + SEPARATOR
				+ getOrt() + SEPARATOR + getTelefon() + SEPARATOR + getFax()
				+ SEPARATOR + getBemerkung();
	}

	@Override
	public int compareTo(Person p) {
		// TODO Auto-generated method stub
		return this.name.compareTo(p.getName());
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnrede() {
		return anrede;
	}

	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}

	public String getStraße() {
		return straße;
	}

	public void setStraße(String straße) {
		this.straße = straße;
	}

	public String getPLZ() {
		return plz;
	}

	public void setPLZ(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getBemerkung() {
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}
}
