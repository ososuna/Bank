
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import classes.User;

public class Client {
    

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;
        Operations server;
        String serverAddress = "localhost";
        int serverPort = 3032;

        try {
            Registry registry = LocateRegistry.getRegistry(serverAddress, serverPort);
            server = (Operations) (registry.lookup("server"));
            
            do {
                System.out.println("\nBANK");
                System.out.println("1. Register");
                System.out.println("2. Deposit");
                System.out.println("3. Withdrawal");
                System.out.println("4. Get out");
                System.out.print("Enter an option: ");
                option = scanner.nextInt();

                switch (option) {
        
                    case 1:
                        String name;
                        
                        System.out.println("\nCREATE ACCOUNT");
                        
                        scanner.nextLine();

                        System.out.print("Enter name: ");
                        name = scanner.nextLine();

                        server.createAccount(name, 1);

                        for (User u : Server.USERS) {
                            System.out.println(u);
                        }
                
                        break;
                    case 2:   

                        int accountNumberDeposit;
                        double amountDeposit;

                        System.out.println("\nDEPOSIT");
                        
                        scanner.nextLine();
                
                        System.out.print("Enter account number: ");
                        accountNumberDeposit = scanner.nextInt();
                        
                        System.out.print("Enter amount: ");
                        amountDeposit = scanner.nextDouble();
                        
                        server.deposit(accountNumberDeposit, amountDeposit);

                        break;
                    case 3:
                        
                        int accountNumberWithdrawal;
                        double amountWithdrawal;

                        System.out.println("\nWITHDRAWAL");
                        
                        scanner.nextLine();
                
                        System.out.print("Enter account number: ");
                        accountNumberWithdrawal = scanner.nextInt();
                        
                        System.out.print("Enter amount: ");
                        amountWithdrawal = scanner.nextDouble();
                        
                        server.withdrawal(accountNumberWithdrawal, amountWithdrawal);

                        break;
                    default:
                        System.out.println("\nSee you!\n");
                        scanner.close();
                        return;
                }
            } while (option>= 1 && option <= 3);


            
        } catch (Exception e) {
            e.printStackTrace();
        }        

    }

}