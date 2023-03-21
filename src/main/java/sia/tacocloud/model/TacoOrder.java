package sia.tacocloud.model;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

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
@Table("orders")
public class TacoOrder implements Serializable {

    private static final long serialVersionUTD = 1L;
    @PrimaryKey
    private UUID id = Uuids.timeBased();

    private Date placedAt = new Date();

    @Column("tacos")
    private List<TacoUDT> tacos = new ArrayList<>();

    public void addTaco(TacoUDT taco){
        this.tacos.add(taco);
    }

}
