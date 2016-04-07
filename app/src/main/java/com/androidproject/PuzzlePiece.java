package com.androidproject;


import android.graphics.Bitmap;
import android.view.MotionEvent;

public class PuzzlePiece implements Slidable{
    private Bitmap bitmap;
    private int initialCoordinateX;
    private int initialCoordinateY;
    private int coordinateX;
    private int coordinateY;
    private int x;
    private int y;
    private boolean topFree;
    private boolean rightFree;
    private boolean leftFree;
    private boolean bottomFree;
    private boolean touched;

    //getters and setters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
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

    //constructor
    public PuzzlePiece(Bitmap bitmap, int coordinateX, int coordinateY) {
        this.bitmap = bitmap;
        this.initialCoordinateX=coordinateX;
        this.initialCoordinateY=coordinateY;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.x=PuzzleView.x+coordinateX*bitmap.getWidth();
        this.y=PuzzleView.y+coordinateY*bitmap.getHeight();
    }

    @Override
    public boolean isTouched(MotionEvent motionEvent) {
        if(
                x<=motionEvent.getX() && motionEvent.getX()<=x+bitmap.getWidth() &&
                y<=motionEvent.getY() && motionEvent.getY()<=y+bitmap.getHeight()
                ){
            touched=true;
            return true;
        }

        else
            return false;
    }

    @Override
    public boolean wasTouched() {
        if(touched){
            touched=false;
            return true;
        }
        else
            return false;
    }

    @Override
    public void followTouch(MotionEvent motionEvent) {
        x=(int)motionEvent.getX() - bitmap.getWidth() / 2;
        //y=(int)motionEvent.getY() - bitmap.getHeight() / 2;
    }

    @Override
    public void slideUp() {

    }

    @Override
    public void slideRight(){
        x=PuzzleView.x+(coordinateX+1)*bitmap.getWidth();
    }

    @Override
    public void slideLeft() {
        x=PuzzleView.x+(coordinateX-1)*bitmap.getWidth();
    }

    @Override
    public void slideDown() {

    }

    @Override
    public void slideBack() {
        x=PuzzleView.x+coordinateX*bitmap.getWidth();
    }
}
