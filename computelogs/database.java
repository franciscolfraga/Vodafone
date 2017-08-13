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
public class database {
    double percentagel8, percentagel18, percentagel26, avgl8, avgl18, avgl26, maxl8, maxl18, maxl26, minl8, minl18, minl26;
    double l8a, l8b, l8c, l8d, l8e, l8f, l8g, l8h, l8i, l8j;
    double l18a, l18b, l18c, l18d, l18e, l18f, l18g, l18h, l18i, l18j;
    double l26a, l26b, l26c, l26d, l26e, l26f, l26g, l26h, l26i, l26j;
    double tl8, tl18, tl26, avgt;
    double start, startvalue, avgdeg, avgintradeg, avginterdeg;
    int inter, intra;
    boolean myintra, myinter;
    ArrayList<Double> degradation;
    ArrayList<Double> interdeg;
    ArrayList<Double> intradeg;
    boolean ho;
    public database(){
        percentagel18=0;
        percentagel8=0;
        percentagel26=0;
        avgl8=0;
        avgl18=0;
        avgl26=0;
        avgt=0;
        maxl8=0;
        maxl18=0;
        maxl26=0;
        minl8=0;
        minl18=0;
        minl26=0;
        l8a=0; l8b=0; l8c=0; l8d=0; l8e=0; l8f=0; l8g=0; l8h=0; l8i=0; l8j=0;
        l18a=0; l18b=0; l18c=0; l18d=0; l18e=0; l18f=0; l18g=0; l18h=0; l18i=0; l18j=0;
        l26a=0; l26b=0; l26c=0; l26d=0; l26e=0; l26f=0; l26g=0; l26h=0; l26i=0; l26j=0;
        tl8=0; tl18=0; tl26=0;
        inter=0; intra=0;
        ho=false; start=0; startvalue=0; avgdeg=0; avgintradeg=0; avginterdeg=0;
        degradation = new ArrayList<>();
        interdeg = new ArrayList<>();
        intradeg = new ArrayList<>();
        myintra=false; myinter=false;
    }
    void incrementHO(String type){
        if(type.equals("intra")){
            intra++;
        } else if(type.equals("inter")){
            inter++;
        }   
    }
    int getHO(String type){
        if(type.equals("intra")){
            return intra;
        } else if(type.equals("inter")){
            return inter;
        }
        return 0;
    }
    void setpl8(double value){
        this.percentagel8=value;
    }
    void setpl18(double value){
        this.percentagel18=value;
    }
    void setpl26(double value){
        this.percentagel26=value;
    }
    double getpl8(){
        return this.percentagel8;
    }
    double getpl18(){
        return this.percentagel18;
    }
    double getpl26(){
        return this.percentagel26;
    }
    void setavgl8(double value){
        this.avgl8=value;
    }
    void setavgl18(double value){
        this.avgl18=value;
    }
    void setavgl26(double value){
        this.avgl26=value;
    }
    double getavgl8(){
        return this.avgl8;
    }
    double getavgl18(){
        return this.avgl18;
    }
    double getavgl26(){
        return this.avgl26;
    }
    void setminl8(double value){
        this.minl8=value;
    }
    void setminl18(double value){
        this.minl18=value;
    }
    void setminl26(double value){
        this.minl26=value;
    }
    void setmaxl8(double value){
        this.maxl8=value;
    }
    void setmaxl18(double value){
        this.maxl18=value;
    }
    void setmaxl26(double value){
        this.maxl26=value;
    }
    double getminl8(){
        return this.minl8;
    }
    double getminl18(){
        return this.minl18;
    }
    double getminl26(){
        return this.minl26;
    }
    double getmaxl8(){
        return this.maxl8;
    }
    double getmaxl18(){
        return this.maxl18;
    }
    double getmaxl26(){
        return this.maxl26;
    }
    void incrementtech(String tech, Double value){
        switch (tech) {
            case "l8":
                if(value <= 0.5){
                    l8a++;
                }
                else if(value > 0.5 && value <= 1){
                    l8b++;
                }
                else if(value > 1 && value <= 1.5){
                    l8c++;
                }
                else if(value > 1.5 && value <= 2){
                    l8d++;
                }
                else if(value > 2 && value <= 2.5){
                    l8e++;
                }
                else if(value > 2.5 && value <= 3){
                    l8f++;
                }
                else if(value > 3 && value <= 3.5){
                    l8g++;
                }
                else if(value > 3.5 && value <= 4){
                    l8h++;
                }
                else if(value > 4 && value <= 4.5){
                    l8i++;
                }
                else if(value > 4.5 && value <= 5){
                    l8j++;
                }
                break;
            case "l18":
                if(value <= 0.5){
                    l18a++;
                }
                else if(value > 0.5 && value <= 1){
                    l18b++;
                }
                else if(value > 1 && value <= 1.5){
                    l18c++;
                }
                else if(value > 1.5 && value <= 2){
                    l18d++;
                }
                else if(value > 2 && value <= 2.5){
                    l18e++;
                }
                else if(value > 2.5 && value <= 3){
                    l18f++;
                }
                else if(value > 3 && value <= 3.5){
                    l18g++;
                }
                else if(value > 3.5 && value <= 4){
                    l18h++;
                }
                else if(value > 4 && value <= 4.5){
                    l18i++;
                }
                else if(value > 4.5 && value <= 5){
                    l18j++;
                }
                break;
            case "l26":
                if(value <= 0.5){
                    l26a++;
                }
                else if(value > 0.5 && value <= 1){
                    l26b++;
                }
                else if(value > 1 && value <= 1.5){
                    l26c++;
                }
                else if(value > 1.5 && value <= 2){
                    l26d++;
                }
                else if(value > 2 && value <= 2.5){
                    l26e++;
                }
                else if(value > 2.5 && value <= 3){
                    l26f++;
                }
                else if(value > 3 && value <= 3.5){
                    l26g++;
                }
                else if(value > 3.5 && value <= 4){
                    l26h++;
                }
                else if(value > 4 && value <= 4.5){
                    l26i++;
                }
                else if(value > 4.5 && value <= 5){
                    l26j++;
                }
                break;
            default:
                break;
        }
    }
    void setTechTimes(String tech, int times){
        if(tech.equals("l8")){
            tl8=times;
        }
        else if(tech.equals("l18")){
            tl18=times;
        }
        else if(tech.equals("l26")){
            tl26=times;
        }
    }
}
