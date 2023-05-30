package ru.praktikum.selenium.pageobject;

import java.time.Duration;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class HomePage {
    WebDriver webDriver;


    public HomePage(WebDriver webDriver) {

        this.webDriver = webDriver;
    }
    // Кнопка "Заказать в хэдере страницы
    private By orderButtonInHeader = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button[text()='Заказать']");

    //  Кнопка "Заказать " в нижней части страницы
    private By orderButtonInLowerPart = By.xpath(".//div[@class='Home_ThirdPart__LSTEE']//button[text()='Заказать']");

    //   Заголовок "Вопросы о важном"
    private By questionsSectionHead = By.xpath(".//div[text()='Вопросы о важном']");

    //   Локатор всех вопросов
    private By questions = By.className("accordion__heading");

    //   Локатор видимого ответа
    private By visibleAnswer = By.xpath(".//div[contains(@class, 'accordion__panel') and not(@hidden)]");

    //    Локатор кнопки принятия кук
    private By acceptCookiesButton = By.xpath(".//button[text()='да все привыкли']");

    //    Перемотать страницу до вопросов о важном
    public void scrollToQuestionsSection(){
        WebElement element = webDriver.findElement(questionsSectionHead);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
    }
    // Нажать на определенный вопрос (по номеру)
    public void clickQuestion(int number){
        List<WebElement> listOfQuestions = webDriver.findElements(questions);
        listOfQuestions.get(number - 1).click();
    }
    // Проверить текст ответа
    public void checkAnswer(String answerText) {
        new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(visibleAnswer));
        String actualAnswer = webDriver.findElement(visibleAnswer).getText();
        MatcherAssert.assertThat(actualAnswer, is(answerText));
    }
    // Нажать кнопку заказа в хедере
    public void clickOrderButton(){
        webDriver.findElement(orderButtonInHeader).click();
    }

    //  Нажать кнопку заказа в нижней части
    public void clickOrderButtonInBottom(){
        webDriver.findElement(orderButtonInLowerPart).click();
    }

    //    Принять куки
    public void acceptCookies(){
        webDriver.findElement(acceptCookiesButton).click();
    }

}
