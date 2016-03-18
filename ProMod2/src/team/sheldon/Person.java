package team.sheldon;

public class Person {

  private static final String TRENNER = "%%%";
  private String name;
  private String vorname;
  
  public Person(String newName, String newVorname) {
    this.name = newName;
    this.vorname = newVorname;
  }
  
  public Person(String fromFileString) {
    String[] strings = fromFileString.split(TRENNER);
    this.name = strings[0];
    this.vorname = strings[1];
  }
  
  @Override
  public String toString() {
    return "[ " +
        "Name:" + name + "\n" +
        "Vorname: " +  vorname +
        "]";
  }
  
  public String toFileString() {
    return name + TRENNER + vorname;
  }
}
