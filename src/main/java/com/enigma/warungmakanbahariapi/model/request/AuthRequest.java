package com.enigma.warungmakanbahariapi.model.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    private String email;
    private String password;

}
