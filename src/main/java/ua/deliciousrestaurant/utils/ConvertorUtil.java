package ua.deliciousrestaurant.utils;

import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.model.entity.Client;

public final class ConvertorUtil {

    public static ClientDTO convertUserToDTO(Client client) {
        return ClientDTO.builder()
                .clientId(client.getClientId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .address(client.getAddress())
                .phoneNum(client.getPhoneNum())
                .role(client.getRole())
                .build();
    }

}
