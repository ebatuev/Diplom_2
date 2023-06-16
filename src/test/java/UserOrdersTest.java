import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import step.BaseMethods;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UserOrdersTest extends BaseMethods { // класс UserOrdersTest - наследник класса BaseMethods

    @Test
    @DisplayName("Получение заказов конкретного авторизованного пользователя") // имя теста
    @Description("Ожидаемый результат: код 200, заказ получен") // описание теста
    public void getUserOrdersForAuthorizedUser() {
        String accessToken = getUser().registrationUser(getUserRegistration()).jsonPath().get("accessToken");
        Response response = getOrder().getUserOrders(accessToken);
        response.then().assertThat()
                .body("success", equalTo(true))
                .body("orders", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    @DisplayName("Получение заказов конкретного неавторизованного пользователя")
    @Description("Ожидаемый результат: код 401, заказ не получен")
    public void getUserOrdersForNotAuthorizedUser() {
        Response response = getOrder().getUserOrders("");
        response.then().assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"))
                .and()
                .statusCode(401);
    }

}
