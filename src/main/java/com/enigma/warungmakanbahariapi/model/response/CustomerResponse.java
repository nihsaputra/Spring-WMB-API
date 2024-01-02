package com.enigma.warungmakanbahariapi.model.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
}
