package cc.test.roa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "roa")
public class Roa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    public Roa(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static Roa of(String name, Integer age) {
        return new Roa(name,age);
    }

}
