package classes;

import java.util.ArrayList;

public class Users {
    
    public static ArrayList<User> users = new ArrayList<User>();

    public static int getAccountNumber() {
        
        int accountNumber;
        
        if (users.isEmpty()) {
            return 1;
        }

        accountNumber = users.get(users.size()-1).getAccountNumber() + 1;

        return accountNumber;
    }

}
