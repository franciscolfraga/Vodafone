/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computelogs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author FragaF1
 */
public class ComputeLogs {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String filename = "18jul\\afternoon.txt";
        getData getmyData = new getData(filename);
        System.out.println("Collecting data, may take a while...\n");
        getmyData.collect();
        //readCells("testing.csv");
        //writefull(filename);
    }

    private static void readCells(String filename) throws IOException {
        BufferedWriter out = null;
        String str = "";
        BufferedReader in = new BufferedReader(new FileReader("cells.txt"));
        ArrayList<String> cells = new ArrayList<>();
        while ((str = in.readLine()) != null) {
            String[] parameters = str.split(";");
            cells.add(parameters[0]);
        }
        try {
            FileWriter fstream = new FileWriter("cells.txt", true);
            out = new BufferedWriter(fstream);
            int cid = 0, earfcn = 0;
            in = new BufferedReader(new FileReader(filename));
            String mycid = "", myearfcn = "";
            str = "";
            int c = 0;
            while ((str = in.readLine()) != null) {
                String[] parameters = str.split(";");
                String myline = "";
                boolean ok = false;
                if (c > 0) {
                    for (String element : cells) {
                        if (element.equals(parameters[cid])) {
                            ok = true;
                        }
                    }
                }
                for (int j = 0; j < parameters.length; j++) {
                    if (parameters[j].equals("All-Serving Cell Identity[1]")) {
                        cid = j;
                    } else if (parameters[j].equals("All-Serving Cell DL EARFCN[1]")) {
                        earfcn = j;
                    }
                    if (j != (parameters.length - 1)) {
                        myline += parameters[j] + ";";
                    } else {
                        myline += parameters[j];
                    }
                }
                String band="";
                if (parameters[earfcn].equals("6300")) {
                    band = "LTE800";

                } else if (parameters[earfcn].equals("2950")) {
                    band = "LTE2600";

                } else if (parameters[earfcn].equals("1300")) {
                    band = "LTE1800";

                }
                
                if (ok == false && c>0) {
                    cells.add(parameters[cid]);
                    out.write(parameters[cid] + ";"+band+ "\r\n");
                }
                c++;
            }
        } catch (IOException e) {
            System.out.println("Can't write the cell file .txt");
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    private static void writefull(String filename) throws IOException {
        String str = "";
        BufferedReader in;
        try (PrintWriter writer = new PrintWriter("fullfile.csv", "UTF-8")){
            in = new BufferedReader(new FileReader(filename));
            str = "";
            int c1=0;
            while ((str = in.readLine()) != null) {
                String[] parameters = str.split("\\t");
                String myline = "";
                for(int c=0; c<parameters.length;c++)
                    writer.print(parameters[c]+";");
                writer.println();
                c1++;
                }
            
            
            
        } catch (IOException e) {
            System.out.println("Can't write the cell file .txt");
        } 
    }

}
