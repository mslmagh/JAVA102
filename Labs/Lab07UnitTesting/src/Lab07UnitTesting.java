import java.util.ArrayList;

import javax.naming.NameNotFoundException;
import javax.security.auth.login.AccountException;

public class Lab07UnitTesting {
    public static void main(String[] args) throws Exception {

    }
}

class Account {
    private double balance;
    private String accountNumber;

    public Account(String accountNumber, double balance) throws InsufficientFundsException{
        this.accountNumber = accountNumber;
        setBalance(balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) throws InsufficientFundsException {
        if (balance < 0)
            throw new InsufficientFundsException(balance);
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount < 0)
            throw new InvalidTransactionException(amount);
        setBalance(balance + amount);
    }

    public void withdraw(double amount) {
        if (amount < 0)
            throw new InvalidTransactionException(amount);
        if (amount > balance)
            throw new InsufficientFundsException(balance, amount);
        setBalance(balance - amount);
    }

    @Override
    public String toString() {
        return "Account: " + accountNumber + ", Balance: " + balance;
    }
}

class Customer {
    private String name;
    private ArrayList<Account> accounts;

    public Customer(String name) {
        this.name = name;
        accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Account getAccounts(String accountNumber) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber))
                return account;
        }
        throw new AccountNotFoundException(this, accountNumber);
    }

    public void addAccount(Account account) {
        try {
            getAccounts(account.getAccountNumber());
            throw new AccountAlreadyExistException(this, account);
        } catch (AccountNotFoundException e) {
            accounts.add(account);
        } finally {
            System.out.println(this);
        }
        System.out.println("Added account: " + account);
    }

    public void removeAccount(Account account) throws AccountNotFoundException {
        getAccounts(account.getAccountNumber());
        accounts.remove(account);
    }

    public void transfer(String fromAccount, Account toAccount, double amount) throws InvalidTransactionException {
        toAccount.deposit(amount);

        try {
            Account from = getAccounts(fromAccount);
            from.withdraw(amount);
            toAccount.deposit(amount);
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        } catch (InvalidTransactionException e) {
            throw new InvalidTransactionException(e, "cannot transfer funds from " + fromAccount + " to account " + toAccount.getAccountNumber()   );
        }

    }
    @Override
    public String toString() {
        String message = this.getName() + "\n\t";
        for (Account account : accounts) {
            message += account + "\n\t";
        }
        return message; 
    }
}

class AccountAlreadyExistException extends RuntimeException {
    private String accountNumber;
    private Customer customer;

    public AccountAlreadyExistException(Customer customer, Account account) {
        super(customer.getName() + "already has an account with number " + account.getAccountNumber());
        this.accountNumber = accountNumber;
        this.customer = customer;
    }
}

class AccountNotFoundException extends RuntimeException {
    private Account account;
    private Customer customer;

    public AccountNotFoundException(Customer customer, String accountNumber) {
        super(customer.getName() + "doesn't have any acoount with number " + accountNumber);
        this.account = account;
        this.customer = customer;
    }

}

class InsufficientFundsException extends RuntimeException {
    private double balance, amount;

    public InsufficientFundsException(Double balance) {
        super("InsufficientFundsException: Invalid balance... " + balance);
        this.balance = balance;
    }

    public InsufficientFundsException(Double balance, Double amount) {
        super("InsufficientFundsException: Required amount is " + amount + ", but only " + balance + "remaining.");
        this.balance = balance;
        this.amount = amount;
    }

}

class InvalidTransactionException extends RuntimeException {
    private double amount;

    public InvalidTransactionException(double amount) {
        super("Invalid amount: " + amount);
    }

    public InvalidTransactionException(InvalidTransactionException e, String message) {
        super(e.getMessage() + "\n\t" + message);
    }
}