package com.raj.cabcompany.entities;

public abstract class User implements Comparable<User> {

    private String id;
    private int totalRides;
    private double avgRating;

    public User(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public double getAvgRating() {
        return avgRating;
    }

    public void updateAvgRating(double currentTripRating) {

        double totalScore = avgRating * totalRides;
        // new score gets current score added
        totalScore += currentTripRating;

        //update totalRides
        totalRides++;

        // calculate new average rating
        avgRating = totalScore/totalRides;

    }

    @Override
    public int compareTo(User that) {
        if(this.getAvgRating() < that.getAvgRating()) {
            return 1;
        } else if(this.getAvgRating() > that.getAvgRating()) {
            return -1;
        } else {
            return 0;
        }
    }


}
