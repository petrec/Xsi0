import java.util.Random;
import java.util.Scanner;

public class Enum {

    public enum Symbol{SX, SO, S_}

    public static final String RED = "\033[0;91m"; //Red
    public static final String BLUE = "\033[0;94m"; //Blue
    public static final String RESET = "\033[0;0m";

    public static void main(String[] args){
        Symbol[][] t = new Symbol[3][3];
        init(t);
        boolean xTurn=isXFirst();{
            if(xTurn==true) {
                System.out.println("Este randul lui X sa aleaga o casuta!");
            }
            else System.out.println("Este randul lui 0 sa aleaga o casuta!");
        }

        while(true) {
            boolean s = nextMove(t, xTurn);
            if(s==true) {
                xTurn = !xTurn;
            }
            showTable(t);

            if(hasWonX(t) == true){
                System.out.println("Jucatorul X este castigator");
                break;
            }
            if(hasWon0(t) == true){
                System.out.println("Jucatorul 0 este castigator");
                break;
            }
            if(isADraw(t) == true){
                System.out.println("Jocul s-a terminat. Este egal.");
                break;
            }
        }
    }

    public static boolean nextMove(Symbol[][] t, boolean xTurn) {
        boolean success = false;
        int a = readValidIndex();
        int b = readValidIndex();
        if (t[a][b] == Symbol.S_) {
            if (xTurn == true) {
                t[a][b] = Symbol.SX;
                success = true;
            }
            else{
                t[a][b] = Symbol.SO;
                success = true;
            }
        }
        return success;
    }

    public static boolean hasWonX(Symbol[][]t){
        boolean winner = true;
        for(int i=0; i<3; ++i) {
            if ((Symbol.SX == t[i][0]&&Symbol.SX == t[i][1]&&Symbol.SX == t[i][2]) ||
                    (Symbol.SX == t[0][i]&&Symbol.SX == t[1][i]&&Symbol.SX == t[2][i]) ||
                    (Symbol.SX == t[0][0]&&Symbol.SX == t[1][1]&&Symbol.SX == t[2][2]) ||
                    (Symbol.SX == t[0][2]&&Symbol.SX == t[1][1]&&Symbol.SX == t[2][0])  ) {
                winner = true;
            } else {
                winner = false;
            }
        }
        return winner;
    }

    public static boolean hasWon0(Symbol[][]t){
        boolean winner = true;
        for (int i=0; i<3; ++i) {
            if ((Symbol.SO == t[i][0] && Symbol.SO == t[i][1] && Symbol.SO == t[i][2]) ||
                    (Symbol.SO == t[0][i] && Symbol.SO == t[1][i] && Symbol.SO == t[2][i]) ||
                    (Symbol.SO == t[0][0] && Symbol.SO == t[1][1] && Symbol.SO == t[2][2]) ||
                    (Symbol.SO == t[0][2] && Symbol.SO == t[1][1] && Symbol.SO == t[2][0])) {
                winner = true;
            } else {
                winner = false;
            }
        }
        return winner;
    }

    public static boolean isADraw(Symbol[][] t){
        boolean draw = true;
        for (int i=0; i<3; ++i){
            for (int j=0; j<3; ++j){
                if (Symbol.S_ == t[i][j]){
                    draw = false;
                }
            }
        }
        return draw;
    }

    public static int readValidIndex(){
        Scanner sc=new Scanner(System.in);
        int a;
        while(true){
            a=sc.nextInt();
            if (a>=0 && a<=2)
                break;
        }
        return a;
    }

    public static boolean isXFirst(){
        Random r=new Random();
        return r.nextBoolean();
    }

    static void init(Symbol[][] t){
        for(int i=0; i<3; ++i)
            for(int j=0; j<3; ++j)
                t[i][j] = Symbol.S_;
    }

    static void showTable(Symbol[][] t){
        for(int i=0; i<3; ++i){
            for(int j=0; j<3; ++j)
                switch (t[i][j]){
                    case SX:
                        System.out.print(BLUE + 'X'+" ");
                        break;
                    case SO:
                        System.out.print(RED + '0'+" ");
                        break;
                    default:
                        System.out.print(RESET + '_' + " ");
                        break;
                }
            System.out.println();
        }
    }

}
