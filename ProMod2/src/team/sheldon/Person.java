package team.sheldon;

/**
 * A Person ...
 */
public class Person {

  /**
   * Eine abfolge von speziellen Sonderzeichen, die verwendet werden, um die
   * einzelnen Attribute einer Person in einem String zu Trennen. Bsp: { name:
   * 'Bryant, vorname: 'Kobe', alter: 38 } => "Bryant%%%Kobe%%%38"
   */
  private static final String SEPARATOR = "%%%";
  private String name;
  private String vorname;

  /**
   * Constructor.
   * 
   * @param name
   *          the name
   * @param vorname
   *          the vorname
   */
  public Person(String name, String vorname) {
    this.name = name;
    this.vorname = vorname;
  }

  /**
   * Constructor to create a person via a serialized string. Each property has
   * to be separated by the {@link #SEPARATOR}.
   * 
   * @param fromFileString the serialized string representing a person
   */
  public Person(String fromFileString) {
    String[] strings = fromFileString.split(SEPARATOR);
    // TODO Exception Handling
    this.name = strings[0];
    this.vorname = strings[1];
  }

  @Override
  public String toString() {
    return "[ " + "Name:" + name + "\n" + "Vorname: " + vorname + "]";
  }

  /**
   * Serializes this person to a string.
   * @return the serialized string
   */
  public String toSerializedString() {
    // TODO all properties
    return name + SEPARATOR + vorname;
  }
}
