package ru.praktikum.selenium;

import org.junit.Test;
import ru.praktikum.selenium.data.TestData;
import ru.praktikum.selenium.pageobject.HomePage;
import ru.praktikum.selenium.pageobject.OrderPage;

public class OrderTest extends BaseTest {
    TestData testDataSet1 = new TestData("Ян", "Ли", "3-я улица Ямского поля, 6", "Черкизовская", "+79160000002", "15.06.2023",
            "Позвоните мне");
    TestData testDataSet2 = new TestData("Яна", "Рождественская", "Зубовский бульвар, 5", "Преображенская площадь", "+791600000027",
            "14.07.2023", "Лучше стучать");

    static HomePage homePage;
    static OrderPage orderPage;

    @Test
    public void testOrderViaButtonInHeaderCorrectData(){

        homePage = new HomePage(webDriver);
        homePage.clickOrderButton();
        orderPage = new OrderPage(webDriver);
        orderPage.waitForPageLoad();
        orderPage.fillFirstFormFields(testDataSet1);
        orderPage.clickSubmitButton();
        orderPage.waitForDetailsFormLoad();
        orderPage.chooseDate(testDataSet1.date);
        orderPage.chooseRentDuration();
        orderPage.chooseGreyColor();
        orderPage.writeComment(testDataSet1.comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        orderPage.waitConfirmation();
        assert orderPage.getConfirmationHeadingText().contains("Заказ оформлен");
    }

    @Test
    public void testOrderViaButtonInBottomAndCheckLink() {

        homePage = new HomePage(webDriver);
        homePage.acceptCookies();
        homePage.clickOrderButtonInBottom();
        orderPage = new OrderPage(webDriver);
        orderPage.waitForPageLoad();
        orderPage.fillFirstFormFields(testDataSet2);
        orderPage.clickSubmitButton();
        orderPage.waitForDetailsFormLoad();
        orderPage.chooseDate(testDataSet2.date);
        orderPage.chooseRentDuration();
        orderPage.chooseBlackColor();
        orderPage.writeComment(testDataSet2.comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        orderPage.waitConfirmation();
        assert orderPage.getConfirmationHeadingText().contains("Заказ оформлен");
    }
}
