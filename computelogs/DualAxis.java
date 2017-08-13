/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computelogs;

import java.awt.Color;
import java.awt.Paint;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.max;
import java.util.ArrayList;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import static jdk.nashorn.internal.objects.NativeMath.min;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author FragaF1
 */
public class DualAxis extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    int red; //i.e. FF
    int green;
    int stepSize;

    public DualAxis(final String title, database db, int type, ArrayList<cellRank> celllist) throws IOException {

        super(title);

        final CategoryDataset dataset1 = createDataset1(db, type, celllist);
        red = 255; //i.e. FF
        green = 0;
        stepSize = (int) 255 / 5;
        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                "Quality limits / Band", // chart title
                "Band", // domain axis label
                "Quality", // range axis label
                dataset1, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips?
                false // URL generator?  Not required...
        );
        if (type == 1) {
            chart = ChartFactory.createBarChart(
                    "Quality distribution / Band", // chart title
                    "Band", // domain axis label
                    "Ammount (%)", // range axis label
                    dataset1, // data
                    PlotOrientation.VERTICAL,
                    true, // include legend
                    true, // tooltips?
                    false // URL generator?  Not required...
            );
        } else if (type == 3) {
            chart = ChartFactory.createBarChart(
                    "Quality distribution / Cell id", // chart title
                    "Cell Id", // domain axis label
                    "Quality", // range axis label
                    dataset1, // data
                    PlotOrientation.VERTICAL,
                    true, // include legend
                    true, // tooltips?
                    false // URL generator?  Not required...
            );
        } else if (type == 4) {
            chart = ChartFactory.createBarChart(
                    "Degradation Average / Handover Type", // chart title
                    "Handover Type", // domain axis label
                    "Degradation Average", // range axis label
                    dataset1, // data
                    PlotOrientation.VERTICAL,
                    true, // include legend
                    true, // tooltips?
                    false // URL generator?  Not required...
            );
        }

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
//        chart.getLegend().setAnchor(Legend.SOUTH);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        Color mypaint[];
        plot.mapDatasetToRangeAxis(1, 1);
        if (type == 1) {
            plot.getRenderer().setSeriesPaint(0, new Color(red, green, 0));
            updatecolor();
            plot.getRenderer().setSeriesPaint(1, new Color(red, green, 0));
            updatecolor();
            plot.getRenderer().setSeriesPaint(2, new Color(red, green, 0));
            updatecolor();
            plot.getRenderer().setSeriesPaint(3, new Color(red, green, 0));
            updatecolor();
            plot.getRenderer().setSeriesPaint(4, new Color(red, green, 0));
            updatecolor();
            plot.getRenderer().setSeriesPaint(5, new Color(red, green, 0));
            updatecolor();
            plot.getRenderer().setSeriesPaint(6, new Color(red, green, 0));
            updatecolor();
            plot.getRenderer().setSeriesPaint(7, new Color(red, green, 0));
            updatecolor();
            plot.getRenderer().setSeriesPaint(8, new Color(red, green, 0));
            updatecolor();
            plot.getRenderer().setSeriesPaint(9, new Color(red, green, 0));
            updatecolor();
            plot.getRenderer().setSeriesPaint(10, new Color(red, green, 0));
        }
        final LineAndShapeRenderer renderer2;
        renderer2 = new LineAndShapeRenderer();
        renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
        // OPTIONAL CUSTOMISATION COMPLETED.

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 400));
        setContentPane(chartPanel);

    }

    private CategoryDataset createDataset1(database db, int type, ArrayList<cellRank> celllist) throws IOException {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (type == 1) {
            // row keys...
            final String category1 = "LTE800";
            final String category2 = "LTE1800";
            final String category3 = "LTE2600";
            final String category4 = "Overall";

            // column keys...
            final String series1 = "0 - 0.5";
            final String series2 = "0.5 - 1";
            final String series3 = "1 - 1.5";
            final String series4 = "1.5 - 2";
            final String series5 = "2 - 2.5";
            final String series6 = "2.5 - 3";
            final String series7 = "3 - 3.5";
            final String series8 = "3.5 - 4";
            final String series9 = "4 - 4.5";
            final String series10 = "4.5 - 5";

            // create the dataset...
            dataset.addValue(100 * db.l8a / db.tl8, series1, category1);
            dataset.addValue(100 * db.l8b / db.tl8, series2, category1);
            dataset.addValue(100 * db.l8c / db.tl8, series3, category1);
            dataset.addValue(100 * db.l8d / db.tl8, series4, category1);
            dataset.addValue(100 * db.l8e / db.tl8, series5, category1);
            dataset.addValue(100 * db.l8f / db.tl8, series6, category1);
            dataset.addValue(100 * db.l8g / db.tl8, series7, category1);
            dataset.addValue(100 * db.l8h / db.tl8, series8, category1);
            dataset.addValue(100 * db.l8i / db.tl8, series9, category1);
            dataset.addValue(100 * db.l8j / db.tl8, series10, category1);

            dataset.addValue(100 * db.l18a / db.tl18, series1, category2);
            dataset.addValue(100 * db.l18b / db.tl18, series2, category2);
            dataset.addValue(100 * db.l18c / db.tl18, series3, category2);
            dataset.addValue(100 * db.l18d / db.tl18, series4, category2);
            dataset.addValue(100 * db.l18e / db.tl18, series5, category2);
            dataset.addValue(100 * db.l18f / db.tl18, series6, category2);
            dataset.addValue(100 * db.l18g / db.tl18, series7, category2);
            dataset.addValue(100 * db.l18h / db.tl18, series8, category2);
            dataset.addValue(100 * db.l18i / db.tl18, series9, category2);
            dataset.addValue(100 * db.l18j / db.tl18, series10, category2);

            dataset.addValue(100 * db.l26a / db.tl26, series1, category3);
            dataset.addValue(100 * db.l26b / db.tl26, series2, category3);
            dataset.addValue(100 * db.l26c / db.tl26, series3, category3);
            dataset.addValue(100 * db.l26d / db.tl26, series4, category3);
            dataset.addValue(100 * db.l26e / db.tl26, series5, category3);
            dataset.addValue(100 * db.l26f / db.tl26, series6, category3);
            dataset.addValue(100 * db.l26g / db.tl26, series7, category3);
            dataset.addValue(100 * db.l26h / db.tl26, series8, category3);
            dataset.addValue(100 * db.l26i / db.tl26, series9, category3);
            dataset.addValue(100 * db.l26j / db.tl26, series10, category3);

            dataset.addValue(100 * (db.l8a + db.l18a + db.l26a) / (db.tl8 + db.tl18 + db.tl26), series1, category4);
            dataset.addValue(100 * (db.l8b + db.l18b + db.l26b) / (db.tl8 + db.tl18 + db.tl26), series2, category4);
            dataset.addValue(100 * (db.l8c + db.l18c + db.l26c) / (db.tl8 + db.tl18 + db.tl26), series3, category4);
            dataset.addValue(100 * (db.l8d + db.l18d + db.l26d) / (db.tl8 + db.tl18 + db.tl26), series4, category4);
            dataset.addValue(100 * (db.l8e + db.l18e + db.l26e) / (db.tl8 + db.tl18 + db.tl26), series5, category4);
            dataset.addValue(100 * (db.l8f + db.l18f + db.l26f) / (db.tl8 + db.tl18 + db.tl26), series6, category4);
            dataset.addValue(100 * (db.l8g + db.l18g + db.l26g) / (db.tl8 + db.tl18 + db.tl26), series7, category4);
            dataset.addValue(100 * (db.l8h + db.l18h + db.l26h) / (db.tl8 + db.tl18 + db.tl26), series8, category4);
            dataset.addValue(100 * (db.l8i + db.l18i + db.l26i) / (db.tl8 + db.tl18 + db.tl26), series9, category4);
            dataset.addValue(100 * (db.l8j + db.l18j + db.l26j) / (db.tl8 + db.tl18 + db.tl26), series10, category4);
            BufferedWriter writer = null;
            try {
                FileWriter fstream = new FileWriter("quality.csv", true);
                writer = new BufferedWriter(fstream);
                writer.write(";" + series1 + ";" + series2 + ";" + series3 + ";" + series4 + ";" + series5 + ";" + series6 + ";" + series7 + ";" + series8 + ";" + series9 + ";" + series10 + "\r\n");
                writer.write("Overall;" + dataset.getValue(series1, category4) + ";" + dataset.getValue(series2, category4) + ";" + dataset.getValue(series3, category4) + ";" + dataset.getValue(series4, category4) + ";" + dataset.getValue(series5, category4) + ";" + dataset.getValue(series6, category4) + ";" + dataset.getValue(series7, category4) + ";" + dataset.getValue(series8, category4) + ";" + dataset.getValue(series9, category4) + ";" + dataset.getValue(series10, category4) + "\r\n");
                writer.write("LTE 800;" + 100 * db.l8a / db.tl8 + ";" + 100 * db.l8b / db.tl8 + ";" + 100 * db.l8c / db.tl8 + ";" + 100 * db.l8d / db.tl8 + ";" + 100 * db.l8e / db.tl8 + ";" + 100 * db.l8f / db.tl8 + ";" + 100 * db.l8g / db.tl8 + ";" + 100 * db.l8h / db.tl8 + ";" + 100 * db.l8i / db.tl8 + ";" + 100 * db.l8j / db.tl8 + "\r\n");
                writer.write("LTE 1800;" + 100 * db.l18a / db.tl18 + ";" + 100 * db.l18b / db.tl18 + ";" + 100 * db.l18c / db.tl18 + ";" + 100 * db.l18d / db.tl18 + ";" + 100 * db.l18e / db.tl18 + ";" + 100 * db.l18f / db.tl18 + ";" + 100 * db.l18g / db.tl18 + ";" + 100 * db.l18h / db.tl18 + ";" + 100 * db.l18i / db.tl18 + ";" + 100 * db.l18j / db.tl18 + "\r\n");
                writer.write("LTE 2600;" + 100 * db.l26a / db.tl26 + ";" + 100 * db.l26b / db.tl26 + ";" + 100 * db.l26c / db.tl26 + ";" + 100 * db.l26d / db.tl26 + ";" + 100 * db.l26e / db.tl26 + ";" + 100 * db.l26f / db.tl26 + ";" + 100 * db.l26g / db.tl26 + ";" + 100 * db.l26h / db.tl26 + ";" + 100 * db.l26i / db.tl26 + ";" + 100 * db.l26j / db.tl26 + "\r\n\r\n");

            } catch (IOException e) {
                System.out.println("Can't write the quality file .txt");
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }

        } else if (type == 2) {
            // row keys...
            final String series1 = "Minimum";
            final String series2 = "Average";
            final String series3 = "Maximum";

            // column keys...
            final String category1 = "LTE800";
            final String category2 = "LTE1800";
            final String category3 = "LTE2600";
            final String category4 = "Overall";

            dataset.addValue(db.getminl8(), series1, category1);
            dataset.addValue(db.getminl18(), series1, category2);
            dataset.addValue(db.getminl26(), series1, category3);
            double min2=0;
            min2=min(double.class, db.getminl26(), db.getminl18());
            min2=min(double.class, min2, db.getminl18());
            dataset.addValue(min2, series1, category4);

            dataset.addValue(db.getavgl8(), series2, category1);
            dataset.addValue(db.getavgl18(), series2, category2);
            dataset.addValue(db.getavgl26(), series2, category3);
            dataset.addValue(db.avgt, series2, category4);

            dataset.addValue(db.getmaxl8(), series3, category1);
            dataset.addValue(db.getmaxl18(), series3, category2);
            dataset.addValue(db.getmaxl26(), series3, category3);
            double max1 = max(db.getmaxl8(), db.getmaxl26());
            double max2 = max(max1, db.getmaxl18());
            dataset.addValue(max2, series3, category4);
            BufferedWriter writer1 = null;
                try {
                    FileWriter fstream = new FileWriter("qualitylimits.csv", true);
                    writer1 = new BufferedWriter(fstream);
                    writer1.write(";Minimum;Average;Maximum\r\n");
                    writer1.write("Overall;"+min2+";"+db.avgt+";"+max2+"\r\n");
                    writer1.write("LTE 800;"+db.getminl8()+";"+db.getavgl8()+";"+db.getmaxl8()+"\r\n");
                    writer1.write("LTE 1800;"+db.getminl18()+";"+db.getavgl18()+";"+db.getmaxl18()+"\r\n");
                    writer1.write("LTE 2600;"+db.getminl26()+";"+db.getavgl26()+";"+db.getmaxl26()+"\r\n");

                } catch (IOException e) {
                    System.out.println("Can't write the quality file .csv");
                } finally {
                    if (writer1 != null) {
                        writer1.close();
                    }
                }

        } else if (type == 4) {
            // row keys...
            final String series1 = "Average";
            // column keys...
            final String category1 = "Overall";
            final String category2 = "InterFreq";
            final String category3 = "IntraFreq";

            dataset.addValue(db.avgdeg, series1, category1);
            dataset.addValue(db.avginterdeg, series1, category2);
            dataset.addValue(db.avgintradeg, series1, category3);

        } else {
            for (cellRank element : celllist) {
                dataset.addValue(element.getAvg(), "" + element.getBand(), "" + (int) element.getCellid());
            }
        }

        return dataset;

    }

    private void updatecolor() {
        if (green < 255) {
            green += stepSize;
            if (green > 255) {
                green = 255;
            }
        } else if (red > 0) {
            red -= stepSize;
            if (red < 0) {
                red = 0;
            }
        }

    }

}
