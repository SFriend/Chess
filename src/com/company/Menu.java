package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Menu extends Main {
	EasterEgg easEgg = new EasterEgg();
	
	public Menu(){
		Schachfeld();
		int selecColor = 5;
		while (checkForSameColorSelection(selecColor)) {
			selecColor++;
		}
		gBuffer.setColor(col[selecColor % col.length]);
		if (selection) { // prints selection
			gBuffer.fillRect(m.getX1() * width + abstX, m.getY1() * width + abstY, width, width);
		}
		if(choseStone){ // prints chose
			gBuffer.fillRect(m.getX2() * width + abstX, m.getY2() * width + abstY, width, width);
		}
		new Stones();
//		if(choseStone) new Stones().ChoseStone(choseStoneX, choseStoneY);
		Settings();
		Uhren();
	}

	public int scale(int n){
		return (n * width) / 100;
	}
	
	public void Settings(){
		if(minsek%2==0) uhrSek = true;
		else uhrSek = false;
		
		//Start Stop Pause
		if(game){
			TextBox(scale(1140), (int)(-40*width/100), (int)(220*width/100), scale(60), Color.orange);
			TextBox(scale(1262), (int)(-35*width/100), (int)(3*width/100), scale(50), Color.black);
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.BOLD, scale(36)));
			gBuffer.drawString("Pause", (int)(1150*width/100) + abstX, (int)(3*width/100) + abstY);
			gBuffer.drawString("Stop", (int)(1270*width/100) + abstX, (int)(3*width/100) + abstY);
		} else {
			TextBox(scale(1140), (int)(-40*width/100), (int)(220*width/100), scale(60), Color.orange);
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.BOLD, scale(40)));
			if(pause)
				gBuffer.drawString("Weiter", scale(1190) + abstX, scale(5) + abstY);
			else gBuffer.drawString("Start", scale(1200) + abstX, scale(5) + abstY);
		}
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, scale(36)));
		gBuffer.drawString("Spielmodus", 11*width + (int)(45*width/100) + abstX, (int)(80*width/100) + abstY);
		gBuffer.setFont(new Font("Arial", Font.BOLD, scale(30)));
		gBuffer.drawString("SvS", scale(1123) + abstX, scale(140) + abstY);
		gBuffer.drawString("SvC", scale(1193) + abstX, scale(140) + abstY);
		gBuffer.drawString("CvS", scale(1262) + abstX, scale(140) + abstY);
		gBuffer.drawString("CvC", scale(1332) + abstX, scale(140) + abstY);
		gBuffer.setColor(Color.ORANGE);
		gBuffer.fillOval(scale(1140) + abstX, scale(150) + abstY, scale(25), scale(25));
		gBuffer.fillOval(scale(1210) + abstX, scale(150) + abstY, scale(25), scale(25));
		gBuffer.fillOval(scale(1280) + abstX, scale(150) + abstY, scale(25), scale(25));
		gBuffer.fillOval(scale(1350) + abstX, scale(150) + abstY, scale(25), scale(25));

		//Choise-Buttons
		gBuffer.setColor(Color.black);
			 if(!computer && !computerVScomputer) gBuffer.fillOval(scale(1145) + abstX, scale(150) + scale(5) + abstY, scale(15), scale(15));
		else if( computer && !computerVScomputer) gBuffer.fillOval(scale(1215) + abstX, scale(150) + scale(5) + abstY, scale(15), scale(15));
		else if(!computer &&  computerVScomputer) gBuffer.fillOval(scale(1285) + abstX, scale(150) + scale(5) + abstY, scale(15), scale(15));
		else if( computer &&  computerVScomputer) gBuffer.fillOval(scale(1355) + abstX, scale(150) + scale(5) + abstY, scale(15), scale(15));
		
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(34*width/100)));
		gBuffer.drawString("Farbe der Felder", scale(1126) + abstX, scale(243) + abstY);

		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(27*width/100)));
		TextBox(scale(1180), scale(260), scale(150), scale(60), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color[countColor[0]%color.length], scale(1180) + abstX, (int)(303*width/100) + abstY);
		Button(scale(1140), scale(260), scale(30), scale(60), 1);
		Button(scale(1345), scale(260), scale(30), scale(60), 2);
		
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(27*width/100)));
		gBuffer.setColor(Color.ORANGE);
		TextBox(scale(1180), scale(330), scale(150), scale(60), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color[countColor[1]%color.length], scale(1180) + abstX, scale(373) + abstY);
		Button(scale(1140), scale(330), scale(30), scale(60), 1);
		Button(scale(1345), scale(330), scale(30), scale(60), 2);
		
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(34*width/100)));
		gBuffer.drawString("Farbe der Figuren", scale(1108) + abstX, scale(463) + abstY);
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(27*width/100)));
		TextBox(scale(1180), scale(480), scale(150), scale(60), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color[countColor[2]%color.length], scale(1180) + abstX, scale(523) + abstY);
		Button(scale(1140), scale(480), scale(30), scale(60), 1);
		Button(scale(1345), scale(480), scale(30), scale(60), 2);
		
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(27*width/100)));
		gBuffer.setColor(Color.ORANGE);
		TextBox(scale(1180), (int)(550*width/100), scale(150), scale(60), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color[countColor[3]%color.length], scale(1180) + abstX, scale(593) + abstY);
		Button(scale(1140), (int)(550*width/100), scale(30), scale(60), 1);
		Button(scale(1345), (int)(550*width/100), scale(30), scale(60), 2);
		
		//Zeiteinstellen
		Button((1105*width/100), (int)(740*width/100), scale(98), scale(50), 0);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, 40*width/100));
		gBuffer.setColor(Color.black);
		if(uhrSek)
			gBuffer.drawString("Sek", (int)(1119*width/100) + abstX, (int)(779*width/100) + abstY);
		else {
			gBuffer.setFont(new Font("Arial", Font.PLAIN, 20*width/100));
			gBuffer.drawString("Min:Sek", (int)(1118*width/100) + abstX, (int)(774*width/100) + abstY);
		}
		
		int l = scale(1210); int o = scale(700); int u = scale(805);
		int w = scale(35); int h = scale(25);
		int ab = scale(40);
		
