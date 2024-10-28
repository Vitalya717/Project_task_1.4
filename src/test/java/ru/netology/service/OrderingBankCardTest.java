package ru.netology.service;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class OrderingBankCardTest {
    @Test
    public void fillInTheCardDeliveryFormWithValidValues() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesWithoutSpecifyingTheCity() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void specifyCityThatDoesNotBelongToTheAdministrativeCentersOfTheRussianFederation() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Лондон");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCityFieldInLatinLetters() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Saratov");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCityFieldWithValidValueHighlightedWithApostrophes() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("'Саратов'");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCityFieldWithSpecialSigns() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("!><?.");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCityFieldWithNumbers() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("123456");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCityFieldWithValidValueSeparatedByHyphen() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Ростов-на-Дону");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCityFieldWithValidValueSeparatedBySpace() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCityFieldWithValidValueWithTheLetterЁ() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Орёл");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCityFieldWithValidValueWithTheLetterЙ() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Петропавловск-Камчатский");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCityFieldWithValidValueByWritingTheLetterЁInsteadOfTheLetterE() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Орел");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesWithoutSpecifyingTheDate() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Неверно введена дата")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesWithDateEarlierThanThreeDaysFromTheCurrentDate() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Заказ на выбранную дату невозможен")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesWithDateLaterThanThreeDaysFromTheCurrentDate() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesIndicatingTheCurrentDate() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(0).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Заказ на выбранную дату невозможен")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillInTheCardDeliveryFormWithValidValuesIndicatingThePastDate() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Саратов");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Заказ на выбранную дату невозможен")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitFormWithValidFirstNameAndDoubleLastNameSeparatedByHyphen() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов-Сидоров Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void submitFormWithValidFirstAndLastNameInLatinLetters() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Ivanov Ivan");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitFormWithValidFirstAndLastNameValueWithLetterЁ() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Аксёнов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void submitFormWithValidFirstAndLastNameValueWithLetterЙ() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Сергей");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void submitFormWithValidFirstAndLastNameWithApostrophes() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("'Иванов Иван'");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitTheFormWithoutFirstAndLastName() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitTheFormWithTheFirstAndLastNameFieldFilledInWithSpecialCharacters() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("!<>?");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitTheFormWithTheFirstAndLastNameFieldFilledInWithNumbers() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("123456");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitTheFormWithTheFirstAndLastNameFieldFilledInWithFourSpaces() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("    ");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitTheFormSpecifyingThePhoneNumberWithoutPlus() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillOutTheFormWithoutSpecifyingThePhoneNumber() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillOutTheFormBySpecifyingOneDigitOfThePhoneNumber() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+7");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillOutTheFormWithTenDigitsOfThePhoneNumber() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+7911000778");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void fillOutTheFormBySpecifyingTheTwelveDigitsOfThePhoneNumber() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+791100077889");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $$(Selectors.byCssSelector(".input__sub")).find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void submitFormWithValidValuesAndWithoutConsent() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+791100077889");
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void enteringTwoLettersInTheCityFieldAndThenSelectingDesiredCityFromDropDownList() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").sendKeys("кр");
        $$(".menu-item").find(exactText("Краснодар")).click();
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void selectingSDateForTheWeekAheadStartingFromTheCurrentDateUsingTheCalendarTool() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").sendKeys("кр");
        $$(".menu-item").find(exactText("Краснодар")).click();
        $(".input__icon").click();
        $("[data-step='1']").click();
        $("[data-day='1730581200000']").click();
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79110007788");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(Selectors.byCssSelector("[data-test-id='notification']")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
