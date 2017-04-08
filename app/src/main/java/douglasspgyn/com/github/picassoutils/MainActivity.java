package douglasspgyn.com.github.picassoutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douglasspgyn.com.github.picassoutils.utils.PicassoUtils;

public class MainActivity extends AppCompatActivity {

    private static final String thumbnailURL = "https://raw.githubusercontent.com/douglasspgyn/Picasso-Utils/master/app/src/main/res/drawable/thumbnail.jpg";
    private static final String lowQualityURL = "https://raw.githubusercontent.com/douglasspgyn/Picasso-Utils/master/app/src/main/res/drawable/low_quality.jpg";
    private static final String highQualityURL = "https://raw.githubusercontent.com/douglasspgyn/Picasso-Utils/master/app/src/main/res/drawable/high_quality.jpg";

    @Bind(R.id.background_image)
    ImageView backgroundImage;
    @Bind(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.load_default_image)
    public void loadImage() {
        PicassoUtils.loadWithThumbnail(this, image, R.drawable.ic_image_black_24dp, thumbnailURL, highQualityURL);
        PicassoUtils.loadAndBlur(this, backgroundImage, R.drawable.background_placeholder, lowQualityURL);
    }
}
