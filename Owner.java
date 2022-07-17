import java.io.Serializable;
import java.util.Date;

public class Owner extends Person implements Serializable{
  String id;
  public String fn;
  public String on;
  public String ln;
  public Person person;

  /** primary constructor that accepts owner info */
  public Owner(String fn, String on, String ln, Date dob, Sex sex){
    super(dob, sex);
    this.fn = fn;
    this.on = on;
    this.ln = ln;

  }

  /** secondary constructor that accepts owner id and owner info */
  public Owner(String id, String fn, String on, String ln, Date dob, Sex sex){
    super(dob, sex);
    this.id = id;
    this.fn = fn;
    this.on = on;
    this.ln = ln;
    }
 /** method that returns owner id */
  public String getId(){
    return id;
  }
  public Sex getSex() {
    return sex;
  }
  public Date getDob() {
    return dob;
  }
/** method that returns a formatted string with owner names */
  public String getName(){
    String str = ln + "," + fn + on;
    return str;
  }
}