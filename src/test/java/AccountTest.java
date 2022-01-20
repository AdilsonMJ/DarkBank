import BD.TestingIfExistAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    /*
         Already exists Account in DB.
     * NAME = Will Rei do gado
     * AGE = 24
     * BALANCE = 4000
     * TYPEOFACCOUNT = Saving
     * ACCOUNTNUMBER = 232324

     This Data will be creating and delete on this test.

     * NAME = Adilson Testador
     * AGE = 28
     * BALANCE = 20000.00
     * TYPEOFACCOUNT = Saving
     * ACCOUNTNUMBER = 12345
      */



    Account account;

    @BeforeEach
    public void instanciar(){
        account = new Account();
    }

    @Test
    void loginTestingIfAccountExistShouldBeReturnTheNameIfSucess() {
        account.login("232324");
        assertEquals("Will Rei do gado", account.getName());
    }

    @Test
    void loginTryLoginIfAAccountNotExistShouldBeReturnNull() {
        account.login("2323249");
        assertEquals(null, account.getName());
    }


    @Test
    void creatingAccountReturnTrueSucess() {
       boolean status = account.CreatingAccount("saving", "101010", "Adilson", 23);
        assertTrue(status);
    }


    @Test
    void creatingAccountReturnFalseIfAccountExist() {
        boolean status = account.CreatingAccount("saving", "101010", "Adilson", 23);
        assertFalse(status);
    }


    @Test
    void deleteAccountShouldBeReturnFalseBecauseArebeTestingIfAccountExistAfterDeleted() {
        account.deleteAccount("101010");
        boolean status = TestingIfExistAccount.testIfExistAccount("101010");
        assertFalse(status);
    }
}