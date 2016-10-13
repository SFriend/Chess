package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Menu extends Main {
	EasterEgg easEgg = new EasterEgg();
	
	public Menu(){
		Schachfeld();
		if (selection && !choseStone) { //Prints selection
			int secColor = 0;
			while (checkForColor2(secColor)) {
				secColor++;
			}
			gBuffer.setColor(col[secColor % col.length]);
			gBuffer.fillRect(mx1 * width + abstX, my1 * width + abstY, width, width);
		}
		new Stones();
		if(choseStone) new Stones().ChoseStone(choseStoneX, choseStoneY);
//		Settings();
//		Uhren();
	}
	
	public void Settings(){
		if(minsek%2==0) uhrSek = true;
		else uhrSek = false;
		
		//Start Stop Pause
		if(game){
			TextBox((int)(1140*width/100), (int)(-40*width/100), (int)(220*width/100), (int)(60*width/100), Color.orange);
			TextBox((int)(1262*width/100), (int)(-35*width/100), (int)(3*width/100), (int)(50*width/100), Color.black);
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(36*width/100)));
			gBuffer.drawString("Pause", (int)(1150*width/100) + abstX, (int)(3*width/100) + abstY);
			gBuffer.drawString("Stop", (int)(1270*width/100) + abstX, (int)(3*width/100) + abstY);
		} else {
			TextBox((int)(1140*width/100), (int)(-40*width/100), (int)(220*width/100), (int)(60*width/100), Color.orange);
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(40*width/100)));
			if(pause)
				gBuffer.drawString("Weiter", (int)(1190*width/100) + abstX, (int)(5*width/100) + abstY);
			else gBuffer.drawString("Start", (int)(1200*width/100) + abstX, (int)(5*width/100) + abstY);
		}
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(36*width/100)));
		gBuffer.drawString("Spielmodus", 11*width + (int)(45*width/100) + abstX, (int)(80*width/100) + abstY);
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(30*width/100)));
		gBuffer.drawString("SvS", (int)(1123*width/100) + abstX, (int)(140*width/100) + abstY);
		gBuffer.drawString("SvC", (int)(1193*width/100) + abstX, (int)(140*width/100) + abstY);
		gBuffer.drawString("CvS", (int)(1262*width/100) + abstX, (int)(140*width/100) + abstY);
		gBuffer.drawString("CvC", (int)(1332*width/100) + abstX, (int)(140*width/100) + abstY);
		gBuffer.setColor(Color.ORANGE);
		gBuffer.fillOval((int)(1140*width/100) + abstX, (int)(150*width/100) + abstY, (int)(25*width/100), (int)(25*width/100));
		gBuffer.fillOval((int)(1210*width/100) + abstX, (int)(150*width/100) + abstY, (int)(25*width/100), (int)(25*width/100));
		gBuffer.fillOval((int)(1280*width/100) + abstX, (int)(150*width/100) + abstY, (int)(25*width/100), (int)(25*width/100));
		gBuffer.fillOval((int)(1350*width/100) + abstX, (int)(150*width/100) + abstY, (int)(25*width/100), (int)(25*width/100));

		//Choise-Buttons
		gBuffer.setColor(Color.black);
		if(!computer && !computerVScomputer) gBuffer.fillOval((int)(1145*width/100) + abstX, (int)(150*width/100) + (int)(5*width/100) + abstY, (int)(15*width/100), (int)(15*width/100));
		else if(computer && !computerVScomputer) gBuffer.fillOval((int)(1215*width/100) + abstX, (int)(150*width/100) + (int)(5*width/100) + abstY, (int)(15*width/100), (int)(15*width/100));
		else if(!computer && computerVScomputer) gBuffer.fillOval((int)(1285*width/100) + abstX, (int)(150*width/100) + (int)(5*width/100) + abstY, (int)(15*width/100), (int)(15*width/100));
		else if(computer && computerVScomputer) gBuffer.fillOval((int)(1355*width/100) + abstX, (int)(150*width/100) + (int)(5*width/100) + abstY, (int)(15*width/100), (int)(15*width/100));
		
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(34*width/100)));
		gBuffer.drawString("Farbe der Felder", (int)(1126*width/100) + abstX, (int)(243*width/100) + abstY);

		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(27*width/100)));
		TextBox((int)(1180*width/100), (int)(260*width/100), (int)(150*width/100), (int)(60*width/100), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color[countColor1%color.length], (int)(1180*width/100) + abstX, (int)(303*width/100) + abstY);
		Button((int)(1140*width/100), (int)(260*width/100), (int)(30*width/100), (int)(60*width/100), 1);
		Button((int)(1345*width/100), (int)(260*width/100), (int)(30*width/100), (int)(60*width/100), 2);
		
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(27*width/100)));
		gBuffer.setColor(Color.ORANGE);
		TextBox((int)(1180*width/100), (int)(330*width/100), (int)(150*width/100), (int)(60*width/100), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color[countColor2%color.length], (int)(1180*width/100) + abstX, (int)(373*width/100) + abstY);
		Button((int)(1140*width/100), (int)(330*width/100), (int)(30*width/100), (int)(60*width/100), 1);
		Button((int)(1345*width/100), (int)(330*width/100), (int)(30*width/100), (int)(60*width/100), 2);
		
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(34*width/100)));
		gBuffer.drawString("Farbe der Figuren", (int)(1108*width/100) + abstX, (int)(463*width/100) + abstY);
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(27*width/100)));
		TextBox((int)(1180*width/100), (int)(480*width/100), (int)(150*width/100), (int)(60*width/100), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color[countColor3%color.length], (int)(1180*width/100) + abstX, (int)(523*width/100) + abstY);
		Button((int)(1140*width/100), (int)(480*width/100), (int)(30*width/100), (int)(60*width/100), 1);
		Button((int)(1345*width/100), (int)(480*width/100), (int)(30*width/100), (int)(60*width/100), 2);
		
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(27*width/100)));
		gBuffer.setColor(Color.ORANGE);
		TextBox((int)(1180*width/100), (int)(550*width/100), (int)(150*width/100), (int)(60*width/100), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.drawString(color[countColor4%color.length], (int)(1180*width/100) + abstX, (int)(593*width/100) + abstY);
		Button((int)(1140*width/100), (int)(550*width/100), (int)(30*width/100), (int)(60*width/100), 1);
		Button((int)(1345*width/100), (int)(550*width/100), (int)(30*width/100), (int)(60*width/100), 2);
		
		//Zeiteinstellen
		Button((int)(1105*width/100), (int)(740*width/100), (int)(98*width/100), (int)(50*width/100), 0);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, 40*width/100));
		gBuffer.setColor(Color.black);
		if(uhrSek)
			gBuffer.drawString("Sek", (int)(1119*width/100) + abstX, (int)(779*width/100) + abstY);
		else {
			gBuffer.setFont(new Font("Arial", Font.PLAIN, 20*width/100));
			gBuffer.drawString("Min:Sek", (int)(1118*width/100) + abstX, (int)(774*width/100) + abstY);
		}
		
		int l = (int)(1210*width/100); int o = (int)(700*width/100); int u = (int)(805*width/100);
		int w = (int)(35*width/100); int h = (int)(25*width/100);
		int ab = (int)(40*width/100);
		
