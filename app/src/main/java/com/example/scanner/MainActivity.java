package com.example.scanner;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCamera(View v)
    {
        int REQUEST_CODE = 99;
        int preference = ScanConstants.OPEN_CAMERA;
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void openGallery(View v)
    {
        int REQUEST_CODE = 99;
        int preference = ScanConstants.OPEN_MEDIA;
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 99 && resultCode == Activity.RESULT_OK)
        {
            Uri uri = data.getExtras() .getParcelable(ScanConstants.SCANNED_RESULT);
            Bitmap bitmap = null;
            try
            {
               bitmap = MediaStore.Images. Media. getBitmap(getContentResolver(), uri);
               getContentResolver(). delete(uri, null, null);
               //scannedImageView. setimageBitmap(bitmap);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}