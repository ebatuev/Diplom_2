import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import model_POJO.UserRegistration;
import step.BaseMethods;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class) // Аннотация. Перед тестовым классом нужно указать раннер Parameterized — класс, который помогает запускать тесты с параметризацией
public class RegistrationUserAndDoNotFillInOneOfRequiredFields extends BaseMethods { // класс RegistrationUserAndDoNotFillInOneOfRequiredFields - наследник класса BaseMethods

    private  String email; // создали поля тестового класса
    private  String password;
    private  String name;

    // Объявить конструктор класса. Он принимает в качестве параметров значения всех полей класса
    public RegistrationUserAndDoNotFillInOneOfRequiredFields(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] registerUserData() {
        return new Object[][]{
                {"", "123456", "Evgeniy"},
                {"demuha.evg@yandex.ru", "", "Evgeniy"},
                {"demuha.evg@yandex.ru", "123456", ""}, // передали тестовые данные
        };
    }

    @Test
    @DisplayName("Создать пользователя и не заполнить одно из обязательных полей. Параметризованный тест")
    @Description("Ожидаемый результат: код 403, пользователь не создан")
    public void RegistrationUserWithoutRequiredField() {
        UserRegistration registerUserPOJO = new UserRegistration(email, password, name);
        Response response = getUser().registrationUser(registerUserPOJO);
        response.then().assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Email, password and name are required fields"))
                .and()
                .statusCode(403);
    }

}
