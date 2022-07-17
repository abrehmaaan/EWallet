public class Name {
    private String firstName;
    private String lastName;
    private String otherNames;

      /** constructor that accepts the first, last, and other name, if necessary, of a person*/

    public Name(String firstname, String lastname, String othername){
        this.firstName = firstname;
        this.lastName = lastname;
        this.otherNames = othername;
    }
      /** method that returns the first name of a person*/

    public String getFName(){
        return firstName;
    }
/** method that returns the last name of a person*/
    public String getLName(){
        return lastName;
    }

 /** returns the other name of a person if applicable */
    public String getOName(){
        return otherNames;
    }


}
