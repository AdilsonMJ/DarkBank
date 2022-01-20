package BD;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestingIfExistAccountTest {

    @Test
    void testExistAccountSucessCase() {
        assertEquals(true, TestingIfExistAccount.testIfExistAccount("232324"));
    }

    @Test
    void testNotExistAccountFailCase() {
        assertEquals(false, TestingIfExistAccount.testIfExistAccount("23232400"));
    }
}