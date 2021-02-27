package pl.dudzin.testing.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AccountTest {

    @Test
    public void newlyCreatedAccountShouldNotBeActive() {
        //given
        Account newAccount = new Account();
        //when
        boolean result = newAccount.isActive();
        //then
        assertFalse(result);
        assertThat(newAccount.isActive()).isFalse();
    }

    @Test
    public void activatedAccountShouldHaveActiveFlagSet() {
        //given
        Account newAccount = new Account();
        //when
        newAccount.activate();
        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive()).isTrue();
    }

    @Test
    public void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet() {
        //given
        Account account = new Account();
        //when
        Address address = account.getDefaultDeliveryAddress();
        //then
        assertThat(address).isNull();
    }

    @Test
    public void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {
        //given
        Address address = new Address("Krakowska", "67c");
        Account account = new Account();
        //when
        account.setDefaultDeliveryAddress(address);
        Address defaultAddress = account.getDefaultDeliveryAddress();
        //then
        assertNotNull(defaultAddress);
        assertThat(defaultAddress).isNotNull();
    }

    @RepeatedTest(1)
    public void newAccountWithNotNullAddressShouldBeActive() {
        //given
        Address address = new Address("Puławska", "46/6");
        //when
        Account account = new Account(address);
        //then
        assumingThat(address != null, () -> assertTrue(account.isActive()));
    }

    @Test
    public void invalidEmailShouldThrowException() {
        //given
        Account account = new Account();
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> account.setEmail("wrong email"));
    }

    @Test
    void validEmailShouldBeSet() {
        //given
        Account account = new Account();
        //when
        account.setEmail("kontakt@devfoundry.pl");
        //then
        assertThat(account.getEmail()).isEqualTo("kontakt@devfoundry.pl");
    }
}