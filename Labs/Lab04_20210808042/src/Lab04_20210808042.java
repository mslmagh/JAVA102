import javax.net.ssl.ExtendedSSLSession;

public class Lab04_20210808042 {

    public static void main(String[] args) throws Exception {
        // try {
        // getAccount(5);
        // System.out.println(" Received Account");
        // } catch (AccountException e) {
        // System.out.println(e.getMessage());
        // } finally {
        // System.out.println("Execute once");
        // }

        // Account acc = new Account();
        // acc.id = 7;
        // try {
        // setAccount(acc);

        // } catch (Exception e) {
        // System.out.println("Exception caught");
        // }

        // }

        // public static void getAccount(int id) throws AccountException {
        // if (id < 0) {
        // throw new AccountException("No such account with");
        // } else {
        // // ...
        // }
        // }

        // public static void setAccount(Account account) throws AccountException {
        // if (account.id != 5) {
        // throw new InvalidOperationException("Invalid");
        // } else {
        // // ...
        // }
        // }

        User acc = new User("qwer");
        acc.setId(6);
        acc.setActive(true);
        System.out.println(acc);
        acc.performOperation();
        try {
            acc.deactivate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            acc.performOperation();
        } catch (InvalidOperationException e) {
            e.getMessage();
        } finally {
            System.out.println("Anything");

        }
        acc.performOperation();
    }
}

class AccountException extends Exception {
    private User account;

    public AccountException(User account) {
        super("An Exception occured " + account);
        this.account = account;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}

class InvalidOperationException extends RuntimeException {
    private User account;

    public InvalidOperationException(User account) {
        super("Invalid operation with " + account);
        this.account = account;
    }

    @Override
    public String getMessage() {

        return "An exception has accured with " + account + super.getMessage();
    }
}

class InvalidArgumentException extends Exception {
    private String message;

    public InvalidArgumentException(String string) {
        super(string);
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return super.getMessage();
    }
}

class User {
    private String username;
    private boolean active;
    private int id;

    public User(String username) {
        this.username = username;
    }

    public void deactivate() throws AccountException {
        if (!active) {
            throw new AccountException(this);
        } else {
            setActive(false);
        }
    }

    public void performOperation() throws InvalidOperationException {
        if (!active) {
            throw new InvalidOperationException(this);
        }

        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("Account: \n\n Id: %d \n\t username: %s \n\t State: %b", id, username, active);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class BankAccount {
    private int id;
}

class InvalidAmountException extends Exception{
    private double amount;

    public InvalidAmountException(double amount) {
        super("Invalid amount " + amount);
        this.amount = amount;
    }

}

class InsefficentFundException extends Exception{
    
}
