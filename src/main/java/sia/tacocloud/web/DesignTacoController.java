package sia.tacocloud.web;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import sia.tacocloud.model.Ingredient;
import sia.tacocloud.model.Ingredient.Type;
import sia.tacocloud.model.Taco;
import sia.tacocloud.model.TacoOrder;
import sia.tacocloud.data.IngredientRepository;

import javax.validation.Valid;

/*
This class handles HTTP requests and hand off a requests to a view
to render HTML.
 */

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();

        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients,type));
        }
    }


    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), ingredients.stream()
                    .filter(it -> it.getType().name().equals(type.name()))
                    .collect(Collectors.toList()));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }


    /*
    Method that handles POST request after submitting your taco
    from home page
     */
    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors
            ,@ModelAttribute TacoOrder tacoOrder){

        if(errors.hasErrors()){
            return "design";
        }


        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}",taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }



}
