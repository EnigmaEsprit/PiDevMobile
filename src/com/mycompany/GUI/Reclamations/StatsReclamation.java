/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.GUI.Reclamations;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entites.Reclamations.Statisques;
import com.mycompany.service.Reclamations.ServiceReclamations;
import java.util.ArrayList;

/**
 *
 * @author Ivan Landry ONANA
 */
public class StatsReclamation {
    /**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(20);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param title
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, ArrayList<Statisques> values) {
    CategorySeries series = new CategorySeries(title);
    for (Statisques value : values) {
        series.add(value.getEtat(), value.getNombre());
    }

    return series;
}

public Form createPieChartForm() {
    // Generate the values
    ServiceReclamations ser = new ServiceReclamations();
    ArrayList<Statisques> values = ser.showStats();

    // Set up the renderer
    int[] colors;

    switch(values.size()){
        case 1: 
            colors = new int[]{0x65C8DC}; break;
        case 2: colors = new int[]{0x65C8DC, 0xFF7800}; break;
        default: colors = new int[]{0x65C8DC, 0xFF7800, ColorUtil.GREEN};
    }    
    
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(false);
    renderer.setLabelsColor(0);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Traitement des Réclamations", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Statistiques réclamations", new BorderLayout());
    f.getStyle().setMarginLeft(3);
        Toolbar tb = f.getToolbar();
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (evt) -> {
            HomeForm h = new HomeForm();
            h.getAccueil().show();
        });
        tb.addMaterialCommandToSideMenu("Consulter réclamations", FontImage.MATERIAL_LIBRARY_BOOKS, (evt) -> {
            ConsulterReclamationsVendeur con = new ConsulterReclamationsVendeur();
            con.getConsulterReclamations().show();
        });
        tb.addMaterialCommandToSideMenu("Consulter les statistiques", FontImage.MATERIAL_EQUALIZER, (evt3) -> {
            StatsReclamation con = new StatsReclamation();
            con.createPieChartForm().show();
        });
    
//    Container g = new Container(BoxLayout.y());
//    c.getStyle().setMarginLeft(10);
//    Label l = new Label("Réclamations : ");
//    l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
//    g.addAll(l,c);
    f.add(BorderLayout.CENTER, c);
    return f;
}
    
}
