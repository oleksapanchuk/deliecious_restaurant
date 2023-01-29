package ua.deliciousrestaurant.model.entity;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class Client implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private int clientId;
    @EqualsAndHashCode.Exclude private Role role;
    private String email;
    private transient String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNum;
}


