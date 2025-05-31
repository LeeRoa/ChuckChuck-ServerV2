package cc.entity;

import lombok.Getter;

@Getter
public enum YesNo {
    Y(1, "Y"), N(0, "Y");

    private final int value;
    private final String name;

    YesNo(int value, String name) {
        this.value = value;
        this.name = name;
    }
}