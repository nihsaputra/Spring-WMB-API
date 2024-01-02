package com.enigma.warungmakanbahariapi.model.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuRequest {

    private String name;
    private Long price;

}
