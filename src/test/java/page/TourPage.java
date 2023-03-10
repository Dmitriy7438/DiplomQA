package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DBHelper;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TourPage {
    HomePage homePage = new HomePage();

    private final SelenideElement continueButton = $x("//*[@id='root']/div/form/fieldset/div[4]/button");
    private final SelenideElement titleCard = $x("//*[@id='root']/div/h3");


    private final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement cardMonth = $("[placeholder='08']");
    private final SelenideElement cardYear = $("[placeholder='22']");
    private final SelenideElement cardHolder = $x("//*[@id='root']/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private final SelenideElement cardCVC = $("[placeholder='999']");


    private final SelenideElement notificationTitleAccept = $(".notification_status_ok");
    private final SelenideElement notificationContentAccept = $(".notification_status_ok");

    private final SelenideElement notificationTitleDenial = $(".notification_status_error");
    private final SelenideElement notificationContentDenial = $(".notification_status_error");


    private final SelenideElement numberFieldError = $x("//*[@id='root']/div/form/fieldset/div[1]/span/span/span[3]");
    private final SelenideElement monthFieldError = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]");
    private final SelenideElement yearFieldError = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]");
    private final SelenideElement holderFieldError = $x("//*[@id='root']/div/form/fieldset/div[3]/span/span[1]/span/span/span[3]");
    private final SelenideElement CVCFieldError = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]");


    public void completePayFrom(String number, String month, String year, String holder, String cvc) {
        homePage.clickPayButton();
        titleCard.shouldBe(Condition.text("???????????? ???? ??????????"));
        cardNumber.setValue(number);
        cardMonth.setValue(month);
        cardYear.setValue(year);
        cardHolder.setValue(holder);
        cardCVC.setValue(cvc);

    }

    public ArrayList<String> getFrom() {
        ArrayList<String> list = new ArrayList<>();
        list.add(cardNumber.getValue());
        list.add(cardMonth.getValue());
        list.add(cardYear.getValue());
        list.add(cardHolder.getValue());
        list.add(cardCVC.getValue());
        return list;
    }

    public void completeCreditFrom(String number, String month, String year, String holder, String cvc) {
        homePage.clickCreditButton();
        titleCard.shouldBe(Condition.text("???????????? ???? ???????????? ??????????"));
        cardNumber.setValue(number);
        cardMonth.setValue(month);
        cardYear.setValue(year);
        cardHolder.setValue(holder);
        cardCVC.setValue(cvc);

    }


    public void continueClick() {
        continueButton.click();
    }

    public void acceptAssertion() {
        notificationTitleAccept.shouldBe(Condition.text("??????????????"), Duration.ofSeconds(15)).shouldBe(Condition.visible);
        notificationContentAccept.shouldBe(Condition.text("???????????????? ???????????????? ????????????."), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    public void denialAssertion() {
        notificationTitleDenial.shouldBe(Condition.text("????????????"), Duration.ofSeconds(15)).shouldBe(Condition.visible);
        notificationContentDenial.shouldBe(Condition.text("????????????! ???????? ?????????????? ?? ???????????????????? ????????????????."), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    public void numberFieldFormatError() {
        numberFieldError.shouldBe(Condition.text("???????????????? ????????????"), Condition.visible);
    }

    public void monthFieldFormatError() {
        monthFieldError.shouldBe(Condition.text("???????????????? ????????????"), Condition.visible);
    }

    public void monthFieldPeriodError() {
        monthFieldError.shouldBe(Condition.text("?????????????? ???????????? ???????? ???????????????? ??????????"), Condition.visible);
    }

    public void yearFieldFormatError() {
        yearFieldError.shouldBe(Condition.text("???????????????? ????????????"), Condition.visible);
    }

    public void yearFieldMinusPeriodError() {
        yearFieldError.shouldBe(Condition.text("?????????? ???????? ???????????????? ??????????"), Condition.visible);
    }

    public void yearFieldPlusPeriodError() {
        yearFieldError.shouldBe(Condition.text("?????????????? ???????????? ???????? ???????????????? ??????????"), Condition.visible);
    }

    public void holderFieldEmptyError() {
        holderFieldError.shouldBe(Condition.text("???????? ?????????????????????? ?????? ????????????????????"), Condition.visible);
    }

    public void holderFieldFormatError() {
        holderFieldError.shouldBe(Condition.text("???????????????? ????????????"), Condition.visible);
    }

    public void CVCFieldFormatError() {
        CVCFieldError.shouldBe(Condition.text("???????????????? ????????????"), Condition.visible);
    }

    public void payApprovedStatusAssertion() {

        String statusExpected = "APPROVED";
        String statusActual = DBHelper.getPaymentStatusDB();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void payDeclinedStatusAssertion() {
        String statusExpected = "DECLINED";
        String statusActual = DBHelper.getPaymentStatusDB();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void payAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DBHelper.getPaymentCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void payDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DBHelper.getPaymentCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void creditApprovedStatusAssertion() {
        String statusExpected = "APPROVED";
        String statusActual = DBHelper.getCreditStatusDB();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void creditDeclinedStatusAssertion() {
        String statusExpected = "DECLINED";
        String statusActual = DBHelper.getCreditStatusDB();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void creditAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DBHelper.getCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void creditDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DBHelper.getCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void orderAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DBHelper.getOrderCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void orderDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DBHelper.getOrderCount();
        Assertions.assertEquals(countExpected, countActual);
    }
}
