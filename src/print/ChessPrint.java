package print;

import com.Game;
import com.Move;

import java.awt.*;

public class ChessPrint {

	public static boolean selection;
	public static boolean choseStone;

	private boolean computer;
	private boolean computerVScomputer;
	private boolean easterEgg[] = new boolean[10];
	private boolean uhrSek = false;
	private Timer timeP1 = new Timer(1800);
	private Timer timeP2 = new Timer(1800);
	private int width, abstX, abstY;
	private int quaX;
	private int quaY = 100;

	Graphics2D gBuffer;
	Game game;
	ChessColor color = new ChessColor();

	public ChessPrint() {
	}
	
	public void print(Game game, int width) { //int x1, int y1, int x2, int y2){
		this.game = game;
		this.width = width;
		abstX = width;
		abstY = width;
		gBuffer = Main.gBuffer;
		CheckBoard(width);
		int selecColor = 5;
		while (color.checkForSameColorSelection(selecColor)) {
			selecColor++;
		}
		gBuffer.setColor(color.getColor(selecColor % color.getLength()));
		if (selection) { // prints selection
			Field(Main.m.getX1(), Main.m.getY1());
		}
		if(choseStone){ // prints chose
			Field(Main.m.getX2(), Main.m.getY2());
		}
		new ChessStones(game.brd, gBuffer);
//		if(choseStone) new ChessStones().ChoseStone(choseStoneX, choseStoneY);
//		Settings();
//		clock(timeP1, false);
//		clock(timeP2, true);
	}

	public int scale(double n){
		return (int)(n * width) / 100;
	}

	public void oval(int x, int y, int width, int height){
		gBuffer.fillOval(scale(x) + abstX, scale(y) + abstY, scale(width), scale(height));
	}