//		Button(btn.getDim().x, btn.getDim().y, btn.getDim().width, btn.getDim().height,btn.getDim().n); //TODO: Button
		
		Button(l, o, w, h, 3);
		Button(l+ab, o, w, h, 3);
		Button(l+ab*2, o, w, h, 3);
		Button(l+ab*3, o, w, h, 3);

		TextBox((int)(1210*width/100), (int)(735*width/100), (int)(155*width/100), (int)(60*width/100), Color.orange);
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial", Font.PLAIN, 65*width/100));
		String zero = "";
		String zero2 = "";
		if(uhrSek){
			if(timeP1.getLimit() < 10) zero = "000";
			else if(timeP1.getLimit() < 100) zero = "00";
			else if(timeP1.getLimit() < 1000) zero = "0";
			else zero = "";
			gBuffer.drawString(zero + timeP1.getLimit(), (int)(1115*width/100) + abstX+width, (int)(787*width/100) + abstY);
		} else{
			zero2 = "";
			if((int)(timeP1.getLimit()/60)<10) zero2 = "0";
			zero = "";
			if((int)(timeP1.getLimit()%60)<10) zero = "0";
			gBuffer.drawString(zero2 + (int)(timeP1.getLimit()/60) + ":" + zero + (int)(timeP1.getLimit()%60), (int)(1107*width/100) + abstX+width, (int)(787*width/100) + abstY);
		}
		
		Button(l, u, w, h, 4);
		Button(l+ab, u, w, h, 4);
		Button(l+ab*2, u, w, h, 4);
		Button(l+ab*3, u, w, h, 4);
	}
	
