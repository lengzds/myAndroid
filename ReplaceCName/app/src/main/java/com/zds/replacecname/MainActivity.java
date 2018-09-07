package com.zds.replacecname;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static String name;
    private static EditText nameInput;
    private static EditText before;
    private static EditText after;
    private TextView btext;
    private TextView atext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button);
        nameInput = (EditText) findViewById(R.id.editText);
        btext = findViewById(R.id.beforetextView);
        atext = findViewById(R.id.aftertextView);
        before = findViewById(R.id.before);
        after = findViewById(R.id.after);
        before.setText("第([0-9]{1,4})");
        after.setText("第$1章 ");
        checkPermission();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new myThread().start();
            }
        });

    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("ok", "checkPermission: 已经授权！");
        }
    }

    class myThread extends Thread {
        @Override
        public void run() {
            name = nameInput.getText().toString();

            File f = new File("/storage/emulated/0/Download/" + name + ".txt");
            String body = FileWork.readFileToString(f);

            Message mb = new Message();
            mb.what = 100;
            mb.obj = body.substring(0, 10000);
            mHandler.sendMessage(mb);

            body = body.replaceAll(before.getText().toString(), after.getText().toString());
            Message ma = new Message();
            ma.what = 200;
            ma.obj = body.substring(0, 10000);
            mHandler.sendMessage(ma);
            FileWork.SaveFile("/storage/emulated/0/Download/" + name + "_new.txt", body);
            f.delete();
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                btext.setText((String) msg.obj);
            }
            if (msg.what == 200) {
                atext.setText((String) msg.obj);
            }
        }
    };

}