	public void Settings(){
//		if(minsek%2==0) uhrSek = true;
//		else
			uhrSek = false;
		
		//Start Stop Pause
		if(game.isRunning()){
			TextBox(scale(1140), scale(-40), scale(220), scale(60), Color.orange);
			TextBox(scale(1262), scale(-35), scale(3), scale(50), Color.black);
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.BOLD, scale(36)));
			gBuffer.drawString("Pause", scale(1150) + abstX, scale(3) + abstY);
			gBuffer.drawString("Stop", scale(1270) + abstX, scale(3) + abstY);
		} else {
			TextBox(scale(1140), scale(-40), scale(220), scale(60), Color.orange);
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.BOLD, scale(40)));
//			if(game.isPaused())
//				gBuffer.drawString("Weiter", scale(1190) + abstX, scale(5) + abstY);
//			else gBuffer.drawString("Start", scale(1200) + abstX, scale(5) + abstY);
		}
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, scale(36)));
		gBuffer.drawString("Spielmodus", 11*width + (45) + abstX, scale(80) + abstY);
		gBuffer.setFont(new Font("Arial", Font.BOLD, scale(30)));
		gBuffer.drawString("SvS", scale(1123) + abstX, scale(140) + abstY);
		gBuffer.drawString("SvC", scale(1193) + abstX, scale(140) + abstY);
		gBuffer.drawString("CvS", scale(1262) + abstX, scale(140) + abstY);
		gBuffer.drawString("CvC", scale(1332) + abstX, scale(140) + abstY);
		gBuffer.setColor(Color.ORANGE);
		for (int i = 0; i < 4; i++) {
			oval(1140 + 70 * i, 150, 25, 25);
		}

		//Choise-Buttons
		gBuffer.setColor(Color.black);
			 if(!computer && !computerVScomputer) oval(1145, 150 + 5, 15, 15);
		else if( computer && !computerVScomputer) oval(1215, 150 + 5, 15, 15);
		else if(!computer &&  computerVScomputer) oval(1285, 150 + 5, 15, 15);
		else if( computer &&  computerVScomputer) oval(1355, 150 + 5, 15, 15);
		
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, scale(34)));
		gBuffer.drawString("Farbe der Felder", scale(1126) + abstX, scale(243) + abstY);

		gBuffer.setFont(new Font("Arial", Font.BOLD, scale(27)));
		TextBox(scale(1180), scale(260), scale(150), scale(60), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color.getStringField1(), scale(1180) + abstX, scale(303) + abstY);
		Button(scale(1140), scale(260), scale(30), scale(60), 1);
		Button(scale(1345), scale(260), scale(30), scale(60), 2);
		
		gBuffer.setFont(new Font("Arial", Font.BOLD, scale(27)));
		gBuffer.setColor(Color.ORANGE);
		TextBox(scale(1180), scale(330), scale(150), scale(60), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color.getStringField2(), scale(1180) + abstX, scale(373) + abstY);
		Button(scale(1140), scale(330), scale(30), scale(60), 1);
		Button(scale(1345), scale(330), scale(30), scale(60), 2);
		
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, scale(34)));
		gBuffer.drawString("Farbe der Figuren", scale(1108) + abstX, scale(463) + abstY);
		gBuffer.setFont(new Font("Arial", Font.BOLD, scale(27)));
		TextBox(scale(1180), scale(480), scale(150), scale(60), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color.getStringPlayer1(), scale(1180) + abstX, scale(523) + abstY);
		Button(scale(1140), scale(480), scale(30), scale(60), 1);
		Button(scale(1345), scale(480), scale(30), scale(60), 2);
		
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, scale(27)));
		gBuffer.setColor(Color.ORANGE);
		TextBox(scale(1180), scale(550), scale(150), scale(60), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color.getStringPlayer1(), scale(1180) + abstX, scale(593) + abstY);
		Button(scale(1140), scale(550), scale(30), scale(60), 1);
		Button(scale(1345), scale(550), scale(30), scale(60), 2);
		
		//Zeiteinstellen
		Button((1105), scale(740), scale(98), scale(50), 0);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, scale(40)));
		gBuffer.setColor(Color.black);
		if(uhrSek)
			gBuffer.drawString("Sek", scale(1119) + abstX, scale(779) + abstY);
		else {
			gBuffer.setFont(new Font("Arial", Font.PLAIN, scale(20)));
			gBuffer.drawString("Min:Sek", scale(1118) + abstX, scale(774) + abstY);
		}
		
		int l = scale(1210); int o = scale(700); int u = scale(805);
		int w = scale(35); int h = scale(25);
		int ab = scale(40);
		
//		Button(btn.getDim().x, btn.getDim().y, btn.getDim().width, btn.getDim().height,btn.getDim().n); //TODO: Button
		for (int i = 0; i < 4; i++)
			Button(l+ab*i, o, w, h, 3);

		TextBox(scale(1210), scale(735), scale(155), scale(60), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, scale(65)));
		String zero = "";
		String zero2 = "";
		if(uhrSek){
				 if(timeP1.getLimit() < 10) zero = "000";
			else if(timeP1.getLimit() < 100) zero = "00";
			else if(timeP1.getLimit() < 1000) zero = "0";
			else zero = "";
			gBuffer.drawString(zero + timeP1.getLimit(), scale(1115) + abstX+width, scale(787) + abstY);
		} else{
			zero2 = "";
			if((timeP1.getLimit()/60)<10) zero2 = "0";
			zero = "";
			if((timeP1.getLimit()%60)<10) zero = "0";
			gBuffer.drawString(zero2 + (timeP1.getLimit()/60) + ":" + zero + (timeP1.getLimit()%60), scale(1107) + abstX+width, scale(787) + abstY);
		}
		for (int i = 0; i < 4; i++)
			Button(l+ab*i, u, w, h, 4);
	}
	
