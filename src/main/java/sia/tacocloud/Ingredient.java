package sia.tacocloud;

import lombok.Data;
import org.springframework.asm.Type;
/*
Taco class which contains information about taco Ingredient
 */
@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
