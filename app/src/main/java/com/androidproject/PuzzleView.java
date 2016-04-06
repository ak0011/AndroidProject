package com.androidproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.util.List;


public class PuzzleView extends View {
    private Bitmap bitmap;
    private int width;
    private int height;
    private int rows;
    private int columns;
    private List<PuzzlePiece> puzzlePieces;

    public PuzzleView(Context context, Bitmap bitmap, int width, int height, int rows, int columns) {
        super(context);
        this.bitmap = bitmap;
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.columns = columns;
        Slicer slicer = new Slicer(bitmap,width,height,rows,columns);
        puzzlePieces=slicer.getPuzzlePieces();
        puzzlePieces.remove(puzzlePieces.size()-1); //remove the last piece from the puzzle
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (PuzzlePiece puzzlePiece:puzzlePieces) {
            canvas.drawBitmap(puzzlePiece.getBitmap(), puzzlePiece.getCoordinateX(), puzzlePiece.getCoordinateY(), null);
        }
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
                imageX = 0;
                imageY = 0;
                break;
            default:
                if(isImageTouched(motionEvent)){
                    imageX = motionEvent.getX() - image.getWidth() / 2;
                    imageY = motionEvent.getY() - image.getHeight() / 2;
                }
                break;
        }
        invalidate();
        return true;
    }

    private boolean isImageTouched(MotionEvent motionEvent){
        if(
                imageX<=motionEvent.getX() && motionEvent.getX()<=imageX+image.getWidth() &&
                imageY<=motionEvent.getY() && motionEvent.getY()<=imageY+image.getHeight()
                )
            return true;
        return false;
    }*/
}