//	public void Buf(int x, int y, int width, int abstX, int abstY){
//		gBuffer.fillRect(x * width + abstX, y * width + abstY, scale(30), 10);
//	}

	public void CheckBoard(int width){
		this.width = width;
		gBuffer.setColor(color.getField1()); // chessboard
		gBuffer.fillRect(abstX, abstY, width*8, width*8);
		gBuffer.setColor(color.getField2());
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(Math.abs(i-j)%2 == 1) Field(i , j);
			}
		}

		if (selection && choseStone && game.isRunning()) {
			if(new Move().Normal(game.brd, Main.m.getX1(), Main.m.getY1(), Main.m.getX2(), Main.m.getY2())) {
				selection = false;
				choseStone = false;
				game.nextPlayer();
			}
		}

		gBuffer.setColor(Color.black); // black border
		gBuffer.drawRect(abstX, abstY, width*8, width*8);
		for (int i = 0; i < 8; i ++) {
			if(easterEgg[1]){
				gBuffer.setColor(Color.red);
				if(8 - i == 2 || 8 - i == 3 || 8 - i == 5 || 8 - i == 7)
					gBuffer.fillOval(width + abstX-width - scale(75), width * (i+1) + scale(27) + abstY-width, scale(50), scale(50));
			}
			if(easterEgg[2]){
				gBuffer.setColor(Color.blue);
				if(8 - i == 1 || 8 - i == 2 || 8 - i == 3 || 8 - i == 5 ||  8 - i == 8)
					gBuffer.fillOval(width*10 + abstX-width - (75), width * (i+1) + (27) + abstY-width, scale(50), scale(50));
			}
			if(easterEgg[3]){
				gBuffer.setColor(Color.CYAN);
				gBuffer.setFont(new Font("Arial", Font.BOLD, scale(23)));
				if(i == 0 || i == 1 || i == 2)
					gBuffer.drawString("2", width * i + (65) + abstX, width * 10 - (65) + abstY-width);
				gBuffer.setFont(new Font("Arial", Font.BOLD, scale(40)));
				if(i == 0) gBuffer.drawString("+", width * i + (85) + abstX, width * 10 - (37) + abstY-width);
				if(i == 1) gBuffer.drawString("=", width * i + (90) + abstX, width * 10 - (37) + abstY-width);
			}
			
//			gBuffer.setColor(Color.black);
//			gBuffer.setFont(new Font("Arial", Font.PLAIN, scale(50)));
//			gBuffer.drawString("" + chessStones.getROW().charAt(i), width * i + scale(35) + abstX, width * 1 - scale(35) + abstY-width);
//			gBuffer.drawString("" + (8-i), width - scale(65) + abstX-width, width * (i+1) + scale(70) + abstY-width);
//
//			gBuffer.setColor(Color.black);
//			gBuffer.setFont(new Font("Arial", Font.PLAIN, 50));
//			gBuffer.drawString("" + chessStones.getROW().charAt(i), width * i + scale(35) + abstX, width * 10 - scale(35) + abstY-width);
//			gBuffer.drawString("" + (8-i), width - scale(65) + abstX-width + width*9, width * (i+1) + scale(70) + abstY-width);
		}
	}

	public void Field(int i, int j){
		gBuffer.fillRect(i * width + abstX, j * width + abstY, width, width);
	}
	
	public void Button(int x, int y, int width, int height, int n){
		gBuffer.setColor(Color.darkGray);
		gBuffer.fillRect(x + abstX, y + abstY, width, height);
		gBuffer.setColor(Color.gray);
		if(n<=2) gBuffer.fillRect(x + abstX + (int)(0.25*width/2), y + abstY + (int)(0.18*height/2), width-(int)(0.25*width), height-(int)(0.18*height));
		else gBuffer.fillRect(x + abstX + (int)(0.18*width/2), y + abstY + (int)(0.4*height/2), width-(int)(0.18*width), height-(int)(0.4*height));
		gBuffer.setColor(Color.black);
		int[] x1 = new int[3];
		int[] y1 = new int[3];
//		int[] array1 = {20,20,20};
//		int[] array2 = {15, 5,25};
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j < 3; j++){
//				x1[j] = (x + 20*width /60 + abstX);
//				y1[j] = (y + 15*height/30 + abstY);
//			}
//		}
		switch (n){
			case 1: {
				x1[0] = (x + 20*width /60 + abstX);
				x1[1] = (x + 40*width /60 + abstX);
				x1[2] = (x + 40*width /60 + abstX);
				y1[0] = (y + 15*height/30 + abstY);
				y1[1] = (y +  5*height/30 + abstY);
				y1[2] = (y + 25*height/30 + abstY);
				break;
			} case 2: {
				x1[0] = (x + 40*width /60 + abstX);
				x1[1] = (x + 20*width /60 + abstX);
				x1[2] = (x + 20*width /60 + abstX);
				y1[0] = (y + 15*height/30 + abstY);
				y1[1] = (y +  5*height/30 + abstY);
				y1[2] = (y + 25*height/30 + abstY);
				break;
			} case 3: {
				x1[0] = (x + 15*width /30 + abstX);
				x1[1] = (x +  5*width /30 + abstX);
				x1[2] = (x + 25*width /30 + abstX);
				y1[0] = (y + 20*height/60 + abstY);
				y1[1] = (y + 40*height/60 + abstY);
				y1[2] = (y + 40*height/60 + abstY);
				break;
			} case 4: {
				x1[0] = (x + 15*width /30 + abstX);
				x1[1] = (x +  5*width /30 + abstX);
				x1[2] = (x + 25*width /30 + abstX);
				y1[0] = (y + 40*height/60 + abstY);
				y1[1] = (y + 20*height/60 + abstY);
				y1[2] = (y + 20*height/60 + abstY);
			}
			default: {}
		}
		gBuffer.fillPolygon(x1, y1, 3);
	}
	
	public void TextBox(int x, int y, int width, int height, Color c){
		gBuffer.setColor(c);
		gBuffer.fillRect(x + abstX, y + abstY, width, height);
	}
	
	public void clock(Timer time, boolean p1){
		// player 1
		gBuffer.setColor(Color.yellow);
		gBuffer.fillOval(width * 9 + abstX, width * 6 + abstY, scale(200), scale(200));
		gBuffer.setColor(Color.black);
		gBuffer.fillOval(width * 9 + abstX + scale(8.4), width * 6 + abstY + scale(8.4), scale(185), scale(185));
		gBuffer.setColor(Color.yellow);
		gBuffer.fillOval(width * 9 + abstX + scale(14), width * 6 + abstY + scale(14), scale(175), scale(175));
//		if(!easterEgg[0]) chessStones.easterEggBvB2(); //TODO: integrieren
		gBuffer.setColor(Color.gray);
		gBuffer.fillArc(width * 9 + abstX, width * 6 + abstY, scale(200), scale(200), 0, (int) (((double)time.getTime() / (double)time.getTime()) * 360) - 360);
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, scale(70)));
		if(uhrSek){
			String zero;
				 if(time.getTime() < 10) zero = "000";
			else if(time.getTime() < 100) zero = "00";
			else if(time.getTime() < 1000) zero = "0";
			else zero = "";
			gBuffer.drawString(zero + time.getTime(), (int)(width * 8.2) + abstX  * 2 -abstX+width, (int)(width * 5.6) + (int)(22) + abstY);
		}
		else{
			String zero2 = "";
			if ((time.getTime() / 60) < 10) zero2 = "0";
			String zero = "";
			if (time.getTime() % 60 < 10) zero = "0";
			gBuffer.drawString(zero2 + (time.getTime() / 60) + ":" + zero + time.getTime() % 60, (int) (width * 8.12) + abstX * 2 - abstX + width, (int) (width * 5.6) + (int) (22) + abstY);
		}
		
