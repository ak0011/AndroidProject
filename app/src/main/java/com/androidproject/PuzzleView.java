package com.androidproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;


public class PuzzleView extends View {
    private Bitmap bitmap;
    private int width;
    private int height;
    private int rows;
    private int columns;
    public static int x;
    public static int y;
    private List<PuzzlePiece> puzzlePieces;

    public PuzzleView(Context context, Bitmap bitmap, int width, int height, int rows, int columns,int x,int y) {
        super(context);
        this.bitmap = bitmap;
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.columns = columns;
        this.x=x;
        this.y=y;
        ImageSlicer slicer = new ImageSlicer(bitmap,width,height,rows,columns);
        puzzlePieces=slicer.getPuzzlePieces();
        puzzlePieces.remove(puzzlePieces.size() - 1); //remove the last piece from the puzzle
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (PuzzlePiece puzzlePiece:puzzlePieces) {
            canvas.drawBitmap(puzzlePiece.getBitmap(), puzzlePiece.getX(), puzzlePiece.getY(), null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if(motionEvent.getAction()==MotionEvent.ACTION_UP){
            for(PuzzlePiece puzzlePiece:puzzlePieces){
                if(puzzlePiece.wasTouched()){
                    if(puzzlePiece.getX()>(PuzzleView.x+puzzlePiece.getCoordinateX()*puzzlePiece.getBitmap().getWidth())+puzzlePiece.getBitmap().getWidth()/2)
                        puzzlePiece.slideRight();
                    else
                        puzzlePiece.slideBack();
                }
            }
        }else{
            for(PuzzlePiece puzzlePiece:puzzlePieces){
                if(puzzlePiece.isTouched(motionEvent)){
                    puzzlePiece.followTouch(motionEvent);
                }
            }
        }
        invalidate();
        return true;
    }

}
