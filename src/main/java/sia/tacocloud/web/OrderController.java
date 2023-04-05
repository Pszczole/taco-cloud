package sia.tacocloud.web;

import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.config.OrderProps;
import sia.tacocloud.data.OrderRepository;
import sia.tacocloud.model.TacoOrder;
import sia.tacocloud.model.User;

import javax.validation.Valid;
import java.util.function.ToDoubleBiFunction;
/*
This OrderController class handles our processTaco
(After creating taco, you will be redirected to orderForm view)
 */


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderProps orderProps;
    private int pageSize = 20;

    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    public OrderController(OrderRepository orderRepository, OrderProps orderProps){
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("tacoOrder",new TacoOrder());
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user,
                                Model model){

        Pageable pageable = PageRequest.of(0,20);
        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        //View that displays listOfOrders for user
        //Todo{"Create a orderList template"}
        return "orderList";
    }

    /*
    This method handles submitted order for now
     */
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user){

        if(errors.hasErrors()){
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

}
