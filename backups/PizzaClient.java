package info.fabiodev.app.p2;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;


import java.util.List;

/**
 * Created by oficina on 02/12/14.
 */
@Rest(rootUrl = "http://fratelli.herokuapp.com", converters = { GsonHttpMessageConverter.class })
public interface PizzaClient {

    @Get("/pizzas/?format=json")
    List<Pizza> getPizzas();

}
