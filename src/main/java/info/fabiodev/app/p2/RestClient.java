package info.fabiodev.app.p2;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * https://github.com/hgoebl/DavidWebb
 * Lightweight Java HTTP-Client for calling JSON REST-Services (especially for Android)
 */
import com.goebl.david.Webb;

import com.nostra13.universalimageloader.core.ImageLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by luzfcb on 25/11/14.
 */

public class RestClient extends AsyncTask<String, Integer, String> {
    private ImageView imageView;
    private ImageLoader imageLoader;

    public ArrayList<JSONObject> getPizzas_jsonObjectArrayList() {
        return pizzas_jsonObjectArrayList;
    }

    private Webb webb;
    private JSONArray pizzasJsonArray;
    private ArrayList<JSONObject> pizzas_jsonObjectArrayList;
    private Activity activity;

    @Override
    protected void onPreExecute() {

    }

    public RestClient(Activity activity, ImageLoader imageLoader, ImageView imageView) {
        this.imageLoader = imageLoader;
        this.imageView = imageView;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub

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
            for (int i = 1; i < pizzasJsonArray.length(); i++) {
                pizzas_jsonObjectArrayList.add(pizzasJsonArray.getJSONObject(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub

        // Update your UI here
        Log.v("json-pizzasJsonArray", this.pizzasJsonArray.toString());
        Log.v("json-pizzas_jsonObjectArrayList", this.pizzas_jsonObjectArrayList.toString());
        JSONObject jsonObject = this.pizzas_jsonObjectArrayList.get(0);
        Pizza pizza = null;
        try {
            //pizza = new Pizza(jsonObject.getString("nome"), jsonObject.getString("tamanho"), jsonObject.getString("ingredientes"), jsonObject.getString("valor"), jsonObject.getString("foto"));
            pizza = new Pizza(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.v("json-pizzas_jsonObjectArrayList", pizzas_jsonObjectArrayList.toString());
        assert pizza != null;
        Log.v("pizza", pizza.toString());

        Toast.makeText(this.activity, "asdad", 5).show();
        Log.v("teste", "asdad");
        imageLoader.displayImage(pizza.getFoto(), imageView);
    }
}