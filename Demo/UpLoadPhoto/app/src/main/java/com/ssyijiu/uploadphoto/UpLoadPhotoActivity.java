package com.ssyijiu.uploadphoto;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class UpLoadPhotoActivity extends Activity {
    public static final int PHOTO_TAKE = 1;    // 拍照
    public static final int PHOTO_ALBUM = 2;   // 图库
    public static final int PHOTO_CROP = 3;    // 剪裁

    public static final String IMAGE_UNSPECIFIED = "image/*";


    @InjectView(R.id.photo)
    ImageView photoView;
    @InjectView(R.id.take)
    Button take;
    @InjectView(R.id.album)
    Button album;
    private File photoFile;
    private Uri photoUri;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


        // 相册
        album.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                getAlbumPhoto();
            }
        });

        // 拍照
        take.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                takePhoto();
            }
        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // 拍照 data 返回的是 null，根据图片保存地址加载图片
        if (requestCode == PHOTO_TAKE) {
            if(resultCode == Activity.RESULT_OK) {

                // 进行图片剪裁
                cropPhoto(Uri.fromFile(photoFile));

                // 直接加载图片
                // Picasso.with(this).load(photoFile).into(photoView);
            }

        }

        // 读取相册图片
        if (requestCode == PHOTO_ALBUM) {
            if(resultCode == Activity.RESULT_OK) {
                if(data != null) {
                    String photoUrl = data.getData().toString();
                    cropPhoto(Uri.parse(photoUrl));
                    // Picasso.with(this).load(photoUrl).into(photoView);
                }
            }

        }


        if (requestCode == PHOTO_CROP) {
            if(resultCode == Activity.RESULT_OK) {
                if(photoUri != null) {
                    Picasso.with(this).load(photoUri).into(photoView);
                }

                // return-data 为 true 加载 “缩略图”
                // Bitmap bitmap = data.getExtras().getParcelable("data");
                // photoView.setImageBitmap(bitmap);
            }

        }
    }


    /**
     * 拍照
     */
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getOutputPhotoFileUri());
        startActivityForResult(intent, PHOTO_TAKE);
    }

    /**
     * 从相册读取图片
     */
    private void getAlbumPhoto() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK); // ACTION_GET_CONTENT
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
        startActivityForResult(intent, PHOTO_ALBUM);
    }

    /**
     * 进行图片剪裁
     * @param uri 要剪裁图片的 Uri
     */
    public void cropPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        // 传入 图片uri 及 协议
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);

        // 发送剪裁信号
        intent.putExtra("crop", "true");


        // 这两行代码的效果不知道
        intent.putExtra("scale", true);
        // intent.putExtra("circleCrop", true);

        // 剪裁框的宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // 裁剪图片宽高, 越大图片质量越好，太大 OOM
        intent.putExtra("outputX", 1024);
        intent.putExtra("outputY", 1024);

        //不启用人脸识别
        intent.putExtra("noFaceDetection", true);

        // 是否将数据在 Bitmap 中返回
        // 注意：在 Bitmap 中返回, Android 为了防止 OOM 会返回一个“缩略图”
        intent.putExtra("return-data", false);

        // 设置图片返回格式 JPG
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());


        photoUri = getOutputPhotoFileUri();
        // 设置图片返回地址
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

        startActivityForResult(intent, PHOTO_CROP);

    }

    /** Create a file Uri for saving an image */
    private Uri getOutputPhotoFileUri(){
        return Uri.fromFile(getOutputPhotoFile());
    }

    /** Create a File for saving an image */
    private File getOutputPhotoFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "UpLoadPhoto");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("UpLoadPhoto", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        photoFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ time + ".jpg");

        return photoFile;
    }
}
