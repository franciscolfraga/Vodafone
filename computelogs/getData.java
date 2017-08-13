/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computelogs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import javax.swing.JFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author FragaF1
 */
public class getData {

    String filename;

    database bd;

    public getData(String filename) {
        this.filename = filename;
        bd = new database();
    }

    public void collect() throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String str;
        int c = 0, ihour = 0, imin = 0, polqadl = 0, cell1 = 0, earfcn = 0, t8 = 0, t18 = 0, t26 = 0, tt = 0, longi = 0, lati = 0, cid = 0, eq = 0, event = 0;
        double isecs = 0, prevtime = 0, havepolqa = 0, maxl8 = -99999, maxl18 = -99999, maxl26 = -99999, minl8 = 99999, minl18 = 99999, minl26 = 99999;
        double ant = 0, a8 = 0, a18 = 0, a26 = 0, at = 0, antavg = 0, antavgtime = 0;
        try (PrintWriter mywriter = new PrintWriter("mymap.kml", "UTF-8")) {
            DecimalFormat df = new DecimalFormat("#0.000");
            String initial = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                    + "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
                    + "<Document>\n"
                    + "<Style id='q5'>\n"
                    + "  <IconStyle>\n"
                    + "  <color>ff339900</color>\n"
                    + "	<scale>0.5</scale>\n"
                    + "	<Icon>\n"
                    + "	  <href>https://sites.google.com/site/pynetmony/home/iconrxl.png</href>\n"
                    + "	</Icon>\n"
                    + "  </IconStyle>\n"
                    + "</Style>\n"
                    + "<Style id='q4'>\n"
                    + "  <IconStyle>\n"
                    + "  <color>ff33ff99</color>\n"
                    + "	<scale>0.5</scale>\n"
                    + "	<Icon>\n"
                    + "	  <href>https://sites.google.com/site/pynetmony/home/iconrxl.png</href>\n"
                    + "	</Icon>\n"
                    + "  </IconStyle>\n"
                    + "</Style>\n"
                    + "<Style id='q3'>\n"
                    + "  <IconStyle>\n"
                    + "  <color>ff33ffff</color>\n"
                    + "	<scale>0.5</scale>\n"
                    + "	<Icon>\n"
                    + "	  <href>https://sites.google.com/site/pynetmony/home/iconrxl.png</href>\n"
                    + "	</Icon>\n"
                    + "  </IconStyle>\n"
                    + "</Style>\n"
                    + "<Style id='q2'>\n"
                    + "  <IconStyle>\n"
                    + "  <color>ff3399ff</color>\n"
                    + "	<scale>0.5</scale>\n"
                    + "	<Icon>\n"
                    + "	  <href>https://sites.google.com/site/pynetmony/home/iconrxl.png</href>\n"
                    + "	</Icon>\n"
                    + "  </IconStyle>\n"
                    + "</Style>\n"
                    + "<Style id='q1'>\n"
                    + "  <IconStyle>\n"
                    + "  <color>ff3333ff</color>\n"
                    + "	<scale>0.5</scale>\n"
                    + "	<Icon>\n"
                    + "	  <href>https://sites.google.com/site/pynetmony/home/iconrxl.png</href>\n"
                    + "	</Icon>\n"
                    + "  </IconStyle>\n"
                    + "</Style>\n"
                    + "<Style id='handover'>\n"
                    + "  <IconStyle>\n"
                    + "  <color>64000000</color>\n"
                    + "	<scale>1.0</scale>\n"
                    + "	<Icon>\n"
                    + "	  <href>https://sites.google.com/site/pynetmony/home/iconrxl.png</href>\n"
                    + "	</Icon>\n"
                    + "  </IconStyle>\n"
                    + "</Style>";
            mywriter.println(initial);
            try (PrintWriter writer = new PrintWriter("testing.csv", "UTF-8")) {
                ArrayList< myPair> polqa = new ArrayList<>();
                ArrayList<cellRank> celllist = new ArrayList<>();
                int myok = 0;
                String prevband = "", prevcell = "";
                int mytest = 0;
                while ((str = in.readLine()) != null) {

                    int chour = 0, cmin = 0;
                    double csecs = 0, ctime = 0;
                    String[] parameters = str.split("\\t");

                    if (c != 0) {
                        if (parameters[eq].equals("EQ1")) {
                            String[] grand = parameters[0].split(":");
                            chour = Integer.parseInt(grand[0]);
                            cmin = Integer.parseInt(grand[1]);
                            csecs = Double.parseDouble(grand[2]);

                            if (c == 1) {
                                ihour = Integer.parseInt(grand[0]);
                                imin = Integer.parseInt(grand[1]);
                                isecs = Double.parseDouble(grand[2]);

                            }
                            ctime = (csecs - isecs) + (cmin - imin) * 60 + (chour - ihour) * 3600;
                            if (c == 1) {
                                prevtime = ctime;
                            }
                            if (parameters.length > polqadl) {
                                if (!parameters[polqadl].equals("")) {
                                    polqa.add(new myPair(Double.parseDouble(parameters[polqadl]), ctime));
                                    havepolqa = ctime;
                                }

                                ListIterator<myPair> iter = polqa.listIterator();
                                while (iter.hasNext()) {
                                    if (abs(ctime - iter.next().getTime()) > 0.1) {
                                        iter.remove();
                                    }
                                }
                            }
                            boolean mymat = false;
                            if (filename.equals("3aug\\matosinhos.txt")) {
                                mymat = true;
                            }
                            if (parameters.length > cell1) {
                                if (myok == 0) {
                                    if (parameters[earfcn].equals("6300")) {
                                        prevband = "LTE800";
                                    } else if (parameters[earfcn].equals("2950")) {
                                        prevband = "LTE2600";
                                    } else if (parameters[earfcn].equals("1300")) {
                                        prevband = "LTE1800";
                                    }
                                    prevcell = parameters[cid];
                                    myok++;
                                }
                                if (!mymat || (chour >= 10 && cmin >= 45) || chour >= 11) {
                                    if (!parameters[cell1].equals("") && abs(ctime - prevtime) >= 0.1 && abs(ctime - havepolqa) <= 0.1) {
                                        String myline = "";
                                        double acumulator = 0, avg = 0;
                                        int times = 0;
                                        prevtime = ctime;
                                        for (myPair element : polqa) {
                                            acumulator += element.getValue();
                                            times++;
                                        }
                                        avg = acumulator / times;
                                        parameters[polqadl] = "" + avg;

                                        at += avg;
                                        tt++;
                                        String band = "";
                                        boolean intra = false, inter = false;
                                        if (parameters[earfcn].equals("6300")) {
                                            band = "LTE800";
                                            t8++;
                                            a8 += avg;
                                            if (avg > maxl8) {
                                                maxl8 = avg;
                                            }
                                            if (avg < minl8) {
                                                minl8 = avg;
                                            }
                                            bd.incrementtech("l8", avg);
                                        } else if (parameters[earfcn].equals("2950")) {
                                            t26++;
                                            a26 += avg;
                                            band = "LTE2600";
                                            if (avg > maxl26) {
                                                maxl26 = avg;
                                            }
                                            if (avg < minl26) {
                                                minl26 = avg;
                                            }
                                            bd.incrementtech("l26", avg);
                                        } else if (parameters[earfcn].equals("1300")) {
                                            band = "LTE1800";
                                            t18++;
                                            a18 += avg;
                                            if (avg > maxl18) {
                                                maxl18 = avg;
                                            }
                                            if (avg < minl18) {
                                                minl18 = avg;
                                            }
                                            bd.incrementtech("l18", avg);
                                        }

                                        String myid = "";
                                        if (avg <= 1) {
                                            myid = "q1";
                                        } else if (avg <= 2) {
                                            myid = "q2";
                                        } else if (avg <= 3) {
                                            myid = "q3";
                                        } else if (avg <= 4) {
                                            myid = "q4";
                                        } else {
                                            myid = "q5";
                                        }
                                        if (abs(ctime - antavgtime) <= 15) {
                                            if (!prevband.equals(band)) {
                                                inter = true;
                                                parameters[event] = "Inter-Frequency Handover";
                                                bd.startvalue = avg;
                                                bd.start = ctime;
                                                bd.ho = true;
                                                bd.incrementHO("inter");
                                                bd.myinter = true;
                                                bd.myintra = false;
                                            } else if (!prevcell.equals(parameters[cid])) {
                                                intra = true;
                                                parameters[event] = "Intra-Frequency Handover";
                                                bd.ho = true;
                                                bd.start = ctime;
                                                bd.startvalue = avg;
                                                bd.incrementHO("intra");
                                                bd.myinter = false;
                                                bd.myintra = true;
                                            }
                                            if (bd.ho == true) {
                                                if (ctime - bd.start > 5) {
                                                    bd.ho = false;
                                                    bd.myinter = false;
                                                    bd.myintra = false;
                                                } else {
                                                    bd.degradation.add(avg - antavg);
                                                    if (bd.myinter) {
                                                        bd.interdeg.add(avg - antavg);
                                                    } else if (bd.myintra) {
                                                        bd.intradeg.add(avg - antavg);
                                                    }
                                                }
                                            }
                                        }
                                        for (int j = 0; j < parameters.length; j++) {

                                            if (j != (parameters.length - 1)) {
                                                myline += parameters[j] + ";";
                                            } else {
                                                myline += parameters[j];
                                            }
                                        }
                                        String towrite = "";
                                        towrite += "  <Placemark>\n"
                                                //+ "  <name>" + df.format(avg) + "</name>\n"
                                                + "	<description><![CDATA[Label: <b>" + band + "</b><br/>CID: <b>" + parameters[cid] + "</b><br/>TYPE: <b>LTE</b><br/>Time: <b>" + parameters[0] + "</b>]]></description>\n"
                                                + "	<styleUrl>#" + myid + "</styleUrl>\n"
                                                + "	 <Point>\n"
                                                + "	  <coordinates>" + parameters[longi] + "," + parameters[lati] + "</coordinates>\n"
                                                + "	 </Point>\n"
                                                + "  </Placemark>";
                                        prevcell = parameters[cid];
                                        prevband = band;
                                        boolean alarm = false;
                                        for (cellRank element : celllist) {
                                            if (element.getCellid() == Integer.parseInt(parameters[cid])) {
                                                element.addValue(avg);
                                                alarm = true;
                                                break;
                                            }
                                        }
                                        if (alarm == false) {
                                            ArrayList<Double> values = new ArrayList<>();
                                            values.add(avg);
                                            celllist.add(new cellRank(Integer.parseInt(parameters[cid]), values, band));
                                        }
                                        antavg = avg;
                                        antavgtime = ctime;
                                        writer.println(myline);
                                        mywriter.println(towrite);
                                    }
                                }
                            }
                        }
                    } else {
                        String myline = "";
                        for (int j = 0; j < parameters.length; j++) {

                            switch (parameters[j]) {
                                case "EQ":
                                    eq = j;
                                    break;
                                case "Event":
                                    event = j;
                                    break;
                                case "All-POLQA SWB Score Downlink[0]":
                                    polqadl = j;
                                    break;
                                case "All-Serving Cell RSRQ (dB)[1]":
                                    cell1 = j;
                                    break;
                                case "All-Serving Cell DL EARFCN[1]":
                                    earfcn = j;
                                    break;
                                case "All-Longitude":
                                    longi = j;
                                    break;
                                case "All-Latitude":
                                    lati = j;
                                    break;
                                case "All-Serving Cell Identity[1]":
                                    cid = j;
                                    break;
                                default:
                                    break;

                            }
                            if (j != (parameters.length - 1)) {
                                myline += parameters[j] + ";";
                            } else {
                                myline += parameters[j];
                            }
                        }
                        writer.println(myline);
                    }
                    c++;
                    ant = ctime;
                }
                if (t8 != 0) {
                    bd.setmaxl8(maxl8);
                    bd.setminl8(minl8);
                    bd.setpl8((double) 100 * t8 / (t8 + t26 + t18));
                    bd.setavgl8(a8 / t8);
                }
                if (t18 != 0) {
                    bd.setmaxl18(maxl18);
                    bd.setminl18(minl18);
                    bd.setpl18((double) 100 * t18 / (t8 + t26 + t18));
                    bd.setavgl18(a18 / t18);
                }
                if (t26 != 0) {
                    bd.setmaxl26(maxl26);
                    bd.setminl26(minl26);
                    bd.setpl26((double) 100 * t26 / (t8 + t26 + t18));
                    bd.setavgl26(a26 / t26);
                }
                bd.setTechTimes("l8", t8);
                bd.setTechTimes("l18", t18);
                bd.setTechTimes("l26", t26);
                System.out.println(" Some statistics:\n");
                double mydeg = 0;
                String percentages = " Percentage of time: \tl8 = " + df.format(bd.getpl8()) + "%\tl18 = " + df.format(bd.getpl18()) + "%\tl26 = " + df.format(bd.getpl26()) + "%\n";
                String avgquality = " Quality average: \tl8 = " + df.format(bd.getavgl8()) + "\tl18 = " + df.format(bd.getavgl18()) + "\tl26 = " + df.format(bd.getavgl26()) + "\n";
                String max = " Max. quality: \tl8 = " + df.format(bd.getmaxl8()) + "\tl18 = " + df.format(bd.getmaxl18()) + "\tl26 = " + df.format(bd.getmaxl26()) + "\n";
                String min = " Min. quality: \tl8 = " + df.format(bd.getminl8()) + "\tl18 = " + df.format(bd.getminl18()) + "\tl26 = " + df.format(bd.getminl26()) + "\n";
                String ho = " Handovers: \tIntra = " + bd.getHO("intra") + "\tInter = " + bd.getHO("inter") + "\n";
                double acumul = 0;
                for (Double element : bd.degradation) {
                    acumul += element;
                }
                bd.avgdeg = acumul / bd.degradation.size();
                acumul = 0;
                for (Double element : bd.intradeg) {
                    acumul += element;
                }
                bd.avgintradeg = acumul / bd.intradeg.size();
                acumul = 0;
                for (Double element : bd.interdeg) {
                    acumul += element;
                }
                BufferedWriter writer1 = null;
                try {
                    FileWriter fstream = new FileWriter("handovers.csv", true);
                    writer1 = new BufferedWriter(fstream);
                    writer1.write(";Collumn\r\nIntra-Frequency;" + bd.getHO("intra") + "\r\nInter-Frequency;" + bd.getHO("inter") + "\r\n\r\n");

                } catch (IOException e) {
                    System.out.println("Can't write the quality file .csv");
                } finally {
                    if (writer1 != null) {
                        writer1.close();
                    }
                }
                bd.avginterdeg = acumul / bd.interdeg.size();
                String deg = " Degradation: \tTotal Avg. = " + df.format(bd.avgdeg) + "\tIntra Avg. = " + df.format(bd.avgintradeg) + "\tInter Avg. = " + df.format(bd.avginterdeg) + "\n";
                
                try {
                    FileWriter fstream = new FileWriter("degradation.csv", true);
                    writer1 = new BufferedWriter(fstream);
                    writer1.write(";Collumn\r\nTotal avg. degradation;" + df.format(bd.avgdeg) + "\r\nInter-Frequency avg. degradation;" + df.format(bd.avginterdeg) +"\r\nIntra-Frequency avg. degradation;" + df.format(bd.avgintradeg) + "\r\n\r\n");

                } catch (IOException e) {
                    System.out.println("Can't write the degradation file .csv");
                } finally {
                    if (writer1 != null) {
                        writer1.close();
                    }
                }
                System.out.println(percentages);
                System.out.println(avgquality);
                System.out.println(max);
                System.out.println(min);
                System.out.println(ho);
                System.out.println(deg);
                in.close();
                bd.avgt = at / tt;

                mywriter.println("</Document>\n"
                        + "</kml>");
                writer.close();
                mywriter.close();

                Collections.sort(celllist, new Comparator<cellRank>() {
                    @Override
                    public int compare(cellRank lhs, cellRank rhs) {
                        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                        return lhs.getAvg() > rhs.getAvg() ? -1 : (lhs.getAvg() < rhs.getAvg()) ? 1 : 0;
                    }
                });
                charts(bd, celllist);
            } catch (IOException e) {
                System.out.println("Erro a escrever no ficheiro .csv");
            }
        } catch (IOException e) {
            System.out.println("Erro a escrever no ficheiro .kml");
        }
    }

    private void charts(database db, ArrayList<cellRank> celllist) throws IOException {
        JFrame myframe = new JFrame();
        PieChart demo = new PieChart("Band distribution", db);
        demo.setSize(560, 400);
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        DualAxis demo1 = new DualAxis("Quality distribution / Band", db, 1, null);
        DualAxis demo2 = new DualAxis("Quality limits / Band", db, 2, null);
        DualAxis demo3 = new DualAxis("Quality Rank distribution / Cell id", db, 3, celllist);
        DualAxis demo4 = new DualAxis("Degradation Average / Handover Type", db, 4, null);
        demo1.pack();
        RefineryUtilities.centerFrameOnScreen(demo1);
        demo1.setVisible(true);
        demo2.pack();
        RefineryUtilities.centerFrameOnScreen(demo2);
        demo2.setVisible(true);
        demo3.pack();
        RefineryUtilities.centerFrameOnScreen(demo3);
        demo3.setVisible(true);
        demo4.pack();
        RefineryUtilities.centerFrameOnScreen(demo4);
        demo4.setVisible(true);
    }
}
