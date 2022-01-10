import BD.BDBank;
import BD.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account extends Client{

    private static int total = 0;

    private Connection connetion = null;
    private Statement stm = null;

    private String typeOfAccount, accountNumber;
    private double balance;

    public Account() {

        try {
            connetion  = BDBank.getConnection();
            stm = connetion.createStatement();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }

        Account.total += 1;
    }

    public void deposit(double cash){
       this.balance += cash;
       boolean status = ChangingBalance.changing(connetion, this);
    }


    public StringBuilder getAccount() {
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append(this.accountNumber + "\n");
        accountNumber.append(this.typeOfAccount+ "\n");
        accountNumber.append(this.getName()+ "\n");
        accountNumber.append(this.getAge()+ "\n");

        return accountNumber;
    }

    public double getBalance() {


        try{

            stm.execute("SELECT BALANCE from client");
            ResultSet rst = stm.getResultSet();
            double balance;

            while(rst.next()){
                balance = rst.getDouble("BALANCE");
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return balance;
    }

    public void closerConnection(){
        BDBank.closerConnection(this.stm, this.connetion);
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void insertDates(String typeOfAccount, String accountNumber, String name, int age){

        super.setName(name);
        super.setAge(age);
        this.typeOfAccount = typeOfAccount;
        this.accountNumber = accountNumber;

        boolean status = InsertDates.inserting(this.connetion, this);
    }
}
