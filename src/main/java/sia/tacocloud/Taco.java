package sia.tacocloud;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/*
Taco class which contains name of our taco and list of ingredients with
validation
 */
@Data
public class Taco {


    @NotNull
    @Pattern(regexp = "^[a-zA-Z]*$",message = "Name must be at least 5 characters long.")
    private String name;

    @NotNull
    @Size(min=1,message = "You must choose at least 1 ingredient.")
    private List<Ingredient> ingredients;
}
