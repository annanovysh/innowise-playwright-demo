package org.example.novysh.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.novysh.config.Configuration;

public class MainPage  extends BasePage {
    private final Locator contactUsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact us"));

    public MainPage(Page page) {
        super(page);
    }

    public MainPage open() {
        page.navigate(Configuration.getBaseUrl());
        acceptCookiesOnScroll();

        return this;
    }

    public ContactUsPage openContactUs() {
        contactUsLink.click();
        return new ContactUsPage(page);
    }
}
