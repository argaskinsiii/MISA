/*
MISA BANKING
COSC 113 FINAL PROJECT
SAVANNAH JACKSON
ANTHONY GASKINS III
MEGAN SHIRLEY
 */

import java.util.Scanner;
import java.math.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        boolean logged = false;
        int response = 0;

        Checkings checking = new Checkings("Savannah", 427852, 300.00, 50);
        checking.withdraw(100);
        checking.giveBalance();

        Credit credit = new Credit("Megan", 102, 0);

        while(logged == false) {
            logInPage();
            response = sc.nextInt();
            sc.nextLine();
            if (response == 1) {
                logInMethod();
                String username = sc.nextLine();

                if (checking != null && username.equalsIgnoreCase(checking.getName())) {
                    logged = true;
                    System.out.println("Login Successful");
                } else if (username.equalsIgnoreCase("user")) {
                    logged = true;
                    System.out.println("Login successful");
                } else {
                    LoginRepeat();
                    logInPage();
                    response = sc.nextInt();
                }
            } else if (response == 2) {
                newAccount();

            }
        }



        while (logged != false) {
            homePage();
            int loopResponse = sc.nextInt();
            if (loopResponse == 1) {
                System.out.print("Enter amount to withraw: ");
                double amt = sc.nextDouble();
                checking.withdraw(amt);
            }else if (loopResponse == 2){
                System.out.print("Enter amount to deposit: ");
                double amt = sc.nextDouble();
                checking.deposit(amt);
            }else if (loopResponse == 3){
                System.out.println("Coming Soon");
            }else if (loopResponse == 4){
                System.out.println("Coming Soon");
            }else if (loopResponse == 5) {
                credit.viewCreditStatus();
                creditRepeat();
                int x = sc.nextInt();
                while(x != 0) {
                    if(x == 1) {
                        System.out.println("Enter Withdraw Amount: ");
                        double y = sc.nextDouble();
                        credit.withdraw(y);
                    }else if(x == 2) {
                        System.out.println("Enter Deposit Amount: ");
                        double y = sc.nextDouble();
                        credit.deposit(y);
                    }else {
                        System.out.println("Invalid Input");
                    }
                }
            }else if (loopResponse == 6) {
                savings.applyInterest();
            }else if (loopResponse == 7) {
                checking.giveBalance();
            }else if (loopResponse == 0){
                System.out.println("Logging out...");
                logged = false;
            }else {
                System.out.println("Invalid Input");

        }



    }

    public static void logInMethod() {
        System.out.println("Please Enter Your Username: ");
    }
    public static void creditRepeat() {
        System.out.println("Would you like to: ");
        System.out.println("1: Withdraw");
        System.out.println("2: Deposit");
        System.out.println("0: Go Back");
    }

    public static void logInPage() {
        System.out.println("MISA BANKING Greets you.");
        System.out.println("1: Log In to An Exisiting Account");
        System.out.println("2: Create a New Account");
    }
    public static void LoginRepeat() {
        System.out.println("Incorrect Login, Please try again.");
    }
    public static void homePage() {
        System.out.println("Welcome to MISA BANKING -- What would you like to do?");
        System.out.println("");

        System.out.println("1: Make a Withdrawal");
        System.out.println("2: Make a Deposit");
        System.out.println("3: Check/Pay off your Fees");
        System.out.println("4: Check Out our Rewards Program");
        System.out.println("5: Manage your Credit Account");
        System.out.println("6: View your Savings Account");
        System.out.println("7: View your Account Details");
        System.out.println("0: Logout");

        System.out.println("");
        System.out.println("Enter your choice: ");
    }
    public static Checkings newAccount() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        System.out.print("Enter starting balance: ");
        double balance = sc.nextDouble();
        System.out.print("Enter overdraft limit: ");
        double overdraft = sc.nextDouble();
        sc.nextLine();

        return new Checkings(name, id, balance, overdraft);
    }}

    //-------------Interface---------------
    interface Transaction {
        public static int Withdraw;
        public static int Deposit;
    }

    //----------Abstract Class--------------
    abstract class Account {
            private String name;
            private int id;
            private double balance;

            protected Account(String name, int id, double balance) {
                this.name = name;
                this.id = id;
                this.balance = balance;
            }

            public void setName(String val){
                name = val;
            }
            public void setId(int val){
                id = val;
            }
            public void setBalance(double val){
                balance = val;
            }
            public String getName() {
                return name;
            }
            public int getId() {
                return id;
            }
            public double getBalance() {
                return balance;
            }

            public double giveBalance() {
                System.out.println("Name: " + this.name + " ID: " + this.id + " Balance: $" + this.balance);
                return this.balance;
            }

            public void withdraw(double amount) {
                if (this.balance >= amount) {
                    this.balance -= amount;
                    System.out.println("Withdrawal successful. Remaining balance: $" + this.balance);
                } else {
                    System.out.println("Insufficient balance to withdraw $" + amount);
                }
            }

            public void deposit(double amount) {
                if (amount > 0) {
                    this.balance += amount;
                    System.out.println("Deposit successful. New balance: $" + this.balance);
                } else {
                    System.out.println("Invalid deposit amount.");
                }
            }
    }


    //void rewardsProgram() {}


    //abstract void creditScore();

//---------------Checking Account----------------
class Checkings extends Account {
    private double overdraftLimit;

    public Checkings(String name, int id, double balance, double overdraftLimit) {
        super(name, id, balance); // Call the Account constructor
        this.overdraftLimit = overdraftLimit;
    }
}


//----------------Savings Account
class Savings extends Account {
    private double interestRate;

    public Savings(String name, int id, double balance, double interestRate) {
        super(name, id, balance); // Call the Account constructor
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = this.giveBalance() * (interestRate / 100);
        this.deposit(interest);
        System.out.println("Interest of $" + interest + " applied. New balance: $" + this.giveBalance());
    }
}


class Credit extends Account {
    private double creditLimit;

    public Credit(String name, int id, double balance) {
        super(name, id, balance);
        this.creditLimit = determineCreditLimit(); // Call the method
    }

    private double determineCreditLimit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your credit score: ");
        int score = scanner.nextInt();

        if (score < 640) {
            return 200;
        } else if (score <= 690) {
            return 500;
        } else if (score <= 720) {
            return 800;
        } else {
            return 1200;
        }
    }
}


