/*

There are 10 valid account numbers starting from 15908000 to 15908090.
For each of the account one correct pin is there. Starting from 0000(for account 15908000) to 9009(15908090) respectively.
User will get 3 chances to enter the correct account number.
After 3 incorrect pin attempt user needs to enter account number again.

*/
import java.util.Scanner;
public class ProBank {
    public static void main(String args[])
    {
        Scanner sc= new Scanner(System.in);
        int acc[] = account_detils();
        account_num_check(acc);
    }
    public static int[] account_detils(){
        int acc[] = new int[10];
        int init=15908000;
        for(int i=0; i<10; i++){
            acc[i]=init;
            init+=10;
        }

        return(acc);
    }
    public static void account_num_check(int[] acc){
        Scanner sc= new Scanner(System.in);
        int userChoice;
        boolean quit=false;

        Virtual obj = new Virtual();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your account number: ");
        int input_accNumber = input.nextInt();
        int i, flag=0;
        for(i=0; i<10; i++){
            if(input_accNumber==acc[i]){
                flag=1;
                break;
            }
        }

        if(flag==1){
            System.out.println("Success");

            int pin = 1000*i + i;

            int c = 0;
            //User will get 3 chances to enter the correct account number.
            while(c<3){
                System.out.println("Please enter your pin: ");
                int entered_pin = input.nextInt();
                if(pin == entered_pin){
                    System.out.println("Welcome.\n");
                    do {
                        System.out.println("Your choice, 1 to Deposit Money:");
                        System.out.println("Your choice, 2 to Withdraw Money:");
                        System.out.println("Your choice, 3 to Check Balance:");
                        System.out.println("Your choice, 0 to quit:");
                        userChoice = sc.nextInt();
                        switch (userChoice)
                        {
                            case 1:
                                obj.Deposit();
                                break;
                            case 2:
                                obj.Withdraw();
                                break;
                            case 3:
                                obj.Check();
                                break;
                            case 0:
                                quit=true;
                                break;
                            default:
                                System.out.println("Wrong choice");
                        }
                        if (userChoice == 0)
                            quit = true;
                    }while(!quit);
                    System.out.println("Bye");
                    break;
                }
                else{
                    System.out.println("Incorrect pin.");
                }
                c++;
            }
            //After 3 incorrect pin attempt user needs to enter account number again.
            if(c==3){
                account_num_check(acc);
            }
        }
        else{
            System.out.println("Invalid Account Number. \nPlease verify your account number.\n");
            account_num_check(acc);
        }
    }

}
abstract class Person extends ProBank{
}

class Virtual extends Person {
    Scanner sc = new Scanner(System.in);
    float balance = 0f;
    float amount = 0;

    public void Deposit() {

        System.out.println("AMOUNT TO DEPOSIT:");
        amount = sc.nextFloat();
        if (amount <= 0) {
            System.out.println("CANT DEPOSIT NON POSITIVE AMOUNT");

        } else {
            balance += amount;
            System.out.println("$" + amount + "has been deposited");
        }
    }

    public void Withdraw() {
        System.out.println("AMOUNT TO WITHDRAW:");
        amount = sc.nextFloat();
        if (amount <= balance) {
            balance -= amount;
            System.out.println("$" + amount + "has been withdrawn");


        } else {
            System.out.println("CANT WITHDRAW");
        }
    }

    public void Check() {
        System.out.println("YOUR BALANCE: $" + balance);
    }
}
