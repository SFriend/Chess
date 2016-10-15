package com;

public class Timer {
	int time;
	static int limit;
	public Timer(int limit){
		this.limit = limit;
		this.time = this.limit;
	}
	public void decrement(){
		if(time > 0) time--;
	}
	public void reset(){
		time = limit;
	}
//	public void limitAd(int t){
//		limit += t;
//	}
//	public void limitSub(int t){
//		limit -= t;
//	}

	public int getTime(){
		return time;
	}
	public int getLimit(){
		return limit;
	}
	public boolean finish(){
		return time <= 0;
	}
}
