package ru.netology.service;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class OrderingBankCardTest {
    SelenideElement form = $(".form");


    @Test
    public void fillInTheCardDeliveryFormWithValidValues() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesWithoutSpecifyingTheCity() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void specifyCityThatDoesNotBelongToTheAdministrativeCentersOfTheRussianFederation() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Лондон");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCityFieldInLatinLetters() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Saratov");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCityFieldWithValidValueHighlightedWithApostrophes() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("'Саратов'");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCityFieldWithSpecialSigns() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("!><?.");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCityFieldWithNumbers() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("123456");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCityFieldWithValidValueSeparatedByHyphen() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Ростов-на-Дону");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCityFieldWithValidValueSeparatedBySpace() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCityFieldWithValidValueWithTheLetterЁ() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Орёл");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCityFieldWithValidValueWithTheLetterЙ() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Петропавловск-Камчатский");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCityFieldWithValidValueByWritingTheLetterЁInsteadOfTheLetterE() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Орел");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesWithoutSpecifyingTheDate() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Неверно введена дата")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesWithDateEarlierThanThreeDaysFromTheCurrentDate() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Заказ на выбранную дату невозможен")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesWithDateLaterThanThreeDaysFromTheCurrentDate() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesIndicatingTheCurrentDate() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(0).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Заказ на выбранную дату невозможен")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesIndicatingThePastDate() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Заказ на выбранную дату невозможен")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitFormWithValidFirstNameAndDoubleLastNameSeparatedByHyphen() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов-Сидоров Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void submitFormWithValidFirstAndLastNameInLatinLetters() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Ivanov Ivan");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitFormWithValidFirstAndLastNameValueWithLetterЁ() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Аксёнов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void submitFormWithValidFirstAndLastNameValueWithLetterЙ() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Сергей");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void submitFormWithValidFirstAndLastNameWithApostrophes() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("'Иванов Иван'");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitTheFormWithoutFirstAndLastName() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitTheFormWithTheFirstAndLastNameFieldFilledInWithSpecialCharacters() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("!<>?");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitTheFormWithTheFirstAndLastNameFieldFilledInWithNumbers() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("123456");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitTheFormWithTheFirstAndLastNameFieldFilledInWithFourSpaces() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("    ");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitTheFormSpecifyingThePhoneNumberWithoutPlus() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillOutTheFormWithoutSpecifyingThePhoneNumber() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillOutTheFormBySpecifyingOneDigitOfThePhoneNumber() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+7");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillOutTheFormWithTenDigitsOfThePhoneNumber() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+7911000778");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillOutTheFormBySpecifyingTheTwelveDigitsOfThePhoneNumber() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+791100077889");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitFormWithValidValuesAndWithoutConsent() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+791100077889");
        form.$(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    public void enteringTwoLettersInTheCityFieldAndThenSelectingDesiredCityFromDropDownList() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").sendKeys("кр");
        $$(".menu-item").find(exactText("Краснодар")).click();
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void selectingSDateForTheWeekAheadStartingFromTheCurrentDateUsingTheCalendarTool() {
        open("http://localhost:9999");
        form.$("[data-test-id='city'] input").sendKeys("кр");
        $$(".menu-item").find(exactText("Краснодар")).click();
        form.$(".input__icon").click();
        $("[data-step='1']").click();
        $("[data-day='1730581200000']").click();
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79110007788");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(Selectors.byCssSelector("[data-test-id='notification']")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
