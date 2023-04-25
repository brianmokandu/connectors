package io.credable.connectors;

public enum DatabaseType {
    MYSQL("mysql"),
    POSTGRESQL("postgresql"),
    ORACLE("oracle"),
    MSSQL("mssql"),
    H2("h2"),
    HSQLDB("hsqldb"),
    SQLITE("sqlite");
    private final String type;

    DatabaseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
