
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import classes.User;

public class Server extends UnicastRemoteObject implements Operations {
    
    private static int PORT = 3032;

    public static ArrayList<User> USERS = new ArrayList<User>();

    public static Scanner scanner = new Scanner(System.in);

    public Server() throws RemoteException { }
    public static void main(String[] args) throws Exception {
        (new Server()).startServer();
    }

    public void startServer() {
        try {
            String dirIp = (InetAddress.getLocalHost()).toString();
            System.out.println("Listening in port " + dirIp + ":" + PORT);
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.bind("server", (Operations) this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createAccount(String name, int accountNumber) throws RemoteException {
        USERS.add(new User(name, accountNumber));
        for (User user : USERS) {
            System.out.println(user);
        }
    }

    @Override
    public void deposit(int accountNumber, double amount) throws RemoteException {
        
        double prevBalance = 0;
        double finalBalance = 0;
        boolean found = false;
        
        for (User user : USERS) {
            prevBalance = user.getBalance();
            finalBalance = prevBalance + amount;
            found = true;
            user.setBalance(user.getBalance()+amount);
        }

        if (!found) {
            System.out.println("\nInvalid account number :(");
            return;
        }

        System.out.println("Successful transaction!");
        System.out.println("\nInitial balance: " + "$" + prevBalance);
        System.out.println("\nFinal balance: " + "$" + finalBalance);
    }

    @Override
    public void withdrawal(int accountNumber, double amount) throws RemoteException {
        double prevBalance = 0;
        double finalBalance = 0;
        boolean found = false;
        
        for (User user : USERS) {
            prevBalance = user.getBalance();
            finalBalance = prevBalance - amount;
            found = true;
            user.setBalance(user.getBalance()+amount);
        }

        if (!found) {
            System.out.println("\nInvalid account number :(");
            return;
        }

        System.out.println("Successful transaction!");
        System.out.println("\nInitial balance: " + "$" + prevBalance);
        System.out.println("\nFinal balance: " + "$" + finalBalance);
    }

}       
   