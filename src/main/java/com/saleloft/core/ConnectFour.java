package com.saleloft.core;


public class ConnectFour {
    private Integer redInput;
    private Integer yellowInput;

    public void setRedInput(int redInput) {
        this.redInput = redInput;
    }

    public void setYellowInput(int yellowInput) {
        this.yellowInput = yellowInput;
    }

    //We need to first create the basic visual pattern

    public String[][] createPattern() {

        String[][] f = new String[7][15];

        for (int i = 0; i < f.length; i++)
        {
            for (int j = 0; j < f[i].length; j++)
            {
                if (j % 2 == 0) f[i][j] = "|";

                else f[i][j] = " ";

                if (i == 6) f[i][j] = "-";

            }
        }
        return f;
    }


    public String[][] applyRedInputToMatrix(String[][] f) {

        int c = 2 * getRedInput() + 1;
        for (int i = 5; i >= 0; i--) {
            if (f[i][c] == " ") {
                f[i][c] = "R";
                break;
            }
        }

        return f;
    }


    public String[][] applyYellowInputToMatrix(String[][] f) {

        int c = 2 * getYellowInput() + 1;

        for (int i = 5; i >= 0; i--) {
            if (f[i][c] == " ") {
                f[i][c] = "Y";
                break;
            }
        }

        return f;
    }


    public String checkWinner(String[][] f) {


        for (int i = 0; i < 6; i++) {


            for (int j = 0; j < 7; j += 2) {

                if ((f[i][j + 1] != " ")

                        && (f[i][j + 3] != " ")

                        && (f[i][j + 5] != " ")

                        && (f[i][j + 7] != " ")

                        && ((f[i][j + 1] == f[i][j + 3])

                        && (f[i][j + 3] == f[i][j + 5])

                        && (f[i][j + 5] == f[i][j + 7])))

                    //If we found a same-colored pattern, we'll return

                    //the color so that we will know who won

                    return f[i][j + 1];
            }
        }


        for (int i = 1; i < 15; i += 2) {

            for (int j = 0; j < 3; j++) {
                if ((f[j][i] != " ")
                        && (f[j + 1][i] != " ")
                        && (f[j + 2][i] != " ")
                        && (f[j + 3][i] != " ")
                        && ((f[j][i] == f[j + 1][i])
                        && (f[j + 1][i] == f[j + 2][i])
                        && (f[j + 2][i] == f[j + 3][i])))

                    return f[j][i];
            }
        }


        for (int i = 0; i < 3; i++) {


            for (int j = 1; j < 9; j += 2)
                if ((f[i][j] != " ")
                        && (f[i + 1][j + 2] != " ")
                        && (f[i + 2][j + 4] != " ")
                        && (f[i + 3][j + 6] != " ")
                        && ((f[i][j] == f[i + 1][j + 2])
                        && (f[i + 1][j + 2] == f[i + 2][j + 4])
                        && (f[i + 2][j + 4] == f[i + 3][j + 6])))

                    return f[i][j];
        }



        for (int i = 0; i < 3; i++) {
            for (int j = 7; j < 15; j += 2) {
                if ((f[i][j] != " ")
                        && (f[i + 1][j - 2] != " ")
                        && (f[i + 2][j - 4] != " ")
                        && (f[i + 3][j - 6] != " ")
                        && ((f[i][j] == f[i + 1][j - 2])
                        && (f[i + 1][j - 2] == f[i + 2][j - 4])
                        && (f[i + 2][j - 4] == f[i + 3][j - 6])))

                    return f[i][j];
            }
        }

        return null;
    }

    public int getRedInput() {
        return redInput;
    }


//The easy part: using these methods


    public String[][] start() {
        String[][] f = createPattern();
        return f;
    }

    public int getYellowInput() {
        return yellowInput;
    }
}
