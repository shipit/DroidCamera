package com.shippingiseverything.droidcamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main extends Activity {
	
	private static final int GRAB_CAMERA_IMAGE = 100;
	private Bitmap _bitmap;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();
        
        // process for orientation change upon return from camera
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        
        if (extras != null && extras.containsKey("data")) {
        	displayImageFromCamera(extras);
        } else if (savedInstanceState != null) {
        	displayImageFromCamera(savedInstanceState);
        }
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	if (_bitmap != null) {
			ImageView iv = (ImageView) findViewById(R.id.iv_image_preview);
			iv.setImageBitmap(_bitmap);
    	}
    }
    
    @Override
    protected void onSaveInstanceState (Bundle outState) {
    	super.onSaveInstanceState(outState);
    	outState.putParcelable("data", _bitmap);
    }
    
    private void bindView() {
    	setContentView(R.layout.main);
    	
    	Button b = (Button) findViewById(R.id.btn_camera_button);
    	b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doTakePhotoAction();
			}
		});
    }
    
	private void doTakePhotoAction() {
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra("crop", "true");                   // crop post capture
		intent.putExtra("outputX",         512);           // width
		intent.putExtra("outputY",         512);           // height
		intent.putExtra("aspectX",         1);
		intent.putExtra("aspectY",         1);
		intent.putExtra("scale",           true);
		intent.putExtra("noFaceDetection", true);         
		intent.putExtra("setWallpaper",    false);
		intent.putExtra("return-data", true);
		
		startActivityForResult(intent, GRAB_CAMERA_IMAGE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		// quit early
		if (resultCode != RESULT_OK) {
			return;
		}
		
		if (requestCode == GRAB_CAMERA_IMAGE) {
			displayImageFromCamera(data.getExtras());
		}
	}
	
	private void displayImageFromCamera(Bundle extras) {
		_bitmap = extras.getParcelable("data");
		
		if (_bitmap != null) {
			ImageView iv = (ImageView) findViewById(R.id.iv_image_preview);
			iv.setImageBitmap(_bitmap);
		}
	}
}