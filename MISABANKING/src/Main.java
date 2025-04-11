/*
MISA BANKING
COSC 113 FINAL PROJECT
SAVANNAH JACKSON
ANTHONY GASKINS III
MEGAN SHIRLEY
 */
public class Main {
    public static void main(String[] args) {


    }
    interface Transaction {
        public static int Withdraw;
        public static int Deposit;
    }
    abstract class Account {
        private String name;
        private int iD;
        private static double balance;

        private Account(String name, int id, double balance){
            this.name = name;
            this.id = id;
            this.balance = balance;
        }
        public static double giveBalance() {
            System.out.println(name + " ID: "+ id + "Balance: $" + balance);
            return balance;
        }


        void rewardsProgram() {

        }

        //abstract void creditScore();

    }

    class Checkings extends Account {
        @Override
        public static int Withdraw(int balance) {
            if(balance != 0) {
                System.out.println();
            }
            while(balance != 0) {
                System.out.println();
            }
        }

    }

    class Savings extends Checkings {

    }

    class Credit extends Account {
private double creditLimit;
        private double determineCreditLimit() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your credit score: ");
            int score = scanner.nextInt();
            customer.setCreditScore(score); // Store score in customer

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

    }
}