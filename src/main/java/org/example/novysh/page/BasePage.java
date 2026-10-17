package org.example.novysh.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public abstract class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected BasePage() {
    }

    public void acceptCookiesOnScroll() {
        page.evaluate("window.scrollBy({ top: 600, behavior: 'smooth' })");
        Locator acceptBtn = page.locator("#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
        acceptBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        acceptBtn.click();
        acceptBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));

        page.evaluate("window.scrollTo(0, 0)");
    }

    public void acceptCookiesOnScrollMouse() {
        page.mouse().wheel(0, 400);
        page.locator("#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll").click();
        page.mouse().wheel(0, -400);
    }
}
