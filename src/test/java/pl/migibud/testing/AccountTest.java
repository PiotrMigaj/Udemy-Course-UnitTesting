package pl.migibud.testing;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;


class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation(){
        //given
        //when
        Account newAccount = new Account();
        //then
        assertFalse(newAccount.isActive(), "Check if new account is not active");
        //assertThat(newAccount.isActive(),equalTo(false));
        //assertThat(newAccount.isActive(),is(false));
        //assertThat(newAccount.isActive()).isFalse();
    }

    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();
        //when
        newAccount.activate();
        //then
        assertTrue(newAccount.isActive());
        //assertThat(newAccount.isActive(),equalTo(true));
        //assertThat(newAccount.isActive()).isTrue();
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet(){
        //Given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        //assertThat(address,nullValue());
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
        //assertNotNull(address1);
        //assertThat(address1,is(notNullValue()));
        //assertThat(address1).isNotNull();
    }

    @RepeatedTest(5)
    void newAccountWithNotNullAddressShouldBeActive(){

        //given
        Address address = new Address("Pułaska","46/6");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address!=null,()->{
            assertTrue(account.isActive());
        });

    }

    @Test
    void invalidEmailShouldThrowException(){
        //given
        Account account = new Account();
        //when
        //then
        assertThrows(IllegalArgumentException.class,()->account.setEmail("wrongEmail"));
    }
    @Test
    void validEmailShouldBeSet(){
        //given
        Account account = new Account();
        //when
        account.setEmail("pmigaj@gmail.com");
        //then
        assertThat(account.getEmail(),is("pmigaj@gmail.com"));
    }

}
