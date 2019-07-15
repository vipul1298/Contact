package android.example.contactlist.Model;

public class Listdata {
    int user_id;
    String name;
    String phone;
    String email;
    String city;
//
    public Listdata(int user_id,String name, String phone, String email, String city) {
        this.name = name;
        this.user_id=user_id;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }

    public Listdata() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
