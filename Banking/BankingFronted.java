import java.util.Scanner;

public class BankingFronted {

    String account_no;
    double amount;

    public void setAccountNo(String account_no) {
        this.account_no = account_no;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountNo() {
        return this.account_no;
    }

    public double getAmount() {
        return this.amount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Banking Application!");
        BankingFronted bf_obj = new BankingFronted();
        BankingController controller = new BankingController();

        while (true) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Account Number: ");
                    bf_obj.setAccountNo(scanner.next());
                    System.out.println("Enter Deposit Amount: ");
                    bf_obj.setAmount(scanner.nextDouble());
                    controller.deposit(bf_obj);
                    break;
                case 2:
                    System.out.println("Enter Account Number: ");
                    bf_obj.setAccountNo(scanner.next());
                    System.out.println("Enter Withdrawal Amount: ");
                    bf_obj.setAmount(scanner.nextDouble());
                    controller.withdraw(bf_obj);
                    break;
                case 3:
                    System.out.println("Enter Account Number: ");
                    bf_obj.setAccountNo(scanner.next());
                    double balance = controller.getBalance(bf_obj);
                    System.out.println("Current Balance: " + balance);
                    break;
                case 4:
                    System.out.println("Thank you for using the Banking Application!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
