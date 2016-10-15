package com;

import print.ChessColor;
import print.ChessStones;

import java.awt.Color;
import java.awt.Font;

public class EasterEgg extends ChessStones {
	public void easterEggBvB1(){
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, (int)(95*width/100)));
		gBuffer.drawString("B", width * 8 + width  * 2 + (int)(25*width/100) -width+width, width * 1 + (int)(35*width/100) + width);
		gBuffer.drawString("V", width * 8 + width  * 2 + (int)(75*width/100) -width+width, width * 1 + 0 + width);
		gBuffer.drawString("B", width * 8 + width  * 2 + (int)(125*width/100) -width+width, width * 1 + (int)(35*width/100) + width);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, (int)(50*width/100)));
		gBuffer.drawString("09", width * 8 + width  * 2 + (int)(80*width/100) -width+width, width * 1 + (int)(75*width/100) + width);
	}

	public void easterEggBvB2(){
		gBuffer.setColor(Color.black);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, (int)(95*width/100)));
		gBuffer.drawString("B", width * 8 + width  * 2 + (int)(25*width/100) -width+width, width * 7 + (int)(35*width/100) + width);
		gBuffer.drawString("V", width * 8 + width  * 2 + (int)(75*width/100) -width+width, width * 7 + 0 + width);
		gBuffer.drawString("B", width * 8 + width  * 2 + (int)(125*width/100) -width+width, width * 7 + (int)(35*width/100) + width);
		gBuffer.setFont(new Font("Arial Narrow", Font.BOLD, (int)(50*width/100)));
		gBuffer.drawString("09", width * 8 + width  * 2 + (int)(80*width/100) -width+width, width * 7 + (int)(75*width/100) + width);
	}

}