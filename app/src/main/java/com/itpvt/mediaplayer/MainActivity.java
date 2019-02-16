package com.itpvt.mediaplayer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    RecyclerView.LayoutManager manager;

ArrayList<VideoModel> arraycycle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recycler);

        manager= new GridLayoutManager(getApplicationContext(),1);
recyclerView.setLayoutManager(manager);
arraycycle= new ArrayList<>();

fetchVideos();

        int Permission_All = 1;

        String[] Permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_SETTINGS};
        if(!hasPermissions(this, Permissions)){
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        }



    }


    public static boolean hasPermissions(Context context, String... permissions){

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && context!=null && permissions!=null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission)!= PackageManager.PERMISSION_GRANTED){
                    return  false;
                }
            }
        }
        return true;
    }




    private void fetchVideos() {

        Uri uri;
        Cursor cur;
        int column_index, column_folder, column_id,thumb;

        String absolute= null;

        uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;


        String[] protection={MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media._ID, MediaStore.Video.Thumbnails.DATA};


String overby= MediaStore.Images.Media.DATE_TAKEN;
cur= getApplicationContext().getContentResolver().query(uri,protection,null,null,overby+" DESC");



column_index=cur.getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE);

//column_folder=cur.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
//column_id= cur.getColumnIndexOrThrow(MediaStore.Video.Media._ID);

thumb=cur.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);


while(cur.moveToNext())
{

    absolute=cur.getString(column_index);
    VideoModel videoModel= new VideoModel();

    videoModel.setaBoolean(false);
    videoModel.setStrpath(absolute);
    videoModel.setStrthumb(cur.getString(thumb));


    arraycycle.add(videoModel);





}



VideoAdapter adapter= new VideoAdapter(getApplicationContext(),arraycycle,MainActivity.this);

recyclerView.setAdapter(adapter);



    }
}
