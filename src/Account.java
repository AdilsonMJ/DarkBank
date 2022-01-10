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
    private double balance = 0;

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
       Balance.changingBalance((cash + this.getBalance()), this.getAccountNumber());
    }


    public void withdrawing(double cash){
        if (this.getBalance() >= cash){
            Balance.changingBalance((this.getBalance() - cash), this.getAccountNumber());
        } else{
            System.out.println("insufficient balance");
        }
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
        return Balance.balance(this);
    }

    public void closerConnection(){
        BDBank.closerConnection(this.stm, this.connetion);
    }

    public void CreatingAccount(String typeOfAccount, String accountNumber, String name, int age){

        super.setName(name);
        super.setAge(age);
        this.typeOfAccount = typeOfAccount;
        this.accountNumber = accountNumber;

        boolean status = InsertDates.inserting(this.connetion, this);
    }


    public void login(String accountNumber){
        this.accountNumber = accountNumber;
        try {
            ResultSet rst = stm.executeQuery("select *  FROM CLIENT WHERE ACCOUNTNUMBER = " + accountNumber);


            while (rst.next()){
                System.out.println(rst.getString("NAME"));
                this.setAge(rst.getInt("AGE"));
                this.setBalance(rst.getDouble("BALANCE"));
                this.setTypeOfAccount(rst.getString("TYPEOFACCOUNT"));
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }

    }


    private void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
