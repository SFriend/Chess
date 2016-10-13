package com.company.elo;

/**
 * Created by pdk on 09.10.16.
 */
public class PvP {
    int p1, p2;
    public PvP(int p1, int p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    int getP1(){
        return p1;
    }
    int getP2(){
        return p2;
    }
    void setP1(int elo){
        this.p1 += elo;
    }
    void setP2(int elo){
        this.p2 += elo;
    }
}
