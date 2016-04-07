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
    private Direction freeDirection;
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

    public Direction getFreeDirection() {
        return freeDirection;
    }

    public void setFreeDirection(Direction freeDirection) {
        this.freeDirection = freeDirection;
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
        if(freeDirection!=null){
            switch(freeDirection){
                case TOP:
                    if(PuzzleView.y+(coordinateY-1)*bitmap.getHeight()+bitmap.getHeight()/2<=motionEvent.getY() &&
                            motionEvent.getY()<=PuzzleView.y+coordinateY*bitmap.getHeight()+bitmap.getHeight()/2)
                        y=(int)motionEvent.getY() - bitmap.getHeight() / 2;
                    break;
                case RIGHT:
                    if(PuzzleView.x+coordinateX*bitmap.getWidth()+bitmap.getWidth()/2<=motionEvent.getX() &&
                            motionEvent.getX()<=PuzzleView.x+(coordinateX+1)*bitmap.getWidth()+bitmap.getWidth()/2)
                        x=(int)motionEvent.getX() - bitmap.getWidth() / 2;
                    break;
                case LEFT:
                    if(PuzzleView.x+(coordinateX-1)*bitmap.getWidth()+bitmap.getWidth()/2<=motionEvent.getX() &&
                            motionEvent.getX()<=PuzzleView.x+(coordinateX)*bitmap.getWidth()+bitmap.getWidth()/2)
                        x=(int)motionEvent.getX() - bitmap.getWidth() / 2;
                    break;
                case DOWN:
                    if(PuzzleView.y+coordinateY*bitmap.getHeight()+bitmap.getHeight()/2<=motionEvent.getY() &&
                            motionEvent.getY()<=PuzzleView.y+(coordinateY+1)*bitmap.getHeight()+bitmap.getHeight()/2)
                        y=(int)motionEvent.getY() - bitmap.getHeight() / 2;
                    break;
            }
        }
    }

    @Override
    public void slideUp() {
        y=PuzzleView.y+(coordinateY-1)*bitmap.getHeight();
        coordinateY--;
    }

    @Override
    public void slideRight(){
        x=PuzzleView.x+(coordinateX+1)*bitmap.getWidth();
        coordinateX++;
    }

    @Override
    public void slideLeft() {
        x=PuzzleView.x+(coordinateX-1)*bitmap.getWidth();
        coordinateX--;
    }

    @Override
    public void slideDown() {
        y=PuzzleView.y+(coordinateY+1)*bitmap.getHeight();
        coordinateY++;
    }

    @Override
    public void slideBackX() {
        x=PuzzleView.x+coordinateX*bitmap.getWidth();
    }

    @Override
    public void slideBackY() {
        y=PuzzleView.y+coordinateY*bitmap.getHeight();
    }
}

