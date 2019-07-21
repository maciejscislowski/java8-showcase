package pl.programmerid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maciej.Scislowski@gmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String firstName;
    private int age;

}
