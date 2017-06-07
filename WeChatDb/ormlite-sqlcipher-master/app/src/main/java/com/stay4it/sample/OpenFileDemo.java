// filename: OpenFileDemo.java
package com.stay4it.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.Dialog;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class OpenFileDemo extends Activity {

    static private int openfileDialogId = 0;
    String oldUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file_demo);

        findViewById(R.id.button_openfile).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        showDialog(openfileDialogId);
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(intent, 1);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            if (requestCode == 1) {
                Uri uri = data.getData();
                oldUrl = uri.getPath().toString();
                copyFile(oldUrl, OpenFileDemo.this.getFilesDir() + "/" + getFileName(oldUrl), getFileName(oldUrl));
            }
        }
    }

//    @Override
//    protected Dialog onCreateDialog(int id) {
//        if (id == openfileDialogId) {
//            Map<String, Integer> images = new HashMap<String, Integer>();
//            images.put(OpenFileDialog.sRoot, R.mipmap.filedialog_root);
//            images.put(OpenFileDialog.sParent, R.mipmap.filedialog_folder_up);
//            images.put(OpenFileDialog.sFolder, R.mipmap.filedialog_folder);
//            images.put("db", R.mipmap.filedialog_wavfile);
//            images.put(OpenFileDialog.sEmpty, R.mipmap.filedialog_root);
//            Dialog dialog = OpenFileDialog.createDialog(id, this, "选择文件",
//                    new CallbackBundle() {
//                        @Override
//                        public void callback(Bundle bundle) {
//                            String filepath = bundle.getString("path");
//                            String basePath = Environment
//                                    .getExternalStorageDirectory()
//                                    .getAbsolutePath();
//
//                            copyFile(basePath + filepath, basePath
//                                    + OpenFileDemo.this.getFilesDir() + "/" + getFileName(filepath), getFileName(filepath));
//
//                        }
//                    }, ".db;", images);
//            return dialog;
//        }
//        return null;
//    }

    public String getFileName(String pathandname) {

        int start = pathandname.lastIndexOf("/");
        int end = pathandname.length();
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }

    }

    public void copyFile(String oldPath, String newPath, String fileName) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            Log.d("yuy", "源文件" + oldPath);
            Log.d("yuy", "新文件" + newPath);
            Log.d("yuy", "文件名" + fileName);
            Log.d("yuy", oldfile.exists() + "源文件存在");
            if (oldfile.exists()) { // �
                InputStream inStream = new FileInputStream(oldPath); //
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //
                    Log.d("yuy", "" + bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                Intent intent = new Intent(OpenFileDemo.this, MainActivity.class);
                intent.putExtra("fileName", fileName);
                startActivity(intent);
                File newFile = new File(newPath);
                Log.d("yuy", newFile.exists() + "新文件存在");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }


    }


}
