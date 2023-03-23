package sia.tacocloud.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
Taco class which includes every information that is needed to make an order with
validation
 */
@Data
@Document
public class TacoOrder implements Serializable {

    private static final long serialVersionUTD = 1L;
    @Id
    private String id;

    private Date placedAt = new Date();


    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }

}
