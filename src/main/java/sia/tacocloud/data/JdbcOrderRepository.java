package sia.tacocloud.data;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sia.tacocloud.model.Ingredient;
import sia.tacocloud.model.IngredientRef;
import sia.tacocloud.model.Taco;
import sia.tacocloud.model.TacoOrder;
import java.util.Optional;

import java.util.Arrays;
import java.util.Date;
import java.sql.Types;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    /*
    TacoOrder save method:
    PreparedStatementCreatorFactory describes insert into query, along with types,
    setReturnGeneratedKeys(true) is for fetching order saved ID's.
    After defining pscf we are passing in the values from the TacoOrder object to
    saveTaco method.
     */
    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Taco_Order " +
                                "(delivery_name, delivery_street," +
                                "delivery_city, delivery_state, delivery_zip," +
                                "cc_number, cc_expiration, cc_ccv, placed_at)" +
                                "values(?,?,?,?,?,?,?,?,?)",
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
                );

        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                order.getDeliveryName(),
                                order.getDeliveryStreet(),
                                order.getDeliveryCity(),
                                order.getDeliveryState(),
                                order.getDeliveryZip(),
                                order.getCcNumber(),
                                order.getCcExpiration(),
                                order.getCcCVV(),
                                order.getPlacedAt())
                        );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc,keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        int i = 0;
        for(Taco taco:tacos){
            saveTaco(orderId,i++,taco);
        }

        return order;
    }

    private long saveTaco(Long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Taco "
                                + "(name, created_at, taco_order, taco_order_key) "
                                + "values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                taco.getCreatedAt(),
                                orderId,
                                orderKey));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveIngredientRefs(tacoId, taco.getIngredients());

        return tacoId;
    }

    private void saveIngredientRefs(
            long tacoId, List<Ingredient> ingredients) {
        int key = 0;
        for (Ingredient ingredient : ingredients) {
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient, taco, taco_key) "
                            + "values (?, ?, ?)",
                    ingredient.getName(), tacoId, key++);
        }
    }


}
