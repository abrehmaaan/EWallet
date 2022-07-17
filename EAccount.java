
public class EAccount{
    private double balance;
  
    /** contructor that accepts the balance of the account */
    public EAccount(double balance){
      this.balance = balance;
    }
  
    /** method that subtracts an amount from the account balance */
    public boolean deposit(double amount){
      if (amount > 0)
        this.balance += amount;
        return true;
    }
  
    /** method that adds an amount to the acount balance */
    public boolean withdraw(double amount){
      if (amount <= balance){
        this.balance -= amount;
        return true;}
      else
        return false;
    }
  
    /** method that returns the balance of the account */
    public double getBalance(){
      return this.balance;
    }
  } 