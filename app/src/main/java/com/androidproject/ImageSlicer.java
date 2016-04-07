package com.androidproject;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class ImageSlicer {
    private Bitmap source;
    private int rows; //number of rows the source image should be sliced into
    private int columns; //number of columns the source image should be sliced into
    private int width; //width of the puzzle image
    private int height; //height of the puzzle image

    public ImageSlicer(Bitmap source, int width, int height, int rows, int columns) {
        this.source = source;
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.columns = columns;
    }

    public ArrayList<PuzzlePiece> getPuzzlePieces(){
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(source, width, height, false);
        ArrayList<PuzzlePiece> puzzlePieces=new ArrayList<>();
        int piecesWidth=width/columns;
        int piecesHeight=height/rows;
        int pieceX=0;
        int pieceY=0;
        for (int r=0;r<rows;r++){
            for (int c=0;c<columns;c++){
                PuzzlePiece puzzlePiece=new PuzzlePiece(Bitmap.createBitmap(resizedBitmap,pieceX,pieceY,piecesWidth,piecesHeight),r,c);
                puzzlePieces.add(puzzlePiece);
                pieceX+=piecesWidth;
            }
            pieceX=0;
            pieceY+=piecesHeight;
        }
        return puzzlePieces;
    }
}
