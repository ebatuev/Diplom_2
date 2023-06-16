import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import model_POJO.UserLogin;
import step.BaseMethods;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class LoginUserTest extends BaseMethods { // класс LoginUserTest - наследник класса BaseMethods

    @Test
    @DisplayName("Логин под существующим пользователем") // имя теста
    @Description("Ожидаемый результат: код 200, пользователь залогинелся") // описание теста
    public void correctLogin() {
        getUser().registrationUser(getUserRegistration());
        Response response = getUser().loginUser(getUserLogin());
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
                .statusCode(200);
    }

    @Test
    @DisplayName("Логин с неверным логином (email)")
    @Description("Ожидаемый результат: код 401, пользователь не залогинелся")
    public void loginWithWrongEmail() {
        getUser().registrationUser(getUserRegistration());
        UserLogin loginUserPOJO = new UserLogin("", super.getPassword());
        Response response = getUser().loginUser(loginUserPOJO);
        response.then().assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"))
                .and()
                .statusCode(401);
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    @Description("Ожидаемый результат: код 401, пользователь не залогинелся")
    public void loginWithWrongPassword() {
        getUser().registrationUser(getUserRegistration());
        UserLogin loginUserPOJO = new UserLogin(super.getEmail(), "");
        Response response = getUser().loginUser(loginUserPOJO);
        response.then().assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"))
                .and()
                .statusCode(401);
    }

}
