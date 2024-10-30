public class BankingController
{
    public BankingClass bc_obj;
    public BankingController()
    {
        this.bc_obj=new BankingClass();
    }
    boolean validate(String account_no,double amount){
        return account_no.length() < 16 && amount < 50000;
    }

    public void deposit(BankingFronted bf_object){
        if(validate(bf_object.account_no, bf_object.amount)){
            bc_obj.deposit(bf_object);
        }
        else{
            System.out.println("Invalid Details");
        }
    }
    public void withdraw(BankingFronted bf_object){
        if(validate(bf_object.account_no, bf_object.amount)){
            bc_obj.withdraw(bf_object);
        }
        else{
            System.out.println("Invalid Details");
        }
    }

    public double getBalance(BankingFronted bf_object){
        if(validate(bf_object.account_no, bf_object.amount)){
            return bc_obj.getBalance(bf_object);
        }
        else{
            System.out.println("Invalid Details");
        }
        return -1;
    }
}
