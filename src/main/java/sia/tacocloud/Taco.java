package sia.tacocloud;

import lombok.Data;

import java.util.List;

/*
Taco class which contains name of our taco and list of ingredients
 */
@Data
public class Taco {

    private String name;
    private List<Ingredient> ingredients;
}
