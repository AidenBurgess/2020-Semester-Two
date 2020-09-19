package se325.assignment01.concert.service.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
    @Id
    private Long id;

    private String username;
    private String password;
    private int version;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
