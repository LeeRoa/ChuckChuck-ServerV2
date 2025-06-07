package cc.test.roa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoaDto {

    private Long id;
    private String name;
    private int age;

    public RoaDto(String name, int age) {
        this(null, name, age);
    }

}
