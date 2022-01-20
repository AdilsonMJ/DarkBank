package BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecoverData {

    public static ResultSet recoveryData(String accountNumber){
        try {
            Statement stm = BDBank.getConnection().createStatement();
            return stm.executeQuery("select * FROM CLIENT WHERE ACCOUNTNUMBER = " + accountNumber);
        }catch (
                SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}
