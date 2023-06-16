package model_POJO;


// Cоздать класс UserChangeData. Его полями будут ключи из JSON — email и name.
public class UserChangeData {

    private String email; // ключ email стал полем типа String
    private String name; // ключ name стал полем типа String


    // Конструктор со всеми параметрами
    public UserChangeData(String email, String name) {
        this.email = email;
        this.name = name;
    }

    // Конструктор без параметров
    public UserChangeData() {
    }


    // К каждому полю написать геттеры и сеттеры, они обеспечивают доступ к полям класса (Геттеры позволяют получать значения, сеттеры — изменять их)

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
