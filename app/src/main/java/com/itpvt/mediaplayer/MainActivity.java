package com.itpvt.mediaplayer;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
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

        manager= new GridLayoutManager(getApplicationContext(), 2);
recyclerView.setLayoutManager(manager);
arraycycle= new ArrayList<>();

fetchVideos();





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



column_index=cur.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

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
