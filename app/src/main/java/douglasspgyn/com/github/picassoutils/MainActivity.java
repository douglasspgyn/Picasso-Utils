package douglasspgyn.com.github.picassoutils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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
    public void loadDefaultImage() {
        PicassoUtils.loadWithThumbnail(this, image, R.drawable.ic_image_black_24dp, thumbnailURL, highQualityURL);
        PicassoUtils.loadAndBlur(this, backgroundImage, R.drawable.background_placeholder, lowQualityURL);
    }

    @OnClick(R.id.load_custom_image)
    public void loadCustomImage() {
        final View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_image_qualities, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogLayout)
                .setPositiveButton(getString(R.string.load_image), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        final AlertDialog dialog = builder.create();
        dialog.show();

        final EditText dialogThumbnailURL = ButterKnife.findById(dialogLayout, R.id.dialog_thumbnail_url);
        final EditText dialogHighQualityURL = ButterKnife.findById(dialogLayout, R.id.dialog_high_quality_url);
        final EditText dialogBackgroundQualityURL = ButterKnife.findById(dialogLayout, R.id.dialog_background_url);
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = true;
                dialogThumbnailURL.setError(null);
                dialogHighQualityURL.setError(null);
                dialogBackgroundQualityURL.setError(null);


                if (dialogThumbnailURL.getText().toString().isEmpty()) {
                    dialogThumbnailURL.setError(getString(R.string.empty_field));
                    valid = false;
                }
                if (dialogHighQualityURL.getText().toString().isEmpty()) {
                    dialogHighQualityURL.setError(getString(R.string.empty_field));
                    valid = false;
                }
                if (dialogBackgroundQualityURL.getText().toString().isEmpty()) {
                    dialogBackgroundQualityURL.setError(getString(R.string.empty_field));
                    valid = false;
                }

                if (valid) {
                    dialog.dismiss();
                    PicassoUtils.loadWithThumbnail(getBaseContext(), image, R.drawable.ic_image_black_24dp, dialogThumbnailURL.getText().toString(), dialogHighQualityURL.getText().toString());
                    PicassoUtils.loadAndBlur(getBaseContext(), backgroundImage, R.drawable.background_placeholder, dialogBackgroundQualityURL.getText().toString());
                }
            }
        });
    }
}
