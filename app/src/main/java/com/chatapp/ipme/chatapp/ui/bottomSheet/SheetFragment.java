package com.chatapp.ipme.chatapp.ui.bottomSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.flipboard.bottomsheet.commons.BottomSheetFragment;

public class SheetFragment extends BottomSheetFragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_bottomsheet, container, false);
  }
}
