package com.androidproject;


import java.util.List;
import java.util.Random;

public class GameManager {
    private PuzzleView puzzleView;
    private int freeBoxX;
    private int freeBoxY;

    public int getFreeBoxX() {
        return freeBoxX;
    }

    public void setFreeBoxX(int freeBoxX) {
        this.freeBoxX = freeBoxX;
    }

    public int getFreeBoxY() {
        return freeBoxY;
    }

    public void setFreeBoxY(int freeBoxY) {
        this.freeBoxY = freeBoxY;
    }

    public GameManager(PuzzleView puzzleView) {
        this.puzzleView = puzzleView;
        freeBoxX = puzzleView.getColumns() - 1;
        freeBoxY = puzzleView.getRows() - 1;
    }

    public void calculateFreeDirections() {
        invalidateFreeDirections();
        PuzzlePiece puzzlePiece;
        puzzlePiece = getAbovePuzzlePiece(puzzleView.getPuzzlePieces(), freeBoxX, freeBoxY);
        if (puzzlePiece != null) {
            puzzlePiece.setFreeDirection(Direction.DOWN);
        }
        puzzlePiece = getRightPuzzlePiece(puzzleView.getPuzzlePieces(), freeBoxX, freeBoxY);
        if (puzzlePiece != null) {
            puzzlePiece.setFreeDirection(Direction.LEFT);
        }
        puzzlePiece = getLeftPuzzlePiece(puzzleView.getPuzzlePieces(), freeBoxX, freeBoxY);
        if (puzzlePiece != null) {
            puzzlePiece.setFreeDirection(Direction.RIGHT);
        }
        puzzlePiece = getBelowPuzzlePiece(puzzleView.getPuzzlePieces(), freeBoxX, freeBoxY);
        if (puzzlePiece != null) {
            puzzlePiece.setFreeDirection(Direction.TOP);
        }
    }

    public boolean isGameFinished() {
        for(PuzzlePiece puzzlePiece:puzzleView.getPuzzlePieces()){
            if(puzzlePiece.getCoordinateX()!=puzzlePiece.getInitialCoordinateX() || puzzlePiece.getCoordinateY()!=puzzlePiece.getInitialCoordinateY())
                return false;
        }
        return true;
    }

    private PuzzlePiece getPuzzlePiece(List<PuzzlePiece> puzzlePieces, int coordinateX, int coordinateY) {
        for (PuzzlePiece puzzlePiece : puzzlePieces) {
            if (puzzlePiece.getCoordinateX() == coordinateX && puzzlePiece.getCoordinateY() == coordinateY)
                return puzzlePiece;
        }
        return null;
    }

    private PuzzlePiece getAbovePuzzlePiece(List<PuzzlePiece> puzzlePieces, int coordinateX, int coordinateY) {
        return getPuzzlePiece(puzzlePieces, coordinateX, coordinateY - 1);
    }

    private PuzzlePiece getRightPuzzlePiece(List<PuzzlePiece> puzzlePieces, int coordinateX, int coordinateY) {
        return getPuzzlePiece(puzzlePieces, coordinateX + 1, coordinateY);
    }

    private PuzzlePiece getLeftPuzzlePiece(List<PuzzlePiece> puzzlePieces, int coordinateX, int coordinateY) {
        return getPuzzlePiece(puzzlePieces, coordinateX - 1, coordinateY);
    }

    private PuzzlePiece getBelowPuzzlePiece(List<PuzzlePiece> puzzlePieces, int coordinateX, int coordinateY) {
        return getPuzzlePiece(puzzlePieces, coordinateX, coordinateY + 1);
    }

    private void invalidateFreeDirections(){
        for(PuzzlePiece puzzlePiece:puzzleView.getPuzzlePieces()){
            puzzlePiece.setFreeDirection(null);
        }
    }

    private Direction getRandomDirection(){
        Random random=new Random();
        return Direction.values()[random.nextInt(4)];
    }

    public void shuffle(){
        PuzzlePiece puzzlePiece;
        for(int i=0;i<1;i++){
            Direction d=getRandomDirection();
            switch(d){
                case TOP:
                    puzzlePiece=getAbovePuzzlePiece(puzzleView.getPuzzlePieces(), freeBoxX, freeBoxY);
                    if(puzzlePiece!=null){
                        puzzlePiece.setCoordinateY(puzzlePiece.getCoordinateY()+1);
                        freeBoxY--;
                    }
                    break;
                case RIGHT:
                    puzzlePiece=getRightPuzzlePiece(puzzleView.getPuzzlePieces(), freeBoxX, freeBoxY);
                    if(puzzlePiece!=null){
                        puzzlePiece.setCoordinateX(puzzlePiece.getCoordinateX() - 1);
                        freeBoxX++;
                    }
                    break;
                case LEFT:
                    puzzlePiece=getLeftPuzzlePiece(puzzleView.getPuzzlePieces(), freeBoxX, freeBoxY);
                    if(puzzlePiece!=null){
                        puzzlePiece.setCoordinateX(puzzlePiece.getCoordinateX() + 1);
                        freeBoxX--;
                    }
                    break;
                case DOWN:
                    puzzlePiece=getBelowPuzzlePiece(puzzleView.getPuzzlePieces(), freeBoxX, freeBoxY);
                    if(puzzlePiece!=null){
                        puzzlePiece.setCoordinateY(puzzlePiece.getCoordinateY()-1);
                        freeBoxY++;
                    }
                    break;
            }
        }
        calculateFreeDirections();
        puzzleView.invalidate();
    }
}
