/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computelogs;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author FragaF1
 */
public class PieChart extends ApplicationFrame{
    
    public PieChart(String title, database db) {
        super(title);
        setContentPane(createDemoPanel(db));
    }
    private static PieDataset createDataset(database db ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "LTE800" , new Double( db.getpl8() ) );  
      dataset.setValue( "LTE1800" , new Double( db.getpl18() ) );   
      dataset.setValue( "LTE2600" , new Double( db.getpl26() ) );    
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Band distribution",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel(database db ) {
      JFreeChart chart = createChart(createDataset( db) );  
      return new ChartPanel( chart ); 
   }
}
