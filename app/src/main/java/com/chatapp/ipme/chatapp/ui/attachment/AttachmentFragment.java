package com.chatapp.ipme.chatapp.ui.attachment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chatapp.ipme.chatapp.R;
import com.flipboard.bottomsheet.commons.BottomSheetFragment;

public class AttachmentFragment extends BottomSheetFragment {

  static final int REQUEST_IMAGE_CAPTURE = 1;
  private PackageManager packageManager;
  private PackageInfo packageInfo;

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

    packageManager = getActivity().getPackageManager();

    //Photo
    takePicture_Thumbnail = rootview.findViewById(R.id.takePicture_Thumbnail);
    takePicture_TextView = rootview.findViewById(R.id.takePicture_TextView);

    takePicture_TextView.setText("test");
    takePicture_Thumbnail.setImageResource(R.drawable.ic_action_photo);
    takePicture_Thumbnail.setBackgroundResource(R.drawable.circle_imageview_shape_green);
    takePicture_Thumbnail.setOnClickListener(v -> dispatchTakePictureIntent());

    //Gallery
    pickPhoto_Thumbnail = rootview.findViewById(R.id.pickPhoto_Thumbnail);
    pickPhoto_TextView = rootview.findViewById(R.id.pickPhoto_TextView);

    pickPhoto_TextView.setText("test");
    pickPhoto_Thumbnail.setImageResource(R.drawable.ic_gallery);
    pickPhoto_Thumbnail.setBackgroundResource(R.drawable.circle_imageview_shape_green);
    pickPhoto_Thumbnail.setOnClickListener(v -> dispatchPickPhotoIntent());

    //Video
    pickVideo_Thumbnail = rootview.findViewById(R.id.pickVideo_Thumbnail);
    pickVideo_TextView = rootview.findViewById(R.id.pickVideo_TextView);

    pickVideo_TextView.setText("test");
    pickVideo_Thumbnail.setImageResource(R.drawable.ic_video);
    pickVideo_Thumbnail.setBackgroundResource(R.drawable.circle_imageview_shape_green);
    pickVideo_Thumbnail.setOnClickListener(v -> dispatchTakeVideoIntent());

    return rootview;
  }

  private void dispatchTakePictureIntent() {
    Toast.makeText(getContext(), "dispatchTakePictureIntent", Toast.LENGTH_LONG).show();
  }

  private void dispatchPickPhotoIntent() {
    Toast.makeText(getContext(), "dispatchPickPhotoIntent", Toast.LENGTH_LONG).show();
  }

  private void dispatchTakeVideoIntent() {
    Toast.makeText(getContext(), "dispatchTakeVideoIntent", Toast.LENGTH_LONG).show();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {

  }
}
