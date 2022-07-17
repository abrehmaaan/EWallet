import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class EWallet implements Serializable
{
    
    String owner, walletid;
    double balance,amount;
    Owner own;
    int i;
    ArrayList<Transfer> transaction;
    public EWallet()
    {
        owner="John Doe";
        walletid= "DEMO0000";
        balance=250;
    }
    public EWallet (String id, String fn, String on, String ln, Sex sex,Date dob)
    {
        own= new Owner(id ,fn,on, ln, dob, sex);
    }
    public EWallet(String id) throws IOException
    {
      id=own.id;
      Writer output = null;
      File file = new File("Z:\\Documents\\walletid2.txt");
      output = new BufferedWriter(new FileWriter(file));
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine())
          System.out.println(sc.nextLine());
      sc.close();
      output.close();
 }
    public String getWalletid()
    {
        return own.id;
    }
    public String getInfo()
    {
      EWallet walle=new EWallet();
        String wallet=String.format("Wallet:"+walle.walletid+"  "+walle.owner+""+"("+Sex.MALE+")"+"\n");  
        String bal=String.format("Account Balance:"+walle.balance+"\n");  
        String wdrawal=String.format("Pending Withdrawals:"+100.00+"\n");
        String deposit=String.format("Pending Deposits:"+50.00+"\n");
        String ftransfer=String.format("Failed Transs:"+2);
        return wallet+bal+wdrawal+deposit+ftransfer;
    }
    public String sendFunds(String wallet, String currency, double amt)
    {
        if (balance>=amt)
        {

          Transfer Trans= new Transfer();
          Trans.status=("Pending");
          Trans.from=walletid;
          Trans.To=own.getId();
          Trans.amount=amt;
          Trans.TranId= Trans.Pad(Trans.getTranId(), 8);
          Trans.Token=walletid+own.getId()+Trans.getTranId();
          transaction.add(Trans);
        }
        return ("SEND");
    }
    public String respond(String imsg)
    {
      Transfer Trans1= new Transfer();
      String[] msg= imsg.split(",");
      if (msg[2]!=(msg[3]+msg[4]+msg[1]))
      {
        return("FAILED");
      }
      else if(msg[0]==("SEND"))
      {
        Trans1.To=msg[4];
        Trans1.from=msg[3];
        Trans1.amount=Integer.parseInt(msg[5]);
        Trans1.status=("PENDING");
        Trans1.TranId= Trans1.Pad(msg[1], 8);
        Trans1.Token=msg[3]+msg[4]+msg[1];
        transaction.add(Trans1);
      }
      else if(msg[0]==("THANKS"))
      {
        EAccount account= new EAccount(balance);
        Trans1.status=("COMMITED");
        Trans1.amount=account.getBalance()-amount;
        return ("WELCOME");
      }
      else if(msg[0]==("WELCOME"))
      {
        EAccount account= new EAccount(balance);
        Trans1.status=("COMMITED");
        Trans1.amount=account.getBalance()+amount;
        return("");
      }
      else if(msg[0]==("FAILED"))
      {
        Trans1.status=("FAILED");
        return("");
      }
      return ("");
    }
    public List<String> getTrans(int mode)
    {
      ArrayList<String> transactin = new ArrayList<>();
        if (mode==0)
        {
          return transactin;
        }
        else if (mode==1)
        {
          for (int i=0;i<transaction.size();i++);
          {
            if(transaction.get(i).getStatus()==("PENDING"))
            {
              transactin.add(""+transactin.get(i));
            }
            return transactin;
          }
        }
        else if (mode==2)
        {
          for (int i=0;i<transaction.size();i++);
          {
            if(transaction.get(i).getStatus()==("COMMITTED"))
            {
              transactin.add(""+transactin.get(i));
            }
            return transactin;
          }
        }
        return transactin;
    }
    public String saveToFile(){
      try {
        EWallet walle=new EWallet(own.id, own.fn, own.on, own.ln, own.sex, own.dob);
        File fileOut = new File(getWalletid()+".txt");
        ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(fileOut));
        objectOut.writeObject(walle);
        objectOut.close();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
   return ("");
} 
}