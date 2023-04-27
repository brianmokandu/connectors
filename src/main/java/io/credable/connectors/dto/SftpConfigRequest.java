package io.credable.connectors.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SftpConfigRequest {
    private UUID id;
    @NotBlank
    @NotNull
    private String host;
    @NotBlank
    @NotNull
    private String username;
    private String privateKey;
    @NotBlank
    @NotNull
    private String location;
    private String passphrase;// will also be used as password for username:password
    private int port=22;
    private  boolean active=true;
    @io.credable.connectors.util.UUID
    @NotNull
    private String clientId;

}