//		Button(btn.getDim().x, btn.getDim().y, btn.getDim().width, btn.getDim().height,btn.getDim().n); //TODO: Button
		for (int i = 0; i < 4; i++)
			Button(l+ab*i, o, w, h, 3);

		TextBox(scale(1210), scale(735), scale(155), scale(60), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, 65*width/100));
		String zero = "";
		String zero2 = "";
		if(uhrSek){
				 if(timeP1.getLimit() < 10) zero = "000";
			else if(timeP1.getLimit() < 100) zero = "00";
			else if(timeP1.getLimit() < 1000) zero = "0";
			else zero = "";
			gBuffer.drawString(zero + timeP1.getLimit(), scale(1115) + abstX+width, (int)(787*width/100) + abstY);
		} else{
			zero2 = "";
			if((int)(timeP1.getLimit()/60)<10) zero2 = "0";
			zero = "";
			if((int)(timeP1.getLimit()%60)<10) zero = "0";
			gBuffer.drawString(zero2 + (int)(timeP1.getLimit()/60) + ":" + zero + (int)(timeP1.getLimit()%60), (int)(1107*width/100) + abstX+width, (int)(787*width/100) + abstY);
		}
		for (int i = 0; i < 4; i++)
			Button(l+ab*i, u, w, h, 4);
	}
	
