package com.chatapp.ipme.chatapp.ui.firstConnection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.ui.login.LogInFragment;

public class FirstConnectionFragment extends Fragment {

    public static FirstConnectionFragment newInstance() {
        return new FirstConnectionFragment();
    }

    private Button buttonTermServiceAgree;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_firstconnection, container, false);
        buttonTermServiceAgree = rootView.findViewById(R.id.buttonTermServiceAgree);
        //First connection Accept Terms and Conditions
        buttonTermServiceAgree.setOnClickListener(view -> {
            Fragment f = LogInFragment.newInstance();
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                    .replace(R.id.login_frame_container, f)
                    .addToBackStack(null)
                    .commit();
        });
        return rootView;
    }
}
