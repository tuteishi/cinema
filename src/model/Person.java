package model;

public class Person {
    private Integer Id;
    private String username;
    private String password;
    private Role role;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Person(Integer id, String username, String password, Role role) {
        Id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person Id = " + Id + " | " +
               "Username = " + username + " | " +
               "Role = " + role + " | ";
    }
}
