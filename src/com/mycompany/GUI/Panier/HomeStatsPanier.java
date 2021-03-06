/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Panier;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.io.Log;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.GUI.utilisateurs.LoginForm;
import com.mycompany.entites.Panier.Stats;
import com.mycompany.entites.Utilisateurs.User;
import com.mycompany.service.Panier.StatsService;
import com.mycompany.service.Utilisateurs.Util;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author jean
 */
public class HomeStatsPanier {
    
    Form statsForm ;
    
    public HomeStatsPanier() {
        
        statsForm= new Form("Stats Produits");
        
        
        StatsService stat = new StatsService();
              Command doneCommand = statsForm.getToolbar().addCommandToRightBar("Back", null,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
                               Log.p("Done pressed");
           
                    VendeurCommandesForm vcf;
                try {
                    vcf = new VendeurCommandesForm();
                     vcf.getPsdTutorial().show();
                } catch (IOException ex) {
                   // Logger.getLogger(HomeStatsPanier.class.getName()).log(Level.SEVERE, null, ex);
                }
                   

             
                
            }
        });
          double[] values = new double[]{12, 14, 11, 10, 19};    // Set up the renderer   
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN,ColorUtil.LTGRAY,0x000000};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);    // Create the chart ... pass the values and renderer to the chart object.    
        PieChart chart = new PieChart(buildCategoryDataset("Statistiques Produits", stat.getList2()), renderer);    // Wrap the chart in a Component so we can add it to a form    
        ChartComponent c = new ChartComponent(chart);    // Create a form and show it.    
        
        statsForm.setLayout(new BorderLayout());
        statsForm.addComponent(BorderLayout.CENTER, c);
        
    }
    
     private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected CategorySeries buildCategoryDataset(String title,  ArrayList<Stats> values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (Stats value : values) {
            series.add(value.getNomproduitStats(), value.getQuantiteStats());
        }
        return series;
    }

    

    public Form getStatsForm() {
        return statsForm;
    }

    public void setStatsForm(Form statsForm) {
        this.statsForm = statsForm;
    }
    
    
}
