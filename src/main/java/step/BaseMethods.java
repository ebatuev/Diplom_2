package step;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import model.*;

import java.util.ArrayList;
import java.util.Collections;


public class BaseMethods {

    public final static String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    private final String email = "demuha.evg@yandex.ru";
    private final String password = "123456";
    private final String name = "Evgeniy";
    private final String changeEmail = "change_" + email;
    private final String changeName = "change_" + name;
    private final ArrayList<String> ingredients = new ArrayList<>(Collections.singleton("61c0c5a71d1f82001bdaaa6f"));
    private final ArrayList<String> ingredientsWrong = new ArrayList<>(Collections.singleton("123456"));



    private final UserRegistration userRegistration = new UserRegistration(email, password, name);
    private final UserLogin userLogin = new UserLogin(email, password);
    private final User user = new User();
    private final UserChangeData userChangeData = new UserChangeData(changeEmail, changeName);
    private final OrderCreate orderCreate = new OrderCreate(ingredients);
    private final Order order = new Order();


    // аннотация Before показывает, что метод будет выполняться перед каждым тестовым методом
    // повторяющуюся для разных ручек часть URL лучше записать в переменную в методе Before
    // если в классе будет несколько тестов, указывать её придётся только один раз
    @Before
    public void setUp() {

        RestAssured.baseURI = BASE_URI;
        UserRegistration userRegistration = new UserRegistration(email, password, name);
    }

    // аннотация After показывает, что метод будет выполняться после каждого тестового метода
    // метод cleanUp удаляет пользователя
    @After
    public void cleanUp(){
        Response responseNoChange = getUser().loginUser(getUserLogin());
        if(responseNoChange.jsonPath().get("success").equals(true)){
            getUser().deleteUser(responseNoChange.jsonPath().get("accessToken"));
        }
        Response responseChange = getUser().loginUser(new UserLogin(changeEmail, password));
        if(responseChange.jsonPath().get("success").equals(true)){
            getUser().deleteUser(responseChange.jsonPath().get("accessToken"));
        }
    }

    public String getEmail() {
        return email;
    } // Геттеры позволяют получать значения

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserRegistration getUserRegistration() {
        return userRegistration;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public User getUser() {
        return user;
    }


    public String getChangeEmail() {
        return changeEmail;
    }

    public String getChangeName() {
        return changeName;
    }

    public UserChangeData getUserChangeData() {
        return userChangeData;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public OrderCreate getOrderCreate() {
        return orderCreate;
    }

    public Order getOrder() {
        return order;
    }

    public ArrayList<String> getIngredientsWrong() {
        return ingredientsWrong;
    }

}
