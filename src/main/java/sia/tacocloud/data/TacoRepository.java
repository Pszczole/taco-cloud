package sia.tacocloud.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.model.Taco;

import java.net.ContentHandler;

public interface TacoRepository  extends CrudRepository<Taco, Long> {

}