//	public void Buf(int x, int y, int width, int abstX, int abstY){
//		gBuffer.fillRect(x * width + abstX, y * width + abstY, scale(30), 10*width/100);
//	}

	public void Schachfeld(){
		gBuffer.setColor(col[countColor[0] % col.length]);
		gBuffer.fillRect( abstX, abstY, width*8, width*8);
		gBuffer.setColor(Color.black);
		gBuffer.drawRect( abstX, abstY, width*8, width*8);
		gBuffer.setColor(col[countColor[1] % col.length]);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(Math.abs(i-j)%2 == 1) Field(i , j);
			}
		}
		for (int i = 0; i < 8; i ++) {
			if(!easterEgg[1]){
				gBuffer.setColor(Color.red);
				if(8 - i == 2 || 8 - i == 3 || 8 - i == 5 || 8 - i == 7)
					gBuffer.fillOval(width + abstX-width - scale(75), width * (i+1) + scale(27) + abstY-width, scale(50), scale(50));
			}
			if(!easterEgg[2]){
				gBuffer.setColor(Color.blue);
				if(8 - i == 1 || 8 - i == 2 || 8 - i == 3 || 8 - i == 5 ||  8 - i == 8)
					gBuffer.fillOval(width*10 + abstX-width - (int)(75*width/100), width * (i+1) + (int)(27*width/100) + abstY-width, scale(50), scale(50));
			}
			if(!easterEgg[3]){
				gBuffer.setColor(Color.CYAN);
				gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(23*width/100)));
				if(i == 0 || i == 1 || i == 2)
					gBuffer.drawString("2", width * i + (65*width/100) + abstX, width * 10 - (65*width/100) + abstY-width);
				gBuffer.setFont(new Font("Arial", Font.BOLD, scale(40)));
				if(i == 0) gBuffer.drawString("+", width * i + (85*width/100) + abstX, width * 10 - (37*width/100) + abstY-width);
				if(i == 1) gBuffer.drawString("=", width * i + (90*width/100) + abstX, width * 10 - (37*width/100) + abstY-width);
			}
			
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.PLAIN, scale(50)));
			gBuffer.drawString("" + Stones.ROW_K.charAt(i), width * i + scale(35) + abstX, width * 1 - scale(35) + abstY-width);
			gBuffer.drawString("" + (8-i), width - scale(65) + abstX-width, width * (i+1) + scale(70) + abstY-width);
			
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.PLAIN, 50*width/100));
			gBuffer.drawString("" + Stones.ROW_K.charAt(i), width * i + scale(35) + abstX, width * 10 - scale(35) + abstY-width);
			gBuffer.drawString("" + (8-i), width - scale(65) + abstX-width + width*9, width * (i+1) + scale(70) + abstY-width);
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
	
	public void Uhren(){
		// Spieler 1
		gBuffer.setColor(Color.yellow);
		gBuffer.fillOval(width * 8 + abstX+width, width * 6 + abstY, width * 2, width * 2);
		gBuffer.setColor(Color.black);
		gBuffer.fillOval(width * 8 + abstX + scale(5) +width, width * 6 + abstY + scale(5), width * 2 - (int)(10*width/100), width * 2 - (int)(10*width/100));
		gBuffer.setColor(Color.yellow);
		gBuffer.fillOval(width * 8 + abstX + (int)(10*width/100) +width, width * 6 + abstY + (int)(10*width/100), width * 2 - (int)(20*width/100), width * 2 - (int)(20*width/100));
		if(!easterEgg[0]) easEgg.easterEggBvB2(); //TODO: integrieren
		gBuffer.setColor(Color.gray);
		gBuffer.fillArc(width * 8 + abstX+width, width * 6 + abstY, width * 2, width * 2, 0, (int) (((double)timeP1.getTime() / (double)timeP1.getTime()) * 360)-360);
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, 70*width/100));
		if(uhrSek){
			String zero = "";
			if(timeP1.getTime() < 10) zero = "000";
			else if(timeP1.getTime() < 100) zero = "00";
			else if(timeP1.getTime() < 1000) zero = "0";
			else zero = "";
			gBuffer.drawString(zero + timeP1.getTime(), (int)(width * 8.2) + abstX  * 2 -abstX+width, (int)(width * 5.6) + (int)(22*width/100) + abstY);
		}
		else{
			String zero2 = "";
			if((int)(timeP1.getTime()/60)<10) zero2 = "0";
			String zero = "";
			if(timeP1.getTime() % 60 < 10) zero = "0";
			gBuffer.drawString(zero2 + (int)(timeP1.getTime() / 60) + ":" + zero + timeP1.getTime() % 60, (int)(width * 8.12) + abstX  * 2 -abstX+width, (int)(width * 5.6) + (int)(22*width/100) + abstY);
		}
		
		// Spieler 2
		gBuffer.setColor(Color.yellow);
		gBuffer.fillOval(width * 8 + abstX+width, width * 0 + abstY, width * 2, width * 2);
		gBuffer.setColor(Color.black);
		gBuffer.fillOval(width * 8 + abstX+ scale(5) +width, width * 0 + abstY + scale(5), width * 2 - (int)(10*width/100), width * 2 - (int)(10*width/100));
		gBuffer.setColor(Color.yellow);
		gBuffer.fillOval(width * 8 + abstX+ (int)(10*width/100) +width, width * 0 + abstY + (int)(10*width/100), width * 2 - (int)(20*width/100), width * 2 - (int)(20*width/100));
		if(!easterEgg[0]) easEgg.easterEggBvB1(); //TODO: integrieren
		gBuffer.setColor(Color.gray);
		gBuffer.fillArc(width * 8 + abstX+width, width * 0 + abstY, width * 2, width * 2, 0, (int) (((double)timeP2.getTime() / (double)timeP1.getTime()) * 360)-360);
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, 70*width/100));
		if(uhrSek){
			String zero = "";
			if(timeP2.getTime() < 10) zero = "000";
			else if(timeP2.getTime() < 100) zero = "00";
			else if(timeP2.getTime() < 1000) zero = "0";
			else zero = "";
			gBuffer.drawString(zero + timeP2.getTime(), (int)(width * 8.2) + abstX  * 2 -abstX+width, (int)(width * 2.5) + (int)(22*width/100) + abstY);
		}
		else{
			String zero2 = "";
			if((int)(timeP2.getTime() / 60) < 10) zero2 = "0";
			String zero = "";
			if(timeP2.getTime() % 60 < 10) zero = "0";
			gBuffer.drawString(zero2 + (int)(timeP2.getTime() / 60) + ":" + zero + timeP2.getTime() % 60, (int)(width * 8.12) + abstX  * 2 -abstX+width, (int)(width * 2.5) + (int)(22*width/100) + abstY);
		}
		
		//Zuege Zaehler
		gBuffer.setColor(col[countColor[0]%col.length]);
		gBuffer.fillRoundRect(10*width + abstX-width, 5*width + abstY-width, (int)(100*width/100), (int)(100*width/100), quaX, quaY);
		gBuffer.fillRoundRect(11*width + abstX-width, 4*width + abstY-width, (int)(100*width/100), (int)(100*width/100), quaX, quaY);
		gBuffer.setColor(col[countColor[1]%col.length]);
		gBuffer.fillRoundRect(10*width + abstX-width, 4*width + abstY-width, (int)(100*width/100), (int)(100*width/100), quaX, quaY);
		gBuffer.fillRoundRect(11*width + abstX-width, 5*width + abstY-width, (int)(100*width/100), (int)(100*width/100), quaX, quaY);
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
		gBuffer.setFont(new Font("Arial", Font.PLAIN, 40*width/100));
		String zero = "";
		if((player-1)<10) zero = "000";
		else if((player-1)<100) zero = "00";
		else if((player-1)<1000) zero = "0";
		else zero = "";
		gBuffer.drawString(zero + (player-1), (int)(width * 9.56) + abstX, width * 4 + scale(15) + abstY);
		if(game){
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.PLAIN, 65*width/100));
			gBuffer.drawString(letzterZug, (int)(800*width/100) + abstX+width, (int)(870*width/100) + abstY);
		}
	}
}
