import BD.BDBank;
import BD.DbException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class  Balance {

    public static double balance( Account account){
        double balance = 0;
        try{
            Statement stm = BDBank.getConnection().createStatement();
            stm.execute("SELECT BALANCE from client where " + account.getAccountNumber());
            ResultSet rst = stm.getResultSet();

            while(rst.next()){
                balance = rst.getDouble("BALANCE");
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }

        return balance;
    }

    public static void changingBalance( double cash, String accountnumber) {
        try {

            PreparedStatement stm = BDBank.getConnection().prepareStatement(
                    "UPDATE client set BALANCE=? where ACCOUNTNUMBER=?"
            );
            stm.setDouble(1, cash);
            stm.setString(2, accountnumber);
            stm.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
}
}
