package com.androidproject;


import java.util.List;

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
        return false;
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
}
