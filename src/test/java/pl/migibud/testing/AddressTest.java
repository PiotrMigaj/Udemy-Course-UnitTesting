package pl.migibud.testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddressTest {

    @ParameterizedTest
    @CsvSource({"Fabryczna, 10","Armii Krajowej, 57/11","'Romka, Tomka, Atomka', 40"})
    void givenAddressesShouldNotBeEmptyAndHaveProperNames(String streets,String number){
        assertThat(streets,notNullValue());
        assertThat(streets, containsString("a"));
        assertThat(number,notNullValue());
        assertThat(number.length(),lessThan(8));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void addressesFromFileShouldNotBeEmptyAndHaveProperNames(String streets,String number){
        assertThat(streets,notNullValue());
        assertThat(streets, containsString("a"));
        assertThat(number,notNullValue());
        assertThat(number.length(),lessThan(8));
    }
}
