package BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class TestingIfExistAccount {

    public static boolean testIfExistAccount(String accountNumber) {

        Statement stm = null;
        ResultSet resultSet = null;
        boolean status = false;

        try{
            stm = BDBank.getConnection().createStatement();
            resultSet = stm.executeQuery("select * FROM CLIENT WHERE ACCOUNTNUMBER = " + accountNumber);

            while (resultSet.next()){
                status = resultSet.getString("ACCOUNTNUMBER").equals(accountNumber);
            }


        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            BDBank.closer(stm);
        }

        return status;
    }
}
