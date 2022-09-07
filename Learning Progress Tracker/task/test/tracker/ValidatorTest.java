package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"John", "Foe", "Doe", "Chow", "O'Clark", "P'e-ter"})
    void isValidFirstName(String name) {
        assertTrue(Validator.isValidFirstName(name));
    }

    @DisplayName("Valid lastNames")
    @ParameterizedTest
    @MethodSource("namesFactory")
    void isValidLastName(String lastNames) {
        assertTrue(Validator.isValidLastName(lastNames));
    }

    @ParameterizedTest
    @MethodSource("argMailFactory")
    void isValidEmail(String email, boolean result) {
        assertEquals(result, Validator.isValidEmail(email));
    }


    @ParameterizedTest(name = "{index} => isAllValidCredentialsOf({0}, {1}, {2}) == {3}")
    @CsvSource({"John, Doe, johnDoe@gmail.com, true", "Bob, feynman, bobFeynman@outlook.com, true"})
    void isValidAllCredentials(String firstName, String lastName, String email, boolean expected) {
        assertEquals(expected, Validator.isValidAllCredentials(firstName, lastName, email));
    }

    static List<String> namesFactory() {
        return List.of("pa'rker", "zu-ker-burg", "b'om-b", "sus", "fist", "resist");
    }

    static List<Arguments> argMailFactory() {
        return List.of(arguments("ajmk47@vix.com", true), arguments("ajmk29@@..com", false));
    }
}