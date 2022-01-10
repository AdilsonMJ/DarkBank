import BD.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class InsertDates {

    public static boolean inserting(Connection connection,  Account account) {

        boolean status;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO client "
                            + "(NAME, AGE, BALANCE, TYPEOFACCOUNT, ACCOUNTNUMBER)"
                            + "values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, account.getName());
            preparedStatement.setInt(2, account.getAge());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4, account.getTypeOfAccount());
            preparedStatement.setString(5, account.getAccountNumber());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            status = true;

        } catch (SQLException e){
            status = false;
            throw new DbException(e.getMessage());
        }

        return status;
    }
}
