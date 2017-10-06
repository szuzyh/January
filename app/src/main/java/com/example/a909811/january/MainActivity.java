 package com.example.a909811.january;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a909811.january.model.JanuartAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

 public class MainActivity extends AppCompatActivity {

     private ListView listView;
     private JanuartAdapter myAdapter;
     private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
     private AlertDialog dialog;
     private ArrayList<HashMap<String,Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this,permissions[0]) != PackageManager.PERMISSION_GRANTED){
                showDialogTipUserRequestPermission();
            }

        }
        listView = (ListView) findViewById(R.id.januaryList);
        data = getData();
        myAdapter = new JanuartAdapter(data,this);
        listView.setAdapter(myAdapter);

    }

     private ArrayList<HashMap<String,Object>> getData() {
         return new ArrayList<HashMap<String,Object>>();
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         menu.add("add");
         return super.onCreateOptionsMenu(menu);
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getTitle().toString()){
             case "add":
                 //listView add one
                 HashMap<String,Object> tmp = new HashMap<>();
                 tmp.put("message","heiheihei");
                 tmp.put("date","103");
                 data.add(tmp);
                 myAdapter.notifyDataSetChanged();
                 break;
         }

         return super.onOptionsItemSelected(item);
     }

     private void showDialogTipUserRequestPermission() {
         new AlertDialog.Builder(this)
                 .setTitle("存储权限不可用")
                 .setMessage("需要权限")
                 .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         startRequestPermission();
                     }
                 })
                 .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         finish();
                     }
                 }).setCancelable(false).show();
     }

     private void startRequestPermission() {
         ActivityCompat.requestPermissions(this,permissions,321);
     }

     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);

         if (requestCode == 321){
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                 if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
                     boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                     if (!b){
                         showDialogTipUserGoToAppSetting();
                     }else {
                         finish();
                     }
                 }
             }
         }
     }

     private void showDialogTipUserGoToAppSetting() {
         dialog = new AlertDialog.Builder(this)
                 .setTitle("存储权限不可用")
                 .setMessage("请在-应用设置-权限-中，允许应用使用存储权限赖保存用户数据")
                 .setPositiveButton("立刻开启", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         goToAppSetting();
                     }
                 })
                 .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         finish();
                     }
                 }).setCancelable(false).show();
     }

     private void goToAppSetting() {
         Intent intent = new Intent();
         intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
         Uri uri = Uri.fromParts("package",getPackageName(),null);
         intent.setData(uri);
         startActivityForResult(intent,123);
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == 123){
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                 if(ContextCompat.checkSelfPermission(this,permissions[0]) != PackageManager.PERMISSION_GRANTED){
                     showDialogTipUserGoToAppSetting();
                 }else {
                     if (dialog != null && dialog.isShowing()){
                         dialog.dismiss();
                     }
                     Toast.makeText(this,"权限获取成功",Toast.LENGTH_SHORT).show();
                 }
             }
         }
     }
 }
