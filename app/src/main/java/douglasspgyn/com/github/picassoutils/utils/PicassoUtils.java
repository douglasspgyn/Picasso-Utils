package douglasspgyn.com.github.picassoutils.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by Douglas on 08/04/17.
 */

public class PicassoUtils {

    private static final float scale = 0.4f;
    private static final int radius = 8;

    public static void loadWithThumbnail(final Context context, final ImageView imageView, int placeholder, String lowQualityURL, final String highQualityURL) {
        Picasso.with(context).load(lowQualityURL).placeholder(placeholder).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Picasso.with(context).load(highQualityURL).placeholder(imageView.getDrawable()).into(imageView);
            }

            @Override
            public void onError() {

            }
        });
    }

    public static void loadAndBlur(Context context, final ImageView imageView, int placeholder, String imageURL) {
        loadAndBlur(context, imageView, placeholder, imageURL, scale, radius);
    }

    public static void loadAndBlur(Context context, final ImageView imageView, int placeholder, String imageURL, float scale, int radius) {
        Picasso.with(context).load(imageURL)
                .placeholder(placeholder)
                .transform(new BlurTransformation(scale, radius))
                .into(imageView);
    }

    private static class BlurTransformation implements Transformation {

        private float scale;
        private int radius;

        private BlurTransformation(float scale, int radius) {
            this.scale = scale;
            this.radius = radius;
        }

        @Override
        public Bitmap transform(Bitmap bitmap) {
            Bitmap blurredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            blurredBitmap = ImageUtils.fastblur(blurredBitmap, scale, radius);

            bitmap.recycle();

            return blurredBitmap;
        }

        @Override
        public String key() {
            return "blur";
        }
    }
}
