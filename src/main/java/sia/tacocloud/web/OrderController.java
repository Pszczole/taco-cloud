package sia.tacocloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.data.OrderRepository;
import sia.tacocloud.model.TacoOrder;

import javax.validation.Valid;
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


    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("tacoOrder",new TacoOrder());
        return "orderForm";
    }

    /*
    This method handles submitted order for now
     */
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus){

        if(errors.hasErrors()){
            return "orderForm";
        }

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

}
