package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions(){
        Owner owner=new Owner(1L,"Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");
        assertAll("Properties Test",
                ()->{
                    assertAll("Person Properties",
                            ()-> assertEquals("Joe",owner.getFirstName(), "First Name Failed"),
                            ()-> assertEquals("Buck",owner.getLastName())
                    );
                },
                ()->{
                    assertAll("Owner Properties",
                            ()-> assertEquals("Key West",owner.getCity(),"City didn't match"),
                            ()-> assertEquals("1231231234",owner.getTelephone())
                    );
                });

        //hamcrest
        assertThat(owner.getCity(),is("Key West"));
    }

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"Spring","Framework","Guru"})
    void testValueSource(String value) {
        System.out.println(value);
    }

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void testEnumSource(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("Csv Input Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource({
            "FL,1,1",
            "OH,2,2",
            "MI,2,1"
        })
    void testCsvInput(String stateName,int val1,int val2) {
        System.out.println(stateName+"-->"+val1+":"+val2);
    }

    @DisplayName("Csv File Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvFileSource(resources = "/input.csv",numLinesToSkip = 1)
    void testCsvInputFile(String stateName,int val1,int val2) {
        System.out.println(stateName+"-->"+val1+":"+val2);
    }

    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgs")
    void testMethodProvider(String stateName,int val1,int val2) {
        System.out.println(stateName+"-->"+val1+":"+val2);
    }

    static Stream<Arguments> getArgs(){
        return  Stream.of(
                Arguments.of("FL",4,1),
                Arguments.of("OH",5,2),
                Arguments.of("MI",6,1));
    }

    @DisplayName("Custom Provider Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void testCustomProvider(String stateName,int val1,int val2) {
        System.out.println(stateName+"-->"+val1+":"+val2);
    }
}