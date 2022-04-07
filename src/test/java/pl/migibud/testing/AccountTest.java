package pl.migibud.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation(){
        //given
        //when
        Account newAccount = new Account();
        //then
        assertFalse(newAccount.isActive(), "Check if new account is not active");
    }

    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();
        //when
        newAccount.activate();
        //then
        assertTrue(newAccount.isActive());
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet(){
        //Given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
    }


    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){
        //given
        Address address = new Address("Krakowska","67c");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address address1 = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(address1);
    }

}
