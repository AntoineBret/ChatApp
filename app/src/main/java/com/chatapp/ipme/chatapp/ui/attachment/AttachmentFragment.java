package com.chatapp.ipme.chatapp.ui.attachment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chatapp.ipme.chatapp.R;
import com.flipboard.bottomsheet.commons.BottomSheetFragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AttachmentFragment extends BottomSheetFragment {

    private static final int REQUEST_STORAGE = 0;
    private static final int REQUEST_IMAGE_CAPTURE = REQUEST_STORAGE + 1;
    private static final int REQUEST_LOAD_IMAGE = REQUEST_IMAGE_CAPTURE + 1;
    private Uri cameraImageUri = null;

    //Photo
    private ImageView takePicture_Thumbnail;
    private TextView takePicture_TextView;

    //Gallery
    private ImageView pickPhoto_Thumbnail;
    private TextView pickPhoto_TextView;

    //Video
    private ImageView pickVideo_Thumbnail;
    private TextView pickVideo_TextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_attachment, container, false);

        //Photo
        takePicture_Thumbnail = rootview.findViewById(R.id.takePicture_Thumbnail);
        takePicture_TextView = rootview.findViewById(R.id.takePicture_TextView);

        takePicture_TextView.setText("Photo");
        takePicture_Thumbnail.setImageResource(R.drawable.ic_action_photo);
        takePicture_Thumbnail.setBackgroundResource(R.drawable.circle_imageview_shape_green);
        takePicture_Thumbnail.setOnClickListener(v -> dispatchTakePictureIntent());

        //Gallery
        pickPhoto_Thumbnail = rootview.findViewById(R.id.pickPhoto_Thumbnail);
        pickPhoto_TextView = rootview.findViewById(R.id.pickPhoto_TextView);

        pickPhoto_TextView.setText("Gallery");
        pickPhoto_Thumbnail.setImageResource(R.drawable.ic_gallery);
        pickPhoto_Thumbnail.setBackgroundResource(R.drawable.circle_imageview_shape_green);
        pickPhoto_Thumbnail.setOnClickListener(view -> startActivityForResult(createPickIntent(), REQUEST_LOAD_IMAGE));

        //Video
        pickVideo_Thumbnail = rootview.findViewById(R.id.pickVideo_Thumbnail);
        pickVideo_TextView = rootview.findViewById(R.id.pickVideo_TextView);

        pickVideo_TextView.setText("Video");
        pickVideo_Thumbnail.setImageResource(R.drawable.ic_video);
        pickVideo_Thumbnail.setBackgroundResource(R.drawable.circle_imageview_shape_green);
        pickVideo_Thumbnail.setOnClickListener(v -> dispatchTakeVideoIntent());

        return rootview;
    }

    private Intent createCameraIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            return takePictureIntent;
        } else {
            return null;
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = createCameraIntent();
        if (takePictureIntent != null) {
            try {
                File imageFile = createImageFile();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } catch (IOException e) {
                genericError("Could not create imageFile for camera");
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        cameraImageUri = Uri.fromFile(imageFile);
        return imageFile;
    }

    private Intent createPickIntent() {
        Intent picImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (picImageIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            return picImageIntent;
        } else {
            return null;
        }
    }

    private void dispatchTakeVideoIntent() {
        Toast.makeText(getContext(), "dispatchTakeVideoIntent", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri selectedImage = null;
            if (requestCode == REQUEST_LOAD_IMAGE && data != null) {
                selectedImage = data.getData();
                if (selectedImage == null) {
                    genericError();
                }
            } else if (requestCode == REQUEST_IMAGE_CAPTURE) {
                selectedImage = cameraImageUri;
            }

            if (selectedImage != null) {
                showSelectedImage(selectedImage);
            } else {
                genericError();
            }
        }
    }

    private void showSelectedImage(Uri selectedImageUri) {

        //todo Send Image to back
//        selectedImage.setImageDrawable(null);
//        Glide.with(this)
//                .load(selectedImageUri)
//                .into(selectedImage);
    }

    private void genericError() {
        genericError(null);
    }

    private void genericError(String message) {
        Toast.makeText(getContext(), message == null ? "Something went wrong." : message, Toast.LENGTH_SHORT).show();
    }
}
