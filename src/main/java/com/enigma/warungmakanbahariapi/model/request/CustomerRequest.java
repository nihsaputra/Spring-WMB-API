package com.enigma.warungmakanbahariapi.model.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    private String id;
    private String name;
    private String phoneNumber;

}
