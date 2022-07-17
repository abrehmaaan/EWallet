import java.io.Serializable;
import java.util.Date;
/** abstract class that represents a person */
public abstract class Person implements Serializable{

	protected Date dob;
	protected Sex  sex;

  /** abstract  method to retrieve a person's dob */
	public abstract Date getDob();
  /** abstract  method to retrieve a person's sex */
	public abstract Sex getSex();

  /** constructor that accepts the date of birth and sex of a person */
	public Person(Date dob, Sex sex){
		this.dob = dob;
		this.sex = sex;
	}
	
}