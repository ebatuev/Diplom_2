import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import step.BaseMethods;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class RegistrationUserTest extends BaseMethods { // класс RegistrationUserTest - наследник класса BaseMethods

    @Test
    @DisplayName("Успешное создание уникального пользователя") // имя теста
    @Description("Ожидаемый результат: код 201, пользователь создан") // описание теста
    public void registrationNewUser() {
        // отправляет запрос и сохраняет ответ в переменную response, экзмепляр класса Response
        Response response = getUser().registrationUser(getUserRegistration());
        // проверяет, что в теле ответа ключам success, email, name соответствует успеху (success), email и имени пользователя, а Матчер notNullValue() проверяет, что аргумент метода assertThat — не null-значение
        response.then().assertThat()
                .body("success", equalTo(true))
                .and()
                .body("user.email", equalTo(super.getEmail()))
                .and()
                .body("user.name", equalTo(super.getName()))
                .and()
                .body("accessToken", notNullValue())
                .and()
                .body("refreshToken", notNullValue())
                .and()
                // проверяем, что статус-код ответа равен 200
                .statusCode(200);
    }

    @Test
    @DisplayName("Попытка создать пользователя, который уже зарегистрирован")
    @Description("Ожидаемый результат: код 403, пользователь существует")
    public void registrationUserAlreadyExist() {
        getUser().registrationUser(getUserRegistration());
        Response response = getUser().registrationUser(getUserRegistration());
        response.then().assertThat().body("success", equalTo(false))
                .and()
                .body("message", equalTo("User already exists"))
                .and()
                .statusCode(403);
    }

}
