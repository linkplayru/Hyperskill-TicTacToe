import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char[][] field = createField();
        printField(field);

        char player = 'X';

        while (getStatus(field).equals("Game not finished")) {
            move(s, field, player);
            if (player == 'X') {
                player = 'O';
            } else {
                player = 'X';
            }
        }
        System.out.println(getStatus(field));
    }

    private static char[][] createField() {
        char[][] field = new char[3][3];
        for (int i=0; i<3; i++) {
            Arrays.fill(field[i], '_');
        }
        return field;
    }

    private static void move(Scanner s, char[][] field, char player) {
        System.out.print("Enter the coordinates: ");
        try {
            int x = s.nextInt() - 1;
            int y = s.nextInt() - 1;
            if (x < 0 || x > 2 || y < 0 || y > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                move(s, field, player);
            } else {
                if (field[y][x] == '_') {
                    field[y][x] = player;
                    printField(field);
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                    move(s, field, player);
                }
            }
        } catch (InputMismatchException e) {
            s.nextLine();
            System.out.println("You should enter numbers!");
            move(s, field, player);
        }
    }

    private static void printField(char[][] field) {
        System.out.println("---------");
        System.out.println("| " + field[2][0] + " " + field[2][1] + " " + field[2][2] + " |");
        System.out.println("| " + field[1][0] + " " + field[1][1] + " " + field[1][2] + " |");
        System.out.println("| " + field[0][0] + " " + field[0][1] + " " + field[0][2] + " |");
        System.out.println("---------");
    }

    private static String getStatus(char[][] field) {
        boolean x3inRow = false;
        boolean o3inRow = false;
        boolean hasEmptyCells = false;
        int numOfX = 0;
        int numOfO = 0;
        
        for (int y=0; y<field.length; y++) {
            for (int x=0; x<field[y].length; x++) {
                if (field[y][x] == '_') {
                    hasEmptyCells = true;
                } else if (field[y][x] == 'X') {
                    numOfX++;
                } else if (field[y][x] == 'O') {
                    numOfO++;
                }
            }
        }

        if (field[0][0] == 'X' && field[0][1] == 'X' && field[0][2] == 'X' || 
                field[1][0] == 'X' && field[1][1] == 'X' && field[1][2] == 'X' || 
                field[2][0] == 'X' && field[2][1] == 'X' && field[2][2] == 'X' ||
                field[0][0] == 'X' && field[1][0] == 'X' && field[2][0] == 'X' ||
                field[0][1] == 'X' && field[1][1] == 'X' && field[2][1] == 'X' ||
                field[0][2] == 'X' && field[1][2] == 'X' && field[2][2] == 'X' ||
                field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X' ||
                field[2][0] == 'X' && field[1][1] == 'X' && field[0][2] == 'X') {
            x3inRow = true;
        }

        if (field[0][0] == 'O' && field[0][1] == 'O' && field[0][2] == 'O' ||
                field[1][0] == 'O' && field[1][1] == 'O' && field[1][2] == 'O' ||
                field[2][0] == 'O' && field[2][1] == 'O' && field[2][2] == 'O' ||
                field[0][0] == 'O' && field[1][0] == 'O' && field[2][0] == 'O' ||
                field[0][1] == 'O' && field[1][1] == 'O' && field[2][1] == 'O' ||
                field[0][2] == 'O' && field[1][2] == 'O' && field[2][2] == 'O' ||
                field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O' ||
                field[2][0] == 'O' && field[1][1] == 'O' && field[0][2] == 'O') {
            o3inRow = true;
        }

        if (x3inRow && o3inRow || Math.abs(numOfX-numOfO) >= 2) {
            return "Impossible";
        } else {
            if (x3inRow) {
                return "X wins";
            } else if (o3inRow) {
                return "O wins";
            } else {
                if (hasEmptyCells) {
                    return "Game not finished";
                } else {
                    return "Draw";
                }
            }
        }
    }
}
