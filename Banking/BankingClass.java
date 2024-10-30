public class BankingClass {
    private BankingJDBC jdbc;

    public BankingClass() {
        this.jdbc = new BankingJDBC();
    }

    public synchronized void deposit(BankingFronted bf_object) {
        jdbc.add(bf_object);
        jdbc.update(bf_object);
    }

    public synchronized void withdraw(BankingFronted bf_object) {
        jdbc.update(bf_object);
    }

    public double getBalance(BankingFronted bf_object) {
        return jdbc.getBal(bf_object);
    }
}
