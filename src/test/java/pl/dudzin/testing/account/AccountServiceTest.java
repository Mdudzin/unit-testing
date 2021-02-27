package pl.dudzin.testing.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @Test
    public void getAllActiveAccounts() {
        //given
        List<Account> accounts = prepareAccountData();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        when(accountRepository.getAllAccounts()).thenReturn(accounts);
        //when
        List<Account> accountList = accountService.getAllActiveAccounts();
        //then
        assertThat(accountList).hasSize(2);
    }

    @Test
    public void getNoActiveAccounts() {
        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        when(accountRepository.getAllAccounts()).thenReturn(Collections.emptyList());
        //when
        List<Account> accountList = accountService.getAllActiveAccounts();
        //then
        assertThat(accountList).hasSize(0);
    }

    private List<Account> prepareAccountData() {
        Address address1 = new Address("Kwiatowa", "33/5");
        Address address2 = new Address("Piekarnicza", "12b");
        Account account1 = new Account(address1);
        Account account2 = new Account();
        Account account3 = new Account(address2);
        return Arrays.asList(account1, account2, account3);
    }
}