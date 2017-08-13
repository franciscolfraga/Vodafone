/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computelogs;

/**
 *
 * @author FragaF1
 */
class myPair {

    double value, time;

    public myPair(double value, double time) {
        this.time = time;
        this.value = value;
    }

    double getTime() {
        return time;
    }

    void setTime(double time) {
        this.time = time;
    }

    double getValue() {
        return value;
    }

    void setValue(double value) {
        this.value = value;
    }
}
