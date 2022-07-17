public class ETransaction implements EAudit{

    protected String status;
    protected String To;
    protected String from;
    public String date;
    public String Token;
    public int value;
    public String TranId;
    /** method that returns the transaction ID */
    public String getTranId(){
      return this.TranId;
    }
    /** method that adds the required amount of 0s to the left of the transaction ID to allow 8 digits */
    
    public String Pad(String TranId, int length) {
      if (TranId.length() >= length) {
          return TranId;
      }
      
      StringBuilder sb = new StringBuilder();
      while (sb.length() < length - TranId.length()) {
          sb.append('0');
      }
      sb.append(TranId);
  
      return sb.toString();
  }
    /** method that returns the date of the transaction */
    public String getDate(){
      return this.date;
    }
    /** method that returns the token that is sent/recieved with a transaction */
    public String getToken(){
      return Token;
    }
    /** method that returns the id of the wallet to which the transaction was sent */
    public String getTo(){
      return this.To;
    }
    /** method that returns the id of the wallet from which the transaction was sent */
    public String getFrom(){
      return this.from;
    }
    /** method that returns the stautus of the transaction */
    public String getStatus(){
      return this.status;
    }
    /** method that that sets the status of the transaction to the parameter
  value. */
    public void setStatus(){
      int value = Integer.parseInt(status);    
    }
    
  }