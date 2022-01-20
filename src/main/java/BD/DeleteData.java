package BD;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteData {

    public static void deleteData(String accountNumber){
        Statement stm = null;
        String sql = "DELETE FROM client WHERE ACCOUNTNUMBER = " +  accountNumber;
        try {
            stm = BDBank.getConnection().createStatement();
            stm.executeUpdate(sql);
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            BDBank.closer(stm);
        }
    }

}
