import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class EWalletGUI extends JFrame {
	private JPanel topPanel, secondPanel, thirdPanel;
	private JLabel ownerText, projectName, walletText, balanceText, transactionText, sendText, amountText, incMsgText, outMsgText;
    private JTextField owner, walletID, balance, sendTo, amount, incMsg, outMsg;
    private JRadioButton all, committed, uncommitted, failed;
    private ButtonGroup bg;
    private JTable transactions;
    private DefaultTableModel daDefaultTableModel;
    private JScrollPane scrollPane;
    private JButton send, respond, clear, quit;
    private int trans;
    private Transfer transaction;
    private static EWallet wallet;
	
	public EWalletGUI(String ownerName, String id) {
		setTitle("Electronic Wallet");
		trans = 1;
        transaction=new Transfer();
		
		topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBounds(0,0,1250,30);
        topPanel.setBackground(new Color(0, 0, 255));
        projectName = new JLabel("UWI Electronic Wallet Project", SwingConstants.CENTER);
        Font f1 = new Font("Bookman Old Style", Font.BOLD, 18);
        Font f2 = new Font("Bookman Old Style", Font.PLAIN, 18);
        projectName.setForeground(Color.WHITE);
        projectName.setFont(f1);
        ownerText = new JLabel("Owner", SwingConstants.CENTER);
        ownerText.setForeground(Color.WHITE);
        ownerText.setFont(f1);
        walletText = new JLabel("Wallet ID", SwingConstants.CENTER);
        walletText.setForeground(Color.WHITE);
        walletText.setFont(f1);
        owner = new JTextField(10);
        owner.setBackground(Color.WHITE);
        owner.setForeground(new Color(0, 0, 255));
        owner.setFont(f2);
        owner.setText(ownerName);
        owner.setEditable(false);
        walletID = new JTextField(10);
        walletID.setBackground(Color.WHITE);
        walletID.setForeground(new Color(0, 0, 255));
        walletID.setFont(f2);
        walletID.setText(id);
        walletID.setEditable(false);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.weightx = 0.5;
        c.weighty = 0;
        topPanel.add(ownerText, c);
        c.gridx=1;
        c.gridy=0;
        topPanel.add(owner, c);
        c.gridx=2;
        c.gridy=0;
        topPanel.add(projectName, c);
        c.gridx=3;
        c.gridy=0;
        topPanel.add(walletText, c);
        c.gridx=4;
        c.gridy=0;
        topPanel.add(walletID, c);
        
        secondPanel = new JPanel();
        secondPanel.setLayout(new GridBagLayout());
        secondPanel.setBounds(30,40,1180,500);
        secondPanel.setBackground(Color.WHITE);
        Border greenline = BorderFactory.createLineBorder(Color.GREEN);
        secondPanel.setBorder(greenline);
        balanceText = new JLabel("Balance", SwingConstants.CENTER);
        balanceText.setForeground(Color.BLACK);
        balanceText.setFont(f1);
        transactionText = new JLabel("Transactions", SwingConstants.CENTER);
        transactionText.setForeground(Color.BLACK);
        transactionText.setFont(f1);
        balance = new JTextField(10);
        balance.setBackground(Color.WHITE);
        balance.setForeground(new Color(0, 0, 255));
        balance.setFont(f2);
        balance.setText("250");
        balance.setEditable(false);
        all = new JRadioButton("All", true);
        all.setBackground(Color.WHITE);
        all.setFont(f1);
        committed = new JRadioButton("Committed");
        committed.setBackground(Color.WHITE);
        committed.setFont(f1);
        uncommitted = new JRadioButton("Uncommitted");
        uncommitted.setBackground(Color.WHITE);
        uncommitted.setFont(f1);
        failed = new JRadioButton("Failed");
        failed.setBackground(Color.WHITE);
        failed.setFont(f1);
        bg=new ButtonGroup();    
        bg.add(all);
        bg.add(committed);
        bg.add(uncommitted);
        bg.add(failed);
        c.gridx=0;
        c.gridy=0;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets =new Insets(0,30,0,30);
        secondPanel.add(balanceText, c);
        c.gridx=1;
        c.gridy=0;
        secondPanel.add(balance, c);
        c.gridx=0;
        c.gridy=1;
        secondPanel.add(transactionText, c);
        c.gridx=1;
        c.gridy=1;
        secondPanel.add(all, c);
        c.gridx=2;
        c.gridy=1;
        secondPanel.add(committed, c);
        c.gridx=3;
        c.gridy=1;
        secondPanel.add(uncommitted, c);
        c.gridx=4;
        c.gridy=1;
        secondPanel.add(failed, c);
        daDefaultTableModel = new DefaultTableModel(0, 0);
        String cols[] = {"From","To","Amount","Status", "ID"};
        daDefaultTableModel.setColumnIdentifiers(cols);
        transactions = new JTable(daDefaultTableModel) {
        	public boolean isCellEditable(int rows, int cols) {
        		return false;
        	}
        };
        transactions.setBackground(Color.WHITE);
        transactions.setForeground(Color.BLACK);
        transactions.setFont(f2);
        transactions.getColumnModel().getColumn(0).setPreferredWidth(240);
        transactions.getColumnModel().getColumn(1).setPreferredWidth(240);
        transactions.getColumnModel().getColumn(2).setPreferredWidth(240);
        transactions.getColumnModel().getColumn(3).setPreferredWidth(240);
        transactions.getColumnModel().getColumn(4).setPreferredWidth(240);
		scrollPane = new JScrollPane(transactions);
		scrollPane.setVisible(true);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5;
		secondPanel.add(scrollPane, c);
		
		thirdPanel = new JPanel();
        thirdPanel.setLayout(new GridBagLayout());
        thirdPanel.setBounds(0,530,1250,210);
        thirdPanel.setBackground(Color.WHITE);
        sendText = new JLabel("Send To", SwingConstants.CENTER);
        sendText.setForeground(Color.BLACK);
        sendText.setFont(f1);
        amountText = new JLabel("Amount", SwingConstants.CENTER);
        amountText.setForeground(Color.BLACK);
        amountText.setFont(f1);
        incMsgText = new JLabel("Incoming Message", SwingConstants.CENTER);
        incMsgText.setForeground(Color.BLACK);
        incMsgText.setFont(f1);
        outMsgText = new JLabel("Outgoing Message", SwingConstants.CENTER);
        outMsgText.setForeground(Color.BLACK);
        outMsgText.setFont(f1);
        sendTo = new JTextField(10);
        sendTo.setBackground(Color.WHITE);
        sendTo.setForeground(new Color(0, 0, 255));
        sendTo.setFont(f2);
        amount = new JTextField(10);
        amount.setBackground(Color.WHITE);
        amount.setForeground(new Color(0, 0, 255));
        amount.setFont(f2);
        incMsg = new JTextField(30);
        incMsg.setBackground(Color.WHITE);
        incMsg.setForeground(new Color(0, 0, 255));
        incMsg.setFont(f2);
        outMsg = new JTextField(30);
        outMsg.setBackground(Color.WHITE);
        outMsg.setForeground(new Color(0, 0, 255));
        outMsg.setFont(f2);
        send = new JButton("   Send   ");
        send.setBackground(new Color(0, 0, 255));
        send.setForeground(Color.WHITE);
        send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Double bal = Double.parseDouble(balance.getText());
				String sendID = sendTo.getText();
				Double amnt = Double.parseDouble(amount.getText());
				if(amnt <= bal) {
					balance.setText(String.valueOf(bal-amnt));
					outMsg.setText("SEND");
					Object obj[] = new Object[] {
			        		id,sendID,amnt,"PENDING", trans
			        };
					trans++;
					daDefaultTableModel.addRow(obj);
				}
				
			}
        	
        });
        respond = new JButton("Respond");
        respond.setBackground(new Color(0, 0, 255));
        respond.setForeground(Color.WHITE);
        respond.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = incMsg.getText();
				String id2 = sendTo.getText();
				String amnt = amount.getText();
				if (msg.matches("SEND"))
                {
                    wallet.respond("SEND"+","+transaction.getTranId()+","+transaction.getToken()+","+id+","+id2+","+transaction.getAmount());
                    outMsg.setText("SEND"+","+trans+","+id+","+id2+","+amnt);
                }
                else if (msg.matches("THANKS"))
                {
                    wallet.respond("THANKS"+","+transaction.getTranId()+","+transaction.getToken()+","+id+","+id2);
                    outMsg.setText("THANKS"+","+trans+","+id+","+id2);
                }
                else if (msg.matches("WELCOME"))
                {
                    wallet.respond("WELCOME"+","+transaction.getTranId()+","+transaction.getToken()+","+id+","+id2);
                    outMsg.setText("WELCOME"+","+trans+","+id+","+id2);
                }
			}
        	
        });
        clear = new JButton("Clear");
        clear.setBackground(new Color(0, 0, 255));
        clear.setForeground(Color.WHITE);
        clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendTo.setText("");
				amount.setText("");
				incMsg.setText("");
				outMsg.setText("");	
			}
        	
        });
        quit = new JButton("Quit");
        quit.setBackground(new Color(0, 0, 255));
        quit.setForeground(Color.WHITE);
        quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
        	
        });
        c.gridx=0;
        c.gridy=0;
        c.fill = 0;
        c.weighty = 0;
        c.insets = new Insets(3,0,3,0);
		c.gridwidth = 1;
        thirdPanel.add(sendText, c);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        thirdPanel.add(sendTo, c);
        c.gridx = 2;
        c.gridy = 0;
        c.fill = 0;
        thirdPanel.add(amountText, c);
        c.gridx = 3;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        thirdPanel.add(amount, c);
        c.gridx = 4;
        c.gridy = 0;
        c.fill = 0;
        thirdPanel.add(send, c);
        c.gridx = 0;
        c.gridy = 1;
        thirdPanel.add(incMsgText, c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        thirdPanel.add(incMsg, c);
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = 0;
        thirdPanel.add(respond, c);
        c.gridx = 0;
        c.gridy = 2;
        thirdPanel.add(outMsgText, c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        thirdPanel.add(outMsg, c);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = 0;
        thirdPanel.add(clear, c);
        c.gridx = 3;
        c.gridy = 3;
        thirdPanel.add(quit, c);
        
        add(topPanel);
        add(secondPanel);
        add(thirdPanel);

        getContentPane().setBackground(Color.WHITE);
        setSize(1250,750);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String [] args) throws ParseException{
        String id = "DEMO0000";
        String fname = "John";
        String lname = "Doe";
        String oname = "";
        String se = "MALE";
        Sex sex=Sex.valueOf(se);
        String dateString = "1/1/1970";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        java.util.Date dob = formatter.parse(dateString);
        wallet=new EWallet(id, fname, oname, lname, sex, dob);
        wallet.saveToFile();
		new EWalletGUI(fname+" "+lname,id);
	}
}
