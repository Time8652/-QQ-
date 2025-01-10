package com.example.myqq;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myqq.MyAdapter.IntelligentAdapter;
import com.example.myqq.News.IntelligentNew;

import java.io.File;
import java.util.ArrayList;

public class IntelligentActivity extends AppCompatActivity {

    private String path;
    private final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1, REQUEST_CODE_PHOTO = 0;
    private ArrayList<IntelligentNew> intelligentNewArrayList;
    private RecyclerView recyclerView;
    private ImageView imageView_intelligent;
    private EditText editText;
    private TextView send, intelligent_image, textView;
    private File file = null;
    private Uri uri = null;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligent);
        textView = findViewById(R.id.textView);
        textView.setText(QQFragment.getTextView());
        imageView_intelligent = findViewById(R.id.imageView_intelligent);
        intelligent_image = findViewById(R.id.intelligent_image);
        editText = findViewById(R.id.editText);
        send = findViewById(R.id.send);
        recyclerView = findViewById(R.id.recyclerView);
        intelligentNewArrayList = new ArrayList<>();
        IntelligentAdapter intelligentAdapter = new IntelligentAdapter(IntelligentActivity.this, intelligentNewArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(IntelligentActivity.this));
        recyclerView.setAdapter(intelligentAdapter);
        IntelligentNew intelligentNew = new IntelligentNew(true, QQFragment.getText(), null);
        intelligentNewArrayList.add(intelligentNew);
        intelligentAdapter.notifyItemInserted(intelligentNewArrayList.size() - 1);
        recyclerView.scrollToPosition(intelligentNewArrayList.size() - 1); // Scroll to the new item
        imageView_intelligent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查外部存储空间的权限
                if (ContextCompat.checkSelfPermission(IntelligentActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(IntelligentActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_PHOTO);
                }
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFirst) {
                    editText.setText("");
                    isFirst = !isFirst;
                }
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                IntelligentNew intelligentNew = new IntelligentNew(false, text, path);
                intelligentNewArrayList.add(intelligentNew);
                intelligentAdapter.notifyItemInserted(intelligentNewArrayList.size() - 1);
                recyclerView.scrollToPosition(intelligentNewArrayList.size() - 1); // Scroll to the new item
                editText.setText("");
                intelligent_image.setText("");
                path = null;
//                //以下为使用OKhttp实现前后端互通的示例
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            OkHttpClient client = new OkHttpClient();//创建http客户端
//                            MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);//通过表单上传文件
//                            if (file != null) {
//                                RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);//上传的文件以及类型
//                                requestBody.addFormDataPart("file", file.getName(), fileBody)
//                                        .addFormDataPart("prompt", text);
//                                Request request = new Request.Builder()
//                                        .url("http://" + "192.168.18.168" + ":8080/ai/muti")
//                                        .post(requestBody.build())
//                                        .header("Authorization", null)
//                                        .build();//创造http请求
//                                Response response = client.newCall(request).execute();//执行发送的指令
//                                String responseData = response.body().string();//获取后端返回过来的json格式的结果
//                                JSONObject jsonObject = new JSONObject(responseData);
//                                String string = jsonObject.getString("data");
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        IntelligentNew intelligentNew = new IntelligentNew(true, string, path);
//                                        intelligentNewArrayList.add(intelligentNew);
//                                        intelligentAdapter.notifyItemInserted(intelligentNewArrayList.size() - 1);
//                                        recyclerView.scrollToPosition(intelligentNewArrayList.size() - 1); // Scroll to the new item
//                                    }
//                                });
//                            }
//                            else {
//                                requestBody.addFormDataPart("prompt", text);
//                                Request request = new Request.Builder()
//                                        .url("http://" + LoginActivity.getUrl() + ":8080/teacher/homework")
//                                        .post(requestBody.build())
//                                        .header("Authorization", key)
//                                        .build();//创造http请求
//                                Response response = client.newCall(request).execute();//执行发送的指令
//                                String responseData = response.body().string();//获取后端返回过来的json格式的结果
//                                JSONObject jsonObject = new JSONObject(responseData);
//                                String string = jsonObject.getString("data");
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        IntelligentNew intelligentNew = new IntelligentNew(true, string, null);
//                                        intelligentNewArrayList.add(intelligentNew);
//                                        intelligentAdapter.notifyItemInserted(intelligentNewArrayList.size() - 1);
//                                        recyclerView.scrollToPosition(intelligentNewArrayList.size() - 1); // Scroll to the new item
//                                    }
//                                });
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(IntelligentActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    }
//                }).start();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限获取失败！无法使用本功能！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PHOTO && resultCode == RESULT_OK) {
            uri = data.getData();
            if (Build.VERSION.SDK_INT < 19) {
                path = getImagePath(uri, null);
            } else {
                if (DocumentsContract.isDocumentUri(this, uri)) {
                    String documentId = DocumentsContract.getDocumentId(uri);
                    if (TextUtils.equals(uri.getAuthority(), "com.android.providers.media.documents")) {
                        String id = documentId.split(":")[1];
                        String selection = MediaStore.Images.Media._ID + "=" + id;
                        path = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                    } else if (TextUtils.equals(uri.getAuthority(), "com.android.providers.downloads.documents")) {
                        Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                        path = getImagePath(contentUri, null);
                    }
                } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                    path = getImagePath(uri, null);
                } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                    path = uri.getPath();
                }
            }
            if (path != null) {
                file = new File(path);
                intelligent_image.setText(path);
            }
        }
    }

    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String image_path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                image_path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
            return image_path;
        }
        return null;
    }
}