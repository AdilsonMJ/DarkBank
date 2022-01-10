import BD.BDBank;

public class Program {
    public static void main(String[] args) {

    Account account = new Account();

    //account.insertDates("Saving", "232322", "Adilson", 28);

    account.insertDates("Saving", "232324", "Will Rei do gado", 24);


    account.deposit(300);

    account.closerConnection();

    }
}
