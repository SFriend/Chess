package com.company;

public class Castling extends Logic {
	public Castling(int x1, int y1, int x2, int y2, int n) {
		moves[x2 + n][y2] = moves[x2][y2] + 1;
		moves[x2][y2] = moves[x1][y1] + 1;
		moves[x1][y1] = 0;
		board[x2 + n][y2] = board[x2][y2];
		board[x2][y2] = board[x1][y1];
		board[x1][y1] = setEmpty();

		player++;
	}
}
