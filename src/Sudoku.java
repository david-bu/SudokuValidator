public class Sudoku {

    public static void main(String[] args) {
        int[][] sudoku = {
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 9, 1, 2, 3, 4, 5, 6, 7, 8 },
                { 8, 9, 1, 2, 3, 4, 5, 6, 7 },
                { 7, 8, 9, 1, 2, 3, 4, 5, 6 },
                { 6, 7, 8, 9, 1, 2, 3, 4, 5 },
                { 5, 6, 7, 8, 9, 1, 2, 3, 4 },
                { 4, 5, 6, 7, 8, 9, 1, 2, 3 },
                { 3, 4, 5, 6, 7, 8, 9, 1, 2 },
                { 2, 3, 4, 5, 6, 7, 8, 9, 1 }
        };
        int[][] validSudoku = {
                { 8, 2, 7, 1, 5, 4, 3, 9, 6 },
                { 9, 6, 5, 3, 2, 7, 1, 4, 8 },
                { 3, 4, 1, 6, 8, 9, 7, 5, 2 },
                { 5, 9, 3, 4, 6, 8, 2, 7, 1 },
                { 4, 7, 2, 5, 1, 3, 6, 8, 9 },
                { 6, 1, 8, 9, 7, 2, 4, 3, 5 },
                { 7, 8, 6, 2, 3, 5, 9, 1, 4 },
                { 1, 5, 4, 7, 9, 6, 8, 2, 3 },
                { 2, 3, 9, 8, 4, 1, 5, 6, 7 }
        };

        System.out.println(isValidSudokuSolution(sudoku));
        System.out.println(isValidSudokuSolution(validSudoku));
    }

    public static boolean isValidSudokuSolution(int[][] sudoku) {
        if (!isValidLayout(sudoku)) {
            System.out.println("Wrong sudoku layout!");
            return false;
        }
        return areColumnsValid(sudoku) && areRowsValid(sudoku) && areMatricesValid(sudoku);
    }

    private static boolean areColumnsValid(int[][] sudoku) {
        boolean[] values = new boolean[9];
        for (int row = 0; row < 9; row++) {
            setBooleanArrayFalse(values);
            for (int column = 0; column < 9; column++) {
                int index = sudoku[row][column]-1;
                if (index < 0 || index > 8)
                    return false; // inserted num not in range 1..9
                values[index] = true; // get number in sudoku field and set its corresponding boolean
            }
            if (!isBooleanArrayTrue(values)) // min one num missing
                return false;
        }
        return true;
    }

    private static boolean areRowsValid(int[][] sudoku) {
        boolean[] values = new boolean[9];
        for (int column = 0; column < 9; column++) {
            setBooleanArrayFalse(values);
            for (int row = 0; row < 9; row++) {
                int index = sudoku[row][column]-1;
                if (index < 0 || index > 8)
                    return false; // inserted num not in range 1..9
                values[index] = true; // get number in sudoku field and set its corresponding boolean
            }
            if (!isBooleanArrayTrue(values)) // min one num missing
                return false;
        }
        return true;
    }

    private static boolean areMatricesValid(int[][] sudoku) {
        for (int matrixRow = 0; matrixRow < 3; matrixRow++) {
            for (int matrixColumn = 0; matrixColumn < 3; matrixColumn++) {
                if (!isMatrixValid(sudoku, 3 * matrixColumn, 3 * matrixRow))
                    return false;
            }
        }
        return true;
    }

    private static boolean isMatrixValid(int[][] sudoku, int top, int left) {
        boolean[] values = new boolean[9];
        setBooleanArrayFalse(values);
        for (int row = left; row < left+3; row++) {
            for (int column = top; column < top+3; column++) {
                int index = sudoku[row][column]-1;
                if (index < 0 || index > 8)
                    return false; // inserted num not in range 1..9
                values[index] = true; // get number in sudoku field and set its corresponding boolean
            }
        }
        // false if min one num missing
        return isBooleanArrayTrue(values);
    }

    private static void setBooleanArrayFalse(boolean[] values) {
        // or Arrays.fill(values, false);
        for (int i = 0; i < values.length; i++)
            values[i] = false;
    }

    private static boolean isBooleanArrayTrue(boolean[] values) {
        for (boolean value : values) {
            if (!value)
                return false;
        }
        return true;
    }

    private static boolean isValidLayout(int[][] sudoku) {
        return (sudoku != null && sudoku.length == 9 &&
                sudoku[0] != null && sudoku[0].length == 9 &&
                sudoku[1] != null && sudoku[1].length == 9 &&
                sudoku[2] != null && sudoku[2].length == 9
        );
    }
}