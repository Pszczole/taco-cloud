package sia.tacocloud.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.model.Ingredient;
import sia.tacocloud.model.Ingredient.Type;
public interface IngredientRepository extends CrudRepository<Ingredient, String> {


}
