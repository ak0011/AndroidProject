package com.androidproject;


import android.graphics.Bitmap;

public class PuzzlePiece implements Slidable{
    private Bitmap bitmap;
    private final int initialX;
    private final int initialY;
    private int x;
    private int y;
    private boolean topFree;
    private boolean rightFree;
    private boolean leftFree;
    private boolean bottomFree;

    //getters and setters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isTopFree() {
        return topFree;
    }

    public void setTopFree(boolean topFree) {
        this.topFree = topFree;
    }

    public boolean isRightFree() {
        return rightFree;
    }

    public void setRightFree(boolean rightFree) {
        this.rightFree = rightFree;
    }

    public boolean isLeftFree() {
        return leftFree;
    }

    public void setLeftFree(boolean leftFree) {
        this.leftFree = leftFree;
    }

    public boolean isBottomFree() {
        return bottomFree;
    }

    public void setBottomFree(boolean bottomFree) {
        this.bottomFree = bottomFree;
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    //get real coordinates of the piece
    public int getCoordinateX(){
        return bitmap.getWidth()*x;
    }

    public int getCoordinateY(){
        return bitmap.getHeight()*y;
    }

    //constructor
    public PuzzlePiece(Bitmap bitmap, int initialX, int initialY, int x, int y) {
        this.bitmap = bitmap;
        this.initialX = initialX;
        this.initialY = initialY;
        this.x = x;
        this.y = y;
    }

    @Override
    public void followTouch() {

    }

    @Override
    public void slideUp() {

    }

    @Override
    public void slideRight() {

    }

    @Override
    public void slideLeft() {

    }

    @Override
    public void slideDown() {

    }
}
