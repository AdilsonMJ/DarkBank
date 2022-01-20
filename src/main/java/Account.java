import BD.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account extends Client{

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
    }

    public void login(String accountNumber) {
        if (TestingIfExistAccount.testIfExistAccount(accountNumber)){
            try {
                ResultSet rst = RecoverData.recoveryData(accountNumber);
                 while (rst.next()) {
                    this.setName((rst.getString("NAME")));
                    this.setAge(rst.getInt("AGE"));
                    this.setBalance(rst.getDouble("BALANCE"));
                    this.setTypeOfAccount(rst.getString("TYPEOFACCOUNT"));
                }
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public Boolean CreatingAccount(String typeOfAccount, String accountNumber, String name, int age){

        boolean existAccount = TestingIfExistAccount.testIfExistAccount(accountNumber);
        if (!existAccount){
            return InsertDates.inserting(this.connetion, typeOfAccount, accountNumber, name, age);
        }

        return false;
    }

    public void deleteAccount(String accountNumber){
        DeleteData.deleteData(accountNumber);
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

    public double getBalance() {
        return Balance.balance(this);
    }

    public void closerConnection(){
        BDBank.closer(this.stm);
        BD.BDBank.closer(this.connetion);
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

    public StringBuilder getAccount() {
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append(this.accountNumber + "\n");
        accountNumber.append(this.typeOfAccount+ "\n");
        accountNumber.append(this.getName()+ "\n");
        accountNumber.append(this.getAge()+ "\n");

        return accountNumber;
    }
}
