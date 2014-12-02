package info.fabiodev.app.p2;

import com.goebl.david.Webb;
import com.telly.groundy.GroundyTask;
import com.telly.groundy.TaskResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sutransdev on 02/12/14.
 */
public class RestClientTask extends GroundyTask {
    private Webb webb;
    private JSONArray pizzasJsonArray;
    private ArrayList<JSONObject> pizzas_jsonObjectArrayList;
    private ArrayList<Pizza> pizzaArrayList;

    @Override
    protected TaskResult doInBackground() {
        // you can send parameters to the task using a Bundle (optional)
        String exampleParam = getStringArg("arg_name");

        webb = Webb.create();
        pizzasJsonArray = webb
                .get("http://fratelli.herokuapp.com/pizzas/?format=json")
                .ensureSuccess()
                .asJsonArray()
                .getBody();

        try {

            //pizzas = pizzasJsonArray.getJSONArray("pizzas");
            //pizzas = new JSONArray(this.pizzasJsonArray);
            pizzas_jsonObjectArrayList = new ArrayList<JSONObject>();
            pizzaArrayList = new ArrayList<Pizza>();
            for (int i = 1; i < pizzasJsonArray.length(); i++) {
                pizzas_jsonObjectArrayList.add(pizzasJsonArray.getJSONObject(i));
                pizzaArrayList.add(new Pizza(pizzasJsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        // return a TaskResult depending on the success of your task
        // and optionally pass some results back
        return succeeded().add("the_result", "some result");
    }
}