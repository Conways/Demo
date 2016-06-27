package com.conways.demo.home.firstmenu.bigsizeImage;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.conways.demo.R;
import com.conways.demo.base.BaseTitleActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by user on 2016/6/27.
 */
public class BigImageActivity extends BaseTitleActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_PICK_IMAGE = 0x001;

    private TextView tvSize;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_bigimage);
        super.onCreate(savedInstanceState);
        initTitle();
        init();
    }

    private void initTitle() {
        ivTitleBack.setOnClickListener(this);
        tvTitleTitle.setText(getText(R.string.bigimage_title));
    }

    private void init() {
        tvSize = (TextView) findViewById(R.id.activity_bigimage_size);
        ivImage = (ImageView) findViewById(R.id.activity_bigimage_image);
        ivImage.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != RESULT_CANCELED) {
            if (requestCode == REQUEST_CODE_PICK_IMAGE) {
                Uri uri = data.getData();
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(uri, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(column_index);
                ImageLoader.getInstance().displayImage(path, ivImage);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.title_style_child_right_text_back:
                end();
                break;
            case R.id.activity_bigimage_image:
                toImageAlum();
                break;
            default:
                break;

        }
    }

    private void toImageAlum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }
}