//		// Spieler 2
//		gBuffer.setColor(Color.yellow);
//		gBuffer.fillOval(width * 8 + abstX+width, abstY, width * 2, width * 2);
//		gBuffer.setColor(Color.black);
//		gBuffer.fillOval(width * 8 + abstX+ scale(8.4) +width, abstY + scale(8.4), width * 2 - (10), width * 2 - (10));
//		gBuffer.setColor(Color.yellow);
//		gBuffer.fillOval(width * 8 + abstX+ (10) +width, abstY + (10), width * 2 - (20), width * 2 - (20));
////		if(!easterEgg[0]) chessStones.easterEggBvB1(); //TODO: integrieren
//		gBuffer.setColor(Color.gray);
//		gBuffer.fillArc(width * 8 + abstX+width, abstY, width * 2, width * 2, 0, (int) (((double)timeP2.getTime() / (double)timeP1.getTime()) * 360)-360);
//		gBuffer.setColor(Color.black);
//		gBuffer.setFont(new Font("Arial", Font.PLAIN, scale(70)));
//		if(uhrSek){
//			String zero = "";
//			if(timeP2.getTime() < 10) zero = "000";
//			else if(timeP2.getTime() < 100) zero = "00";
//			else if(timeP2.getTime() < 1000) zero = "0";
//			else zero = "";
//			gBuffer.drawString(zero + timeP2.getTime(), (int)(width * 8.2) + abstX  * 2 -abstX+width, (int)(width * 2.5) + (int)(22) + abstY);
//		}
//		else{
//			String zero2 = "";
//			if((int)(timeP2.getTime() / 60) < 10) zero2 = "0";
//			String zero = "";
//			if(timeP2.getTime() % 60 < 10) zero = "0";
//			gBuffer.drawString(zero2 + (timeP2.getTime() / 60) + ":" + zero + timeP2.getTime() % 60, (int)(width * 8.12) + abstX  * 2 -abstX+width, (int)(width * 2.5) + (int)(22) + abstY);
//		}
		
		//Zuege Zaehler
		gBuffer.setColor(color.getField1());
		gBuffer.fillRoundRect(10*width + abstX-width, 5*width + abstY-width, scale(100), scale(100), quaX, quaY);
		gBuffer.fillRoundRect(11*width + abstX-width, 4*width + abstY-width, scale(100), scale(100), quaX, quaY);
		gBuffer.setColor(color.getField2());
		gBuffer.fillRoundRect(10*width + abstX-width, 4*width + abstY-width, scale(100), scale(100), quaX, quaY);
		gBuffer.fillRoundRect(11*width + abstX-width, 5*width + abstY-width, scale(100), scale(100), quaX, quaY);
		gBuffer.setColor(Color.gray);
		gBuffer.fillOval(width * 8 + scale(50) + abstX+width, width * 3 + scale(50) + abstY, width, width);
		
//		if(game){
//			gBuffer.fillOval(width * 8 + scale(50) + abstX+width, width * 3 + scale(50) + abstY, width, width);
//			if (getPlayer() == 0) {
//				gBuffer.setColor(col[countColor[2]%col.length]);
//				if(computer) new Random(); //Computer //TODO: integrieren
//			} else {
//				gBuffer.setColor(col[countColor[3]%col.length]);
//				if(computerVScomputer) new Random(); //ComputerVSComputer //TODO: integrieren
//			}
//		}
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, scale(40)));
		String zero;
			 if(game.brd.getMove_count()<10) zero = "000";
		else if(game.brd.getMove_count()<100) zero = "00";
		else if(game.brd.getMove_count()<1000) zero = "0";
		else zero = "";
		gBuffer.drawString(zero + game.brd.getMove_count(), (int)(width * 9.56) + abstX, width * 4 + scale(15) + abstY);
		if(game.isRunning()){
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.PLAIN, scale(65)));
			gBuffer.drawString("ad" /*game.getLastMove()*/, scale(800) + abstX+width, scale(870) + abstY);
		}
	}
}
