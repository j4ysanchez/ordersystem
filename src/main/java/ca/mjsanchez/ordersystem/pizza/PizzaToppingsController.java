package ca.mjsanchez.ordersystem.pizza;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.LinkedHashMap;


@RestController
public class PizzaToppingsController {
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/pizza-toppings")
    public Map<String, String> getPizzaToppings() {
        Map<String, String> pizzaToppings = new LinkedHashMap<>();
        pizzaToppings.put("cheese", "Cheese");
        pizzaToppings.put("pepperoni", "Pepperoni");
        pizzaToppings.put("hawaiian", "Hawaiian");
        return pizzaToppings;
    }
}
