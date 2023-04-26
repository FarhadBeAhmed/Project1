package com.example.project1.view.ui.MobileRecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.project1.view.ui.DashboardActivity;
import com.example.project1.R;
import com.example.project1.view.ui.allFragmnts.FlexiLoadFragment;

public class FlexiloadActivity extends AppCompatActivity {
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexiload);

        Bundle bundle = getIntent().getExtras();
        if( bundle!=null){
            number =  bundle.getString("conVal");
            Bundle bundle1= new Bundle();
            bundle1.putString("arg", number);
            FlexiLoadFragment flexiLoadFragment = new FlexiLoadFragment();
            flexiLoadFragment.setArguments(bundle1);
            getSupportFragmentManager().beginTransaction().add(R.id.id_fragment_Container, flexiLoadFragment, "flexiLoadFragment").addToBackStack("flexiLoadFragment").commit();

        }else{


            FlexiLoadFragment flexiLoadFragment = new FlexiLoadFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.id_fragment_Container, flexiLoadFragment, "flexiLoadFragment").addToBackStack("flexiLoadFragment").commit();

        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            startActivity(new Intent(this, DashboardActivity.class));
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}