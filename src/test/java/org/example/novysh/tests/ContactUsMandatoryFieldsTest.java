package org.example.novysh.tests;

import io.qameta.allure.*;
import org.example.novysh.page.ContactUsPage;
import org.example.novysh.page.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic("Innowise Website UI Tests")
@Feature("Contact Us Form")
public class ContactUsMandatoryFieldsTest extends BaseTest {
    private ContactUsPage contactUsPage;

    @BeforeEach
    @Step("Setup: Open home page and navigate to 'Contact Us' form")
    void setUpContactUsPage() {
        MainPage mainPage = new MainPage(page);

        contactUsPage = mainPage.open()
                .openContactUs();
    }

    @Test
    @Story("Mandatory Fields Validation")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Verify Name field validation on form submission")
    @Description("Tests that an error message appears when submitting the form without providing a Name")
    void testContactUsFormValidationNameRequired() {
        contactUsPage.fillForm(null, "Innowise", "+1234567890", "test@innowise.com", "Project details")
                .clickSend();

        assertThat(contactUsPage.getNameError()).hasText("Please fill out this field");
    }

    @Test
    @Story("Mandatory Fields Validation")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Verify Phone field validation on form submission")
    @Description("Tests that an error message appears when submitting the form without providing a Phone number")
    void testContactUsFormValidationPhoneRequired() {
        contactUsPage.fillForm("John Doe", "Innowise", null, "test@innowise.com", "Project details")
                .clickSend();

        assertThat(contactUsPage.getPhoneError()).hasText("Please fill out this field");
    }
}