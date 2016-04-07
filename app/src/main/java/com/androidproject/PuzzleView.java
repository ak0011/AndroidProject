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
    private GameManager gameManager;
    private PuzzlePiece touchedPiece;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<PuzzlePiece> getPuzzlePieces() {
        return puzzlePieces;
    }

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
        gameManager=new GameManager(this);
        gameManager.calculateFreeDirections();
        touchedPiece=null;
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
            touchedPiece=null;
            for(PuzzlePiece puzzlePiece:puzzlePieces){
                if(puzzlePiece.wasTouched()){
                    Direction direction=puzzlePiece.getFreeDirection();
                    if(direction!=null){
                        switch (direction){
                            case TOP:
                                if(puzzlePiece.getY() < (PuzzleView.y+puzzlePiece.getCoordinateY()*puzzlePiece.getBitmap().getHeight())-puzzlePiece.getBitmap().getHeight()/2){
                                    puzzlePiece.slideUp();
                                    gameManager.setFreeBoxY(gameManager.getFreeBoxY() + 1);
                                    gameManager.calculateFreeDirections();
                                } else
                                    puzzlePiece.slideBackY();
                                break;
                            case RIGHT:
                                if(puzzlePiece.getX() > (PuzzleView.x+puzzlePiece.getCoordinateX()*puzzlePiece.getBitmap().getWidth())+puzzlePiece.getBitmap().getWidth()/2){
                                    puzzlePiece.slideRight();
                                    gameManager.setFreeBoxX(gameManager.getFreeBoxX() - 1);
                                    gameManager.calculateFreeDirections();
                                } else
                                    puzzlePiece.slideBackX();
                                break;
                            case LEFT:
                                if(puzzlePiece.getX()<(PuzzleView.x+puzzlePiece.getCoordinateX()*puzzlePiece.getBitmap().getWidth())-puzzlePiece.getBitmap().getWidth()/2){
                                    puzzlePiece.slideLeft();
                                    gameManager.setFreeBoxX(gameManager.getFreeBoxX() + 1);
                                    gameManager.calculateFreeDirections();
                                } else
                                    puzzlePiece.slideBackX();
                                break;
                            case DOWN:
                                if(puzzlePiece.getY() > (PuzzleView.y+puzzlePiece.getCoordinateY()*puzzlePiece.getBitmap().getHeight())+puzzlePiece.getBitmap().getHeight()/2){
                                    puzzlePiece.slideDown();
                                    gameManager.setFreeBoxY(gameManager.getFreeBoxY() - 1);
                                    gameManager.calculateFreeDirections();
                                } else
                                    puzzlePiece.slideBackY();
                                break;
                        }
                    }
                }
            }
        }else{
            for(PuzzlePiece puzzlePiece:puzzlePieces){
                if (puzzlePiece.isTouched(motionEvent) && (touchedPiece==null || touchedPiece==puzzlePiece)){
                    touchedPiece=puzzlePiece;
                    puzzlePiece.followTouch(motionEvent);
                }
            }
        }
        invalidate();
        return true;
    }

}
