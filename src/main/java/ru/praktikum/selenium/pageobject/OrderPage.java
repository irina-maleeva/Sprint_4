package ru.praktikum.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.selenium.data.TestData;

import java.time.Duration;
import java.util.Random;

public class OrderPage {
    WebDriver webDriver;
    int random = new Random().nextInt(6);

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    // Заголовок формы "Для кого самокат"
    private By formHeading = By.xpath(".//div[text()='Для кого самокат']");

    //  Поле ввода имени
    private By nameInputField = By.xpath(".//input[@placeholder='* Имя']");

    //  Поле ввода фамилии
    private By familyNameInputField = By.xpath(".//input[@placeholder='* Фамилия']");

    //  Поле ввода адреса
    private By addressInputField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //  Поле выбора станции метро
    private By metroStationInputField = By.xpath(".//input[@placeholder='* Станция метро']");

    //  Поле ввода телефона
    private By phoneInputField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //  Кнопка "Далее"
    private By submitButton = By.xpath(".//button[text()= 'Далее']");

    //  Заголовок формы "Про аренду"
    private By detailsFormHeading = By.xpath(".//div[text()='Про аренду']");

    //  Поле ввода даты
    private By dateInputField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //  Поля выпадающего списка в поле выбора срока аренды
    private By rentDurationDropdownOptions = By.xpath(".//div[@class='Dropdown-option']");

    //  Полк выбора срока аренды
    private By rentDurationInputField = By.xpath(".//div[@class='Dropdown-root']");

    //  Чекбокс "черный жемчуг"
    private By blackColorCheckbox = By.id("black");

    //  Чекбокс "серая безысходность"
    private By greyColorCheckbox = By.id("grey");

    //  Поле ввода комментария
    private By commentInputField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //  Кнопка "Заказать"
    private By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    //  Сообщение "Заказ оформлен"
    private By orderCreatedMessage = By.xpath(".//div[text()='Заказ оформлен']");

    //  Кнопка "Да"
    private By yesButton = By.xpath(".//button[text()='Да']");

    //  Дождаться загрузки страницы
    public void waitForPageLoad(){
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(formHeading));
    }
    // Заполнить поля первой формы
    public void fillFirstFormFields(TestData testData) {
        webDriver.findElement(nameInputField).sendKeys(testData.name);
        webDriver.findElement(familyNameInputField).sendKeys(testData.familyName);
        webDriver.findElement(addressInputField).sendKeys(testData.address);
        webDriver.findElement(metroStationInputField).sendKeys(testData.metro);
        webDriver.findElement(metroStationInputField).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        webDriver.findElement(phoneInputField).sendKeys(testData.phone);
    }
    //  Нажать кнопку "Далее"
    public void clickSubmitButton(){
        webDriver.findElement(submitButton).click();
    }

    //  Дождаться загрузки формы Про аренду
    public void waitForDetailsFormLoad(){
        new WebDriverWait(webDriver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(detailsFormHeading));
    }
    //  Выбрать дату
    public void chooseDate(String date){
        webDriver.findElement(dateInputField).sendKeys(date);
        webDriver.findElement(dateInputField).sendKeys(Keys.ENTER);
    }
    // Выбрать срок аренды
    public void chooseRentDuration(){
        webDriver.findElement(rentDurationInputField).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(rentDurationDropdownOptions));
        webDriver.findElements(rentDurationDropdownOptions).get(random).click();
    }
    //  Выбрать черный цвет
    public void chooseGreyColor(){
        webDriver.findElement(greyColorCheckbox).click();
    }
    //  Выбрать серый цвет
    public void chooseBlackColor(){
        webDriver.findElement(blackColorCheckbox).click();
    }
    //  Написать комментарий
    public void writeComment(String comment){
        webDriver.findElement(commentInputField).sendKeys(comment);
    }
    //  Нажать кнопку "Заказать"
    public void clickOrderButton(){
        webDriver.findElement(orderButton).click();
    }
    //  Дождаться подтверждения заказа
    public void waitConfirmation(){
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(orderCreatedMessage));
    }
    //  Получить текст заголовка подтверждения заказа
    public String getConfirmationHeadingText(){
        String text = webDriver.findElement(orderCreatedMessage).getText();
        return text;
    }
    // Нажать "Да"
    public void clickYesButton(){
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        webDriver.findElement(yesButton).click();
    }
}
