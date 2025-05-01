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

        Checkings checking = new Checkings("Savannah", 427852, 300.00, 50);
        checking.withdraw(100);
        checking.giveBalance();

        Credit credit = new Credit("Megan", 102, 0);

        while(logged == false) {
            logInPage();
            int response = sc.nextInt();
            sc.nextLine();
            if (response == 1) {
                logInMethod();
                String username = sc.nextLine();

                if (username.equalsIgnoreCase("user")) {
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
                credit.giveBalance();
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

        System.out.println("1: To make a Withdraw");
        System.out.println("2: To make a Deposit, Press 2");
        System.out.println("3: To Check/Pay off your Fees, Press 3");
        System.out.println("4: To Check Out our Rewards Program, Press 4");
        System.out.println("5: To Check your Credit");

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

            public double giveBalance() {
                System.out.println(this.name + " ID: " + this.id + " Balance: $" + this.balance);
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


