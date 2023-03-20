package sia.tacocloud.data;

import sia.tacocloud.model.TacoOrder;
import java.util.Optional;
public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
