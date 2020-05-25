package com.example.everyday.m04;

import java.util.Arrays;

/**
 * #289
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 */
public class T02 {

    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] tmp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int c = 0;
                if (i - 1 >= 0 && j - 1 >= 0) {
                    c += board[i - 1][j - 1];
                }
                if (i - 1 >= 0) {
                    c += board[i - 1][j];
                }
                if (i - 1 >= 0 && j + 1 < n) {
                    c += board[i - 1][j + 1];
                }
                if (j - 1 >= 0) {
                    c += board[i][j - 1];
                }
                if (j + 1 < n) {
                    c += board[i][j + 1];
                }
                if (i + 1 < m && j - 1 >= 0) {
                    c += board[i + 1][j - 1];
                }
                if (i + 1 < m) {
                    c += board[i + 1][j];
                }
                if (i + 1 < m && j + 1 < n) {
                    c += board[i + 1][j + 1];
                }
                if (board[i][j] == 1) {
                    if (c < 2 || c > 3) {
                        tmp[i][j] = 0;
                    } else {
                        tmp[i][j] = 1;
                    }
                } else {
                    if (c == 3) {
                        tmp[i][j] = 1;
                    } else {
                        tmp[i][j] = 0;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = tmp[i][j];
            }
        }
    }

    public static void main(String[] args) {
        T02 t = new T02();
        int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        t.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }
}
