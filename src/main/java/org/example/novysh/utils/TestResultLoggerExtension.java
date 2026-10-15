package org.example.novysh.utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

public class TestResultLoggerExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        // Проверяем, завершился ли тест с ошибкой
        if (context.getExecutionException().isPresent()) {
            Object testInstance = context.getTestInstance().orElse(null);
            if (testInstance == null) return;

            Page page = findPageField(testInstance);

            // В этот момент @AfterEach ЕЩЕ НЕ ВЫЗВАН, страница 100% открыта!
            if (page != null && !page.isClosed()) {
                try {
                    byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
                    Allure.addAttachment("Screenshot on Failure", "image/png", new ByteArrayInputStream(screenshot), "png");
                    CustomLogger.info("Screenshot attached to Allure report successfully!");
                } catch (Exception e) {
                    CustomLogger.error("Failed to capture screenshot on test failure", e);
                }
            }
        }
    }

    private Page findPageField(Object testInstance) {
        Class<?> clazz = testInstance.getClass();
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (Page.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    try {
                        return (Page) field.get(testInstance);
                    } catch (IllegalAccessException e) {
                        CustomLogger.error("Cannot access Page field", e);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }
}
