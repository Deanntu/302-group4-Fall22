package com.gurup.domain;

public enum DatabaseRequirements {
    url("jdbc:postgresql://localhost/gurup"),
    username("postgres"),
    password("123456"),
    driver("org.postgresql.Driver");
    private final String value;

    DatabaseRequirements(String string) {
        // TODO Auto-generated constructor stub
        this.value = string;
    }

    public String getValue() {
        return value;
    }
}
