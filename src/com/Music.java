package com;

import javax.sound.sampled.LineUnavailableException;

public class Music {
	String musicScore = "";
	int note, tempo;
	String tonLeiter = "CDEFGAH";

	public Music() throws LineUnavailableException{
		//musicScore = "C3 E3 G2 C3 E2 G4 F2";
		random();
		for(int i = 0; i < musicScore.length(); i+=3){
			System.out.print(musicScore.charAt(i) +""+ musicScore.charAt(i+1) + " ");
			switch(musicScore.charAt(i)){
				case 'C' : note = 1000; break;
				case 'D' : note = 1100; break;
				case 'E' : note = 1200; break;
				case 'F' : note = 1300; break;
				case 'G' : note = 1400; break;
				case 'A' : note = 1500; break;
				case 'H' : note = 1600; break;
				default : System.out.println("none note"); break;
			}
			switch(musicScore.charAt(i+1)){
				case '1' : tempo = 600; break;
				case '2' : tempo = 500; break;
				case '3' : tempo = 400; break;
				case '4' : tempo = 300; break;
				default : System.out.println("none tempo"); break;
			}
			Tone.sound(note, tempo, 0.05);
		}
	}
	
	public void random(){
		for(int i = 0; i < 100; i++){
			int ranNote = (int)(Math.random()*7+1);
			int ranTempo = (int)(Math.random()*4+1);
			musicScore += tonLeiter.charAt(ranNote-1) + "" + ranTempo + " ";
			System.out.println(tonLeiter.charAt(ranNote-1) + " " + ranTempo);
		}
		System.out.println(musicScore);
	}
	
	public static void main(String[] args) throws LineUnavailableException {
		new Music();
    }
}
