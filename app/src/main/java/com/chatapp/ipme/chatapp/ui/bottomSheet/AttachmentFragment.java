package com.chatapp.ipme.chatapp.ui.bottomSheet;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;
import com.flipboard.bottomsheet.commons.BottomSheetFragment;

public class AttachmentFragment extends BottomSheetFragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private PackageManager packageManager;
    private PackageInfo packageInfo;

    //Photo
    private LinearLayout takePicture_ll;
    private ImageView takePicture_Thumbnail;
    private TextView takePicture_TextView;

    //Gallery
    private LinearLayout pickPhoto_ll;
    private ImageView pickPhoto_Thumbnail;
    private TextView pickPhoto_TextView;

    //Video
    private LinearLayout pickVideo_ll;
    private ImageView pickVideo_Thumbnail;
    private TextView pickVideo_TextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_attachment, container, false);

        packageManager = getActivity().getPackageManager();

        //Photo
        takePicture_Thumbnail = rootview.findViewById(R.id.takePicture_Thumbnail);
        takePicture_TextView = rootview.findViewById(R.id.takePicture_TextView);

        takePicture_Thumbnail.setImageResource(R.drawable.ic_action_photo);
        takePicture_Thumbnail.setBackgroundResource(R.drawable.circle_imageview_shape_green);
        takePicture_TextView.setText("test");

        //Gallery
        pickPhoto_Thumbnail = rootview.findViewById(R.id.pickPhoto_Thumbnail);
        pickPhoto_TextView = rootview.findViewById(R.id.pickPhoto_TextView);

        pickPhoto_Thumbnail.setImageResource(R.drawable.ic_gallery);
        pickPhoto_Thumbnail.setBackgroundResource(R.drawable.button_roundcorner_shape);
        pickPhoto_TextView.setText("test");

        //Video
        pickVideo_Thumbnail = rootview.findViewById(R.id.pickVideo_Thumbnail);
        pickVideo_TextView = rootview.findViewById(R.id.pickVideo_TextView);

        pickVideo_Thumbnail.setImageResource(R.drawable.ic_video);
        pickVideo_Thumbnail.setBackgroundResource(R.drawable.button_roundcorner_shape);
        pickVideo_TextView.setText("test");

        return rootview;
    }

    private void dispatchTakePictureIntent() {

    }

    private void dispatchPickPhotoIntent() {

    }

    private void dispatchTakeVideoIntent() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}