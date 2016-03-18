package team.sheldon;

public class Person {

  private String name;
  
  public Person(String newName) {
    this.name = newName;
  }
  
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  
  /**
   * @param name the name to set
   */
  public void setName(String newName) {
    this.name = newName;
  }
  
  
  @Override
  public String toString() {
    return "[ Name:" + name + " ]";
  }
}
