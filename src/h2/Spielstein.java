package h2;

public class Spielstein {
	

    private int currentRow;
    private int currentCol;
    private Spielbrett brett;

    public Spielstein(Spielbrett brett, int indexRow, int indexCol) {
        this.currentRow = indexRow;
        this.currentCol = indexCol;
        this.brett = brett;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

    public Spielbrett getBrett() {
        return brett;
    }

    public void setBrett(Spielbrett brett) {
        this.brett = brett;
    }

    // true, wenn direction vom aktuellen Feld aus das Brett verlassen würde
    private boolean movesOut() {
        Feld[][] arr = brett.getBrett();
        int dim = brett.getDim();

        Feld current = arr[currentRow][currentCol];
        char dir = current.getDirection();

        if (dir == 'U') {
            return currentRow == 0;
        } else if (dir == 'D') {
            return currentRow == dim - 1;
        } else if (dir == 'L') {
            return currentCol == 0;
        } else { // 'R'
            return currentCol == dim - 1;
        }
    }

    // führt n Züge aus (n ist nicht-negativ)
    public void go(int n) {
        Feld[][] arr = brett.getBrett();

        for (int step = 0; step < n; step++) {

            Feld current = arr[currentRow][currentCol];

            // Regel: boese == true -> stehen bleiben
            if (current.isBoese()) {
                continue;
            }

            // Regel: wenn der Zug aus dem Brett rausführen würde -> stehen bleiben
            if (movesOut()) {
                continue;
            }

            // sonst: einen Schritt in gespeicherte Richtung
            char dir = current.getDirection();

            if (dir == 'U') {
                currentRow = currentRow - 1;
            } else if (dir == 'D') {
                currentRow = currentRow + 1;
            } else if (dir == 'L') {
                currentCol = currentCol - 1;
            } else { // 'R'
                currentCol = currentCol + 1;
            }
        }
    }
}