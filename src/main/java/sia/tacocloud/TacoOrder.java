package sia.tacocloud;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder {

    private String deliveryTime;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCV;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }

}
