/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computelogs;

import java.util.ArrayList;

/**
 *
 * @author FragaF1
 */
public class cellRank {

    int cellid;
    ArrayList<Double> values;
    String band;

    public cellRank(int cellid, ArrayList<Double> values, String band) {
        this.values = values;
        this.cellid = cellid;
        this.band = band;
    }

    double getCellid() {
        return cellid;
    }

    String getBand() {
        return band;
    }

    void setCellid(int cellid) {
        this.cellid = cellid;
    }

    ArrayList<Double> getValues() {
        return values;
    }

    void addValue(double value) {
        values.add(value);
    }

    double getAvg() {
        double acumulator = 0;
        for (Double element : values) {
            acumulator += element;
        }
        return acumulator / values.size();
    }
}
