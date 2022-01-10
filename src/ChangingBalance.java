import BD.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public  abstract  class ChangingBalance {

    public static boolean changing(Connection connection, Account account) {
        Statement statement = null;
        boolean status;
        try {
            statement = connection.createStatement();
            statement.execute("UPDATE client SET BALANCE = " + account.getBalance() + "WHERE " + account.getAccountNumber());
            status = true;
        }catch (SQLException e){
            status = false;
            throw new DbException(e.getMessage());
        }

        return status;

    }
}
