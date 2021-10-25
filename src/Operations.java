
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Operations extends Remote {  
    
    public void createAccount(
        String name,
        int accountNumber
    ) throws RemoteException;    
    
    public void deposit(
        int accountNumber,
        double amount
    ) throws RemoteException;
    
    public void withdrawal(
        int accountNumber,
        double amount
    ) throws RemoteException;
    
}
