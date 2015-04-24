package com.temi4hik.interactivestory.model;

/**
 * Created by Artem on 19.04.15.
 */
public class Page {

    private int mImageId;
    private String mText;
    private Choice mChoice1;
    private Choice mChoice2;

    public Page(int imageId, String text, Choice choice1, Choice choice2) {
        mImageId = imageId;
        mText = text;
        mChoice1 = choice1;
        mChoice2 = choice2;

    }

    public Page(int imageId, String text) {
        mText = text;
        mImageId = imageId;
        mChoice1 = null;
        mChoice2 = null;
    }



    public int getImageId() {
        return mImageId;
    }

    public String getText() {
        return mText;
    }

    public Choice getChoice1() {
        return mChoice1;
    }

    public Choice getChoice2() {
        return mChoice2;
    }

    public boolean isFinal() {
        return mChoice1 == null && mChoice2 == null;
    }

}
