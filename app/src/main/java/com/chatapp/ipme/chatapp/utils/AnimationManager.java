package com.chatapp.ipme.chatapp.utils;

import android.animation.AnimatorSet;
import android.widget.FrameLayout;

public class AnimationManager {

  public static void frameTransition(FrameLayout frameLayout, AnimatorSet flip) {
    frameLayout.removeAllViews();
    flip.setTarget(frameLayout);
    flip.start();
  }
}
