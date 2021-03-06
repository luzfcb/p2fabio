
package info.fabiodev.app.p2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * nostra13 Android-Universal-Image-Loader
 * https://github.com/nostra13/Android-Universal-Image-Loader#usage
 */
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * excilys Android Annotations
 * https://github.com/excilys/androidannotations/wiki/HowItWorks
 */
import org.androidannotations.annotations.*;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by luzfcb on 25/11/14.
 */
@EActivity(R.layout.activity_main)
public class MainActivity
    extends Activity
{
    private ImageLoader imageLoader;

    @ViewById
    protected ImageView imageView_principal;

    @ViewById
    protected ProgressBar progressBar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                //.cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                    .defaultDisplayImageOptions(defaultOptions)
                    .writeDebugLogs()
                    .build();
        //ImageLoader.getInstance().init(config);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        //ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())



    }

    @AfterViews
    void afterViews() {
        this.progressBar2.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater();
        return true;
    }

    @Click(R.id.obter_dados)
    protected void obter_dados(){
        RestClient restclient = new RestClient(this, this.imageLoader, this.imageView_principal, this.progressBar2);
        restclient.execute();

    }




}
