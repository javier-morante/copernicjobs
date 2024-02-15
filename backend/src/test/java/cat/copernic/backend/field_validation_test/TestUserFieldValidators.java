package cat.copernic.backend.field_validation_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import cat.copernic.backend.services.utils.validators.user_profiles.ValidateStudentData;
import cat.copernic.backend.services.utils.validators.user_profiles.ValidateUserData;

@Service
public class TestUserFieldValidators {

    private ValidateUserData validateUserData = mock(ValidateUserData.class);

    private ValidateStudentData validateStudentData = mock(ValidateStudentData.class);

    // Generate a list of random phone numbers
    private List<String> generatePhones(int quantity) {
        Random rnd = new Random();
        List<String> phones = new ArrayList<String>();

        for (int i = 0; i < quantity; i++) {
            phones.add(
                String.valueOf(rnd.nextLong(1000000000l, 9999999999l))
            );
        }

        return phones;
    }

    @Test
    public void testPhonesValidation() {
        List<String> phones = generatePhones(200);

        for (String phone : phones) {
            when(this.validateUserData.validatePhone(phone)).thenReturn(true);
            boolean validationResult = this.validateUserData.validatePhone(phone);
            assertEquals(true, validationResult);
        }
    }

    // Generate a list of random user emails
    private List<String> generateEmails(int quantity) {
        Faker faker = new Faker();
        List<String> emails = new ArrayList<String>();
        
        for (int i = 0; i < quantity; i++) {
            emails.add(
                faker.internet().emailAddress()
            );
        }

        return emails;
    }

    @Test
    public void testEmailValidation() {
        List<String> emails = generateEmails(200);

        for (String email : emails) {
            when(this.validateUserData.validateEmail(email)).thenReturn(true);
            boolean validationResult = this.validateUserData.validateEmail(email);
            assertEquals(true, validationResult);
        }
    }


    private List<String> generteDNIs(int quantity) {
        Faker faker = new Faker();
        List<String> dnis = new ArrayList<String>();

        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";

        for (int i = 0; i < quantity; i++) {
            String dni = faker.numerify("########");

            int dniNumber = Integer.parseInt(dni);
            int index = dniNumber % 23;

            dnis.add(
                dni + letters.charAt(index)
            );
        }

        return dnis;
    }

    @Test
    public void testDNIValidation() {
        List<String> dnis = generteDNIs(200);

        for (String dni : dnis) {
            when(this.validateStudentData.validateDNI(dni)).thenReturn(true);
            boolean validationResult = this.validateStudentData.validateDNI(dni);
            assertEquals(true, validationResult);
        }
    }
}