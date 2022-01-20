import BD.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class InsertDates {

    public static boolean inserting(Connection connection,  String typeOfAccount, String accountNumber, String name, int age) {

        boolean status;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO client "
                            + "(NAME, AGE, BALANCE, TYPEOFACCOUNT, ACCOUNTNUMBER)"
                            + "values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setDouble(3, 0.0);
            preparedStatement.setString(4, typeOfAccount);
            preparedStatement.setString(5, accountNumber);

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
