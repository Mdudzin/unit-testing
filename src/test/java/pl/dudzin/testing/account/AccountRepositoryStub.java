package pl.dudzin.testing.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository {

    @Override
    public List<Account> getAllAccounts() {
        Address address1 = new Address("Kwiatowa", "33/5");
        Address address2 = new Address("Piekarnicza", "12b");

        Account account1 = new Account(address1);
        Account account2 = new Account();
        Account account3 = new Account(address2);

        return Arrays.asList(account1, account2, account3);
    }
}