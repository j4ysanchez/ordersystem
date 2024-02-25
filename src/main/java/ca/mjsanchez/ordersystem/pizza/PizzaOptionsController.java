package ca.mjsanchez.ordersystem.pizza;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class PizzaOptionsController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/pizza-sizes")
    public Map<String, String> getPizzaSizes() {
        Map<String, String> pizzaSizes = new LinkedHashMap<>();
        pizzaSizes.put("small", "Small");
        pizzaSizes.put("medium", "Medium");
        pizzaSizes.put("large", "Large");
        return pizzaSizes;
    }
}
