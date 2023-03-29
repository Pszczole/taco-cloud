package sia.tacocloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import sia.tacocloud.data.security.OrderAdminService;

@Controller
public class AdminController {
    private OrderAdminService adminService;

    public AdminController(OrderAdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        adminService.deleteAllOrders();
        return  "redirect:/admin";
    }
}
