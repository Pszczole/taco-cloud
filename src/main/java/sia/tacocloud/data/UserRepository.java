package sia.tacocloud.data;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sia.tacocloud.model.User;
import sia.tacocloud.security.UserDetailsService;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
