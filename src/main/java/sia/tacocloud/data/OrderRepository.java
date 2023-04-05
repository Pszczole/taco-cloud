package sia.tacocloud.data;

import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.model.TacoOrder;
import sia.tacocloud.model.User;

import java.util.List;

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}


