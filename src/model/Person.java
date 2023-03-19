package model;

public class Person {
    private int Id;
    private String username;
    private String password;
    private String role;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Person(int id, String username, String password, String role) {
        Id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
               "Id=" + Id +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", role='" + role + '\'' +
               '}';
    }
}