//	public void Buf(int x, int y, int width, int abstX, int abstY){
//		gBuffer.fillRect(x * width + abstX, y * width + abstY, (int)(30*width/100), 10*width/100);
//	}

	public void Schachfeld(){
		for (int i = 0; i < 8; i += 2) {
			for (int j = 0; j < 8; j += 2) {
				gBuffer.setColor(col[countColor1%col.length]);
				gBuffer.fillRect(i * width + abstX, j * width + abstY, width, width);
				gBuffer.fillRect((i + 1) * width + abstX, j * width + width + abstY, width, width);
				gBuffer.setColor(col[countColor2%col.length]);
				gBuffer.fillRect(i * width + abstX, j * width + width + abstY, width, width);
				gBuffer.fillRect((i + 1) * width + abstX, j * width + abstY, width, width);
			}
		}
		for (int i = 0; i < 8; i ++) {
			gBuffer.setColor(Color.red);
			if(!easterEgg[1])
				if(8-i==2 || 8-i==3 || 8-i==5 || 8-i==7) gBuffer.fillOval(width + abstX-width - (int)(75*width/100), width * (i+1) + (int)(27*width/100) + abstY-width, (int)(50*width/100), (int)(50*width/100));
			gBuffer.setColor(Color.blue);
			if(!easterEgg[2])
				if(8-i==1 || 8-i==2 || 8-i==3 || 8-i==5 || 8-i==8) gBuffer.fillOval(width*10 + abstX-width - (int)(75*width/100), width * (i+1) + (int)(27*width/100) + abstY-width, (int)(50*width/100), (int)(50*width/100));
			gBuffer.setColor(Color.CYAN);
			if(!easterEgg[3]){
				gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(23*width/100)));
				if(i==0 || i==1 || i==2) gBuffer.drawString("2", width * i + (int)(65*width/100) + abstX, width * 10 - (int)(65*width/100) + abstY-width);
				gBuffer.setFont(new Font("Arial", Font.BOLD, (int)(40*width/100)));
				if(i==0) gBuffer.drawString("+", width * i + (int)(85*width/100) + abstX, width * 10 - (int)(37*width/100) + abstY-width);
				if(i==1) gBuffer.drawString("=", width * i + (int)(90*width/100) + abstX, width * 10 - (int)(37*width/100) + abstY-width);
			}
			
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.PLAIN, (int)(50*width/100)));
			gBuffer.drawString("" + ROW_K.charAt(i), width * i + (int)(35*width/100) + abstX, width * 1 - (int)(35*width/100) + abstY-width);
			gBuffer.drawString("" + (8-i), width - (int)(65*width/100) + abstX-width, width * (i+1) + (int)(70*width/100) + abstY-width);
			
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.PLAIN, 50*width/100));
			gBuffer.drawString("" + ROW_K.charAt(i), width * i + (int)(35*width/100) + abstX, width * 10 - (int)(35*width/100) + abstY-width);
			gBuffer.drawString("" + (8-i), width - (int)(65*width/100) + abstX-width + width*9, width * (i+1) + (int)(70*width/100) + abstY-width);
		}
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
		if(n==1){
			x1[0] = (int)(x + 20*width/60 + abstX);
			x1[1] = (int)(x + 40*width/60 + abstX);
			x1[2] = (int)(x + 40*width/60 + abstX);
			y1[0] = (int)(y + 15*height/30 + abstY);
			y1[1] = (int)(y + 5*height/30 + abstY);
			y1[2] = (int)(y + 25*height/30 + abstY);
		} else if(n==2){
			x1[0] = (int)(x + 40*width/60 + abstX);
			x1[1] = (int)(x + 20*width/60 + abstX);
			x1[2] = (int)(x + 20*width/60 + abstX);
			y1[0] = (int)(y + 15*height/30 + abstY);
			y1[1] = (int)(y + 5*height/30 + abstY);
			y1[2] = (int)(y + 25*height/30 + abstY);
		} else if(n==3){
			x1[0] = (int)(x + 15*width/30 + abstX);
			x1[1] = (int)(x + 5*width/30 + abstX);
			x1[2] = (int)(x + 25*width/30 + abstX);
			y1[0] = (int)(y + 20*height/60 + abstY);
			y1[1] = (int)(y + 40*height/60 + abstY);
			y1[2] = (int)(y + 40*height/60 + abstY);
		} else if(n==4){
			x1[0] = (int)(x + 15*width/30 + abstX);
			x1[1] = (int)(x + 5*width/30 + abstX);
			x1[2] = (int)(x + 25*width/30 + abstX);
			y1[0] = (int)(y + 40*height/60 + abstY);
			y1[1] = (int)(y + 20*height/60 + abstY);
			y1[2] = (int)(y + 20*height/60 + abstY);
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
		gBuffer.fillOval(width * 8 + abstX + (int)(5*width/100) +width, width * 6 + abstY + (int)(5*width/100), width * 2 - (int)(10*width/100), width * 2 - (int)(10*width/100));
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
		gBuffer.fillOval(width * 8 + abstX+ (int)(5*width/100) +width, width * 0 + abstY + (int)(5*width/100), width * 2 - (int)(10*width/100), width * 2 - (int)(10*width/100));
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
		gBuffer.setColor(col[countColor1%col.length]);
		gBuffer.fillRoundRect(10*width + abstX-width, 5*width + abstY-width, (int)(100*width/100), (int)(100*width/100), quaX, quaY);
		gBuffer.fillRoundRect(11*width + abstX-width, 4*width + abstY-width, (int)(100*width/100), (int)(100*width/100), quaX, quaY);
		gBuffer.setColor(col[countColor2%col.length]);
		gBuffer.fillRoundRect(10*width + abstX-width, 4*width + abstY-width, (int)(100*width/100), (int)(100*width/100), quaX, quaY);
		gBuffer.fillRoundRect(11*width + abstX-width, 5*width + abstY-width, (int)(100*width/100), (int)(100*width/100), quaX, quaY);
		gBuffer.setColor(Color.gray);
		gBuffer.fillOval(width * 8 + (int)(50*width/100) + abstX+width, width * 3 + (int)(50*width/100) + abstY, width, width);
		
//		if(game){
//			gBuffer.fillOval(width * 8 + (int)(50*width/100) + abstX+width, width * 3 + (int)(50*width/100) + abstY, width, width);
//			if (getPlayer() == 0) {
//				gBuffer.setColor(col[countColor3%col.length]);
//				if(computer) new Random(); //Computer //TODO: integrieren
//			} else {
//				gBuffer.setColor(col[countColor4%col.length]);
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
		gBuffer.drawString(zero + (player-1), (int)(width * 9.56) + abstX, width * 4 + (int)(15*width/100) + abstY);
		if(game){
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Arial", Font.PLAIN, 65*width/100));
			gBuffer.drawString(letzterZug, (int)(800*width/100) + abstX+width, (int)(870*width/100) + abstY);
		}
	}
}
