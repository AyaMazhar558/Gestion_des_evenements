package com.microservice_notification.microservice_notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private String message;
    @NotNull
    private boolean dejavue;
    @NotNull
    private Long idUser; // Correction camelCase
}
