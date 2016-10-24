package com;

import com.elo.Elo;

public class Player {
    public Elo elo = new Elo();
    private String id = "";
    private int generation;
    private Smart brain = new Smart();

    public Player(String id) {
        this.id += id + " ";
    }

    public Player clone() {
        Player clone = new Player(id+generation);
        clone.setElo(elo.clone());
        clone.setBrain(brain.clone());
        clone.setGeneration(generation);
        return clone;
    }

    public void mutate() {
        brain.mutate();
        generation++;
    }

    private void setElo(Elo elo) {
        this.elo = elo;
    }

    public void setBrain(Smart brain) {
        this.brain = brain;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public Smart getBrain() {
        return brain;
    }

    public int getGeneration() {
        return generation;
    }

    public String getId() {
        return id;
    }
}
