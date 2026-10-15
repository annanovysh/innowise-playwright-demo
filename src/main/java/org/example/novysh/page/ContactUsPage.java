package org.example.novysh.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ContactUsPage extends BasePage {
    private final Locator nameInput = page.locator("#contact-name");
    private final Locator companyInput = page.locator("#contact-company");
    private final Locator phoneInput = page.locator("#contact-phone");
    private final Locator emailInput = page.locator("#contact-email");
    private final Locator descInput = page.locator("textarea#contact-message");

    private final Locator sendButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send"));

    private final Locator nameError = page.locator("//input[@id='contact-name']/following::span[1]");
    private final Locator companyError = page.locator("//input[@id='contact-company']/following::span[1]");
    private final Locator phoneError = page.locator("//input[@id='contact-phone']/following::span[1]");
    private final Locator emailError = page.locator("//input[@id='contact-email']/following::span[1]");

    public Locator getNameError() {
        return nameError;
    }

    public Locator getPhoneError() {
        return phoneError;
    }

    public ContactUsPage(Page page) {
        super(page);
    }
    public ContactUsPage fillForm(String name, String company, String phone, String email, String description) {
        fillOrClear(nameInput, name);
        fillOrClear(companyInput, company);
        fillOrClear(phoneInput, phone);
        fillOrClear(emailInput, email);
        fillOrClear(descInput, description);

        return this;
    }

    public ContactUsPage clickSend() {
        sendButton.click();

        return this;
    }
    private void fillOrClear(Locator locator, String value) {
        if (value != null) {
            locator.fill(value);
        }
    }
}
