package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.model.Taco;
import sia.tacocloud.model.TacoOrder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readTacoOrderByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}


