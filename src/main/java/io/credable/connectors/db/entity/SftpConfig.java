package io.credable.connectors.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name ="sftp_config")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SftpConfig {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotBlank
    private String host;
    @NotBlank
    private String username;
    @Column(columnDefinition = "LONGTEXT")
    private String privateKey;
    private String location;

    private String passphrase;// will also be used as password for username:password

    private int port;
    private  boolean active=true;

}
