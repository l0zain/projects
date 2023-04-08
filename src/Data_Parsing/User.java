package Data_Parsing;

public class User {
    @Column("first name")
    private String firstname;

    @Column("last name")
    private String lastname;

    @Column("age")
    private int age;

    @Column("city")
    private String city;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName= " + firstname + '\'' +
                "lastName= " + lastname + '\'' +
                "age= " + age + '\'' +
                "city= " + city + '\'' + "}";
    }
}
