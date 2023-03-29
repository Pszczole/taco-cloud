package sia.tacocloud.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Taco class which includes every information that is needed to make an order with
validation
 */
@Data
@Entity
@Table(name = "Taco_Order")
public class TacoOrder implements Serializable {

    private static final long serialVersionUTD = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt = new Date();
    @NotBlank(message = "This field is required")
    private String deliveryTime;
    @NotBlank(message = "This field is required")
    private String deliveryStreet;
    @NotBlank(message = "This field is required")
    private String deliveryCity;
    @NotBlank(message = "This field is required")
    private String deliveryState;
    @NotBlank(message = "This field is required")
    private String deliveryZip;

    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;
    private String ccCV;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }



}
