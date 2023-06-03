package ru.praktikum.selenium;

import org.junit.Assert;
import org.junit.Test;
import ru.praktikum.selenium.data.TestData;
import ru.praktikum.selenium.pageobject.HomePage;
import ru.praktikum.selenium.pageobject.OrderPage;

import static org.hamcrest.CoreMatchers.containsString;

public class OrderTest extends BaseTest {
    HomePage homePage;
    OrderPage orderPage;
    TestData dataSetForOrderViaButtonInHeader = new TestData("Ян", "Ли", "3-я улица Ямского поля, 6", "Черкизовская", "+79160000002", "15.06.2023",
            "Позвоните мне");
    TestData dataSetForOrderViaButtonInBottom = new TestData("Яна", "Рождественская", "Зубовский бульвар, 5", "Преображенская площадь", "+791600000027",
            "14.07.2023", "Лучше стучать");

    @Test
    public void testOrderViaButtonInHeaderCorrectData() {

        homePage = new HomePage(webDriver);
        homePage.clickOrderButton();
        orderPage = new OrderPage(webDriver);
        orderPage.waitForPageLoad();
        orderPage.fillFirstFormFields(dataSetForOrderViaButtonInHeader);
        orderPage.clickSubmitButton();
        orderPage.waitForDetailsFormLoad();
        orderPage.chooseDate(dataSetForOrderViaButtonInHeader.date);
        orderPage.chooseRentDuration();
        orderPage.chooseGreyColor();
        orderPage.writeComment(dataSetForOrderViaButtonInHeader.comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        orderPage.waitConfirmation();
        Assert.assertThat(orderPage.getConfirmationHeadingText(), containsString("Заказ оформлен"));
    }

    @Test
    public void testOrderViaButtonInBottomAndCheckLink() {

        homePage = new HomePage(webDriver);
        homePage.acceptCookies();
        homePage.clickOrderButtonInBottom();
        orderPage = new OrderPage(webDriver);
        orderPage.waitForPageLoad();
        orderPage.fillFirstFormFields(dataSetForOrderViaButtonInBottom);
        orderPage.clickSubmitButton();
        orderPage.waitForDetailsFormLoad();
        orderPage.chooseDate(dataSetForOrderViaButtonInBottom.date);
        orderPage.chooseRentDuration();
        orderPage.chooseBlackColor();
        orderPage.writeComment(dataSetForOrderViaButtonInBottom.comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        orderPage.waitConfirmation();
        Assert.assertThat(orderPage.getConfirmationHeadingText(), containsString("Заказ оформлен"));
    }
}
