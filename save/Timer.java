package com.company;

public class Timer {
	int time;
	int limit;
	public Timer(int limit){
		this.limit = limit;
		this.time = this.limit;
	}
	public void decrement(){
		time--;
	}
	public void reset(){
		time = limit;
	}
	public void limitAd(int t){
		limit += t;
	}
	public void limitSub(int t){
		limit -= t;
	}

	public void timeAd(int t){
		time += t;
	}
	public void timeSub(int t){
		time -= t;
	}
	
	
	public int getTime(){
		return time;
	}
	public int getLimit(){
		return limit;
	}
	public boolean finish(){
		return time == 0;
	}
}
