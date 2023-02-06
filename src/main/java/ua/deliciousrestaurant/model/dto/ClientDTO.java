package ua.deliciousrestaurant.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.deliciousrestaurant.model.entity.Role;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(of = {"email", "firstName", "lastName", "address"})
@Builder
public class ClientDTO implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private int clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNum;
    private Role role;
    private int walletBalance;
    private int totalOrders;
    private int totalFundsSpent;
    private boolean notification;
}
