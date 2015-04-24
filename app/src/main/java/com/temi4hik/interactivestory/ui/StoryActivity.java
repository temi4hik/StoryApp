package com.temi4hik.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.temi4hik.interactivestory.R;
import com.temi4hik.interactivestory.model.Page;
import com.temi4hik.interactivestory.model.Story;


public class StoryActivity extends ActionBarActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private final Story mStory = Story.getStory();
    private Page mCurrentPage;


    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;


    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        mName = intent.getStringExtra("name");

        if (mName == null) {
            mName = "Friend";
        }

        mImageView = (ImageView) findViewById(R.id.storyImageView);
        mTextView = (TextView) findViewById(R.id.storyTextView);
        mChoice1 = (Button) findViewById(R.id.choiceButton1);
        mChoice2 = (Button) findViewById(R.id.choiceButton2);

        loadPage(0);


    }

    private void loadPage(int choice) {
        mCurrentPage = mStory.getPage(choice);

        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
        mImageView.setImageDrawable(drawable);

        String pageText = mCurrentPage.getText();

        // Add the name if placeholder included. Won't add if no placeholder
        pageText = String.format(pageText, mName);

        mTextView.setText(pageText);


        if (mCurrentPage.isFinal()) {
            mChoice1.setVisibility(View.INVISIBLE);
            mChoice2.setText("PLAY AGAIN");

            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        } else {
            mChoice1.setText(mCurrentPage.getChoice1().getText());
            mChoice2.setText(mCurrentPage.getChoice2().getText());


            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }

    }


}
