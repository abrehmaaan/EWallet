import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestEWallet {
    public static void main(String[] args) throws IOException, ParseException{
        if (args.length == 1 && args[0].equals("demo")){
        // run program in demo moded
        new EWallet();
        }
        else{
        // run live program
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter id");
        String id = myObj.nextLine();
        System.out.println("Please enter first name");
        String fname = myObj.nextLine();
        System.out.println("Please enter last name");
        String lname = myObj.nextLine();
        System.out.println("Please enter any other names");
        String oname = myObj.nextLine();
        System.out.println("Please enter sex in all caps");
        String se = myObj.nextLine();
        Sex sex=Sex.valueOf(se);
        System.out.println("Please enter date of birth in dd/mm/yy format");
        String dateString = myObj.next();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        java.util.Date dob = formatter.parse(dateString);
        EWallet wallet=new EWallet(id, fname, oname, lname, sex, dob);
        System.out.println(wallet.saveToFile());
        try {
 
            FileInputStream fileIn = new FileInputStream(id+".txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            EWallet my=null;
            my= (EWallet)objectIn.readObject();
            objectIn.close();
            System.out.println("1. Display wallet information");
            System.out.println("2. Send Funds to another wallet ");
            System.out.println("3. Process a message from another wallet.");
            System.out.println("4. Quit ");
            int display=myObj.nextInt();
            Transfer transaction=new Transfer();
            if (display==1)
            {
                System.out.println(my);
            }
            else if(display==2)
            {
                System.out.println("Please enter id to send funds to");
                String id2=myObj.nextLine();
                System.out.println("amount to be sent");
                double amt=myObj.nextDouble();
                if (amt<=my.balance)
                {
                    System.out.println("SEND");
                }
                else{
                    System.out.println("");
                }
            }
            else if(display==3)
            {
                System.out.println("Please enter message");
                String message=myObj.nextLine()+myObj.nextLine();
                System.out.println("Please enter id to send funds to");
                String id2=myObj.nextLine()+myObj.nextLine();
                if (message=="SEND")
                {
                    my.respond("SEND,"+","+transaction.getTranId()+","+transaction.getToken()+","+id+","+id2+","+transaction.getAmount());
                }
                else if (message==("THANKS"))
                {
                    my.respond("THANKS"+","+transaction.getTranId()+","+transaction.getToken()+","+id+","+id2);
                }
                else if (message==("WELCOME"))
                {
                    my.respond("WELCOME"+","+transaction.getTranId()+","+transaction.getToken()+","+id+","+id2);
                }
            }
            else if (display==4)
            {
                System.out.println("are you sure you want to quit?");
                String answer = myObj.nextLine()+myObj.nextLine();
                if (answer==("yes"))
                {
                    ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream("Wallet.txt"));
                    out.writeObject(my);
                }
                else{
                    System.out.println("");
                }
            }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
        } 
        
        
       
}
