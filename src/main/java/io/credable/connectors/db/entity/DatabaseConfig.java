package io.credable.connectors.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name ="database_config")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DatabaseConfig {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String databaseName;
    private String databaseType;
    private String schemaName;
    private boolean useTls;
    @Column(nullable = false)
    private String clientId;
}
