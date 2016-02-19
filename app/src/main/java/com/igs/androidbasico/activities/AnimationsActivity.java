package com.igs.androidbasico.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.igs.androidbasico.R;

/**
 * Created by isgarsi on 19/2/16.
 */
public class AnimationsActivity extends AppCompatActivity implements View.OnClickListener{

        private ImageView image;
        private Button butRotate;
        private Button butTranslate;
        private Button butAlpha;
        private Button butScale;
        private Button butMix;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_animation);

            image = (ImageView) findViewById(R.id.imageAnimation);
            butRotate = (Button) findViewById(R.id.butRotate);
            butTranslate = (Button) findViewById(R.id.butTranslate);
            butAlpha = (Button) findViewById(R.id.butAlpha);
            butScale = (Button) findViewById(R.id.butScale);
            butMix = (Button) findViewById(R.id.butMix);

            butRotate.setOnClickListener(this);
            butTranslate.setOnClickListener(this);
            butAlpha.setOnClickListener(this);
            butScale.setOnClickListener(this);
            butMix.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Animation animation = null;

            switch(v.getId()){
                case R.id.butAlpha:
                    animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                    break;
                case R.id.butRotate:
                    animation = AnimationUtils.loadAnimation(this,R.anim.rotate);
                    break;
                case R.id.butScale:
                    animation = AnimationUtils.loadAnimation(this,R.anim.scale);
                    break;
                case R.id.butTranslate:
                    animation = AnimationUtils.loadAnimation(this,R.anim.translate);
                    break;
                case R.id.butMix:
                    animation = AnimationUtils.loadAnimation(this,R.anim.mix);
                    break;
            }

            if(animation != null)
                image.startAnimation(animation);
        }
}
