/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package allenhu.app.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.ninegrid.NineGridView;
import com.squareup.picasso.Picasso;

import java.io.File;

import allenhu.app.R;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/5
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class GlideImageLoader implements ImageLoader, NineGridView.ImageLoader {
    private final RequestOptions options;

    public GlideImageLoader() {
        options = new RequestOptions();
        options.fitCenter();
        options.error(R.drawable.ic_error);
        options.placeholder(R.drawable.ic_default_image);
//        options.dontAnimate();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity).load(new File(path))
                .apply(options)//
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
//        Glide.with(activity).asBitmap().
    }

    @Override
    public void clearMemoryCache() {
    }

    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {


        Picasso.with(context).load(url)
                .placeholder(R.drawable.ic_default_image)
                .error(R.drawable.ic_error)
                .fit()
                .centerInside()
                .into(imageView);


//            Glide.with(context)
//                    .asBitmap()
//                    .load(url)
//                    .listener(new RequestListener<Bitmap>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
//                            Logger.d(e.getMessage());
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
//                            return false;
//                        }
//                    })
//                    .thumbnail(0.2f)
//                    .apply(options)//
//                    .into(imageView);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
