package br.com.douglasffilho.UserServices.utils;

public enum ProfileEnum {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private String value;

    ProfileEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
