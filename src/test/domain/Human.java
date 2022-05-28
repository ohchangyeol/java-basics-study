package test.domain;

public enum Human {
    age("25"),
    job("developer"),
    address("양주시"),
    name("홍길동");

    private String value;

    Human(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
