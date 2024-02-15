package cat.copernic.backend.field_validation_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.Internet;

import cat.copernic.backend.services.utils.validators.user_profiles.ValidateCompanyData;

public class TestCompanyFieldValidators {

    private ValidateCompanyData validateCompanyData = mock(ValidateCompanyData.class);
    
    // Method in charged of generating a list of URLs
    private List<String> generateURLs(int quantity) {
        Internet fakerInternet = new Faker().internet();
        List<String> urls = new ArrayList<String>();

        for (int i = 0; i < quantity; i++) {
            urls.add(
                "http://" + fakerInternet.domainName()
            );

            urls.add(
                "https://" + fakerInternet.domainName()
            );
        }
        return urls;
    }

    @Test
    public void testCompanyWebPageURL() {
        List<String> webPageURLs = generateURLs(20);

        for (String url : webPageURLs) {
            when(this.validateCompanyData.validateWebPageURL(url)).thenReturn(true);
            boolean validationResult = this.validateCompanyData.validateWebPageURL(url);
            assertEquals(true, validationResult);
        }
    }

    // Simple method for generating a list of CIFs
    private List<String> generateCIFs(int quantity) {
        Faker faker = new Faker();
        List<String> cifs = new ArrayList<String>();

        for (int i = 0; i < quantity; i++) {
            
            char firstLetter = faker.options().option('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'N', 'P', 'Q', 'R', 'S', 'U', 'V', 'W');
            String numbers = faker.numerify("#######");
            char controllLetter = calculateControlLetter(firstLetter, numbers.toString());

            cifs.add(
                firstLetter + numbers + String.valueOf(controllLetter).toUpperCase()
            );

        }

        return cifs;
    }

    // Method for calculating the control letter of the CIF
    private static char calculateControlLetter(char firstLetter, String numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length(); i++) {
            int digit = Character.getNumericValue(numbers.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        sum %= 10;
        sum = 10 - sum;
        if (sum == 10) {
            sum = 0;
        }

        // Si la primera letra es una K, P, Q, S, U, V, W, o N, se suma 64 al valor
        if (firstLetter == 'K' || firstLetter == 'P' || firstLetter == 'Q' || firstLetter == 'S' || firstLetter == 'U' || firstLetter == 'V' || firstLetter == 'W' || firstLetter == 'N') {
            sum += 64;
        }

        return (char) ('0' + sum);
    }

    @Test
    public void testCompanyCIF() {
        List<String> cifs = generateCIFs(20);

        for (String cif : cifs) {
            when(this.validateCompanyData.validateCIF(cif)).thenReturn(true);
            boolean validationResult = this.validateCompanyData.validateCIF(cif);
            assertEquals(true, validationResult);
        }
    }

}