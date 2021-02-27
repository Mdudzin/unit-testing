package pl.dudzin.testing.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AccountServiceStubTest {

    @Test
    void getAllActiveAccounts() {
        //given
        AccountRepository accountRepositoryStub = new AccountRepositoryStub();
        AccountService accountService = new AccountService(accountRepositoryStub);
        //when
        List<Account> accountList = accountService.getAllActiveAccounts();
        //then
        assertThat(accountList).hasSize(2);
    }
}