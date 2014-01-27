package it.unical.mat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.service.ParseDate;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Country;
import com.googlecode.charts4j.Country.Code;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.GeographicalArea;
import com.googlecode.charts4j.MapChart;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.PoliticalBoundary;
import static com.googlecode.charts4j.Color.*;

@Controller
public class AdministratorController {

//	@RequestMapping("/ShowStatistics")
    public String registrationByMonthChart(Model model, HttpSession session) {
		if(session.getAttribute("admin")!=null){
			RegisteredUserMapper rm=new RegisteredUserMapper();
			HashMap<String, Integer> result=rm.findMemberByMonth();
			// EXAMPLE CODE START
	        // Defining data plots.
			List<Integer> data=new ArrayList<Integer>();
			for (String s: ParseDate.months) {
				if(result.get(s)!=null)
					data.add(result.get(s));
				else //Giusto per prova FIXME va eliminato
					data.add((int) ((Math.random())*100));
			}
			System.out.println(data);
			BarChartPlot barPlot=Plots.newBarChartPlot(Data.newData(data), LIMEGREEN, "Registrazioni");
	        BarChart chart = GCharts.newBarChart(barPlot);
	        
	        AxisStyle axisStyle = AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER);
	        AxisLabels year = AxisLabelsFactory.newAxisLabels("Mese", 50.0);
	        year.setAxisStyle(axisStyle);

	        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(ParseDate.months));
	        chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 100));
	        chart.addXAxisLabels(year);

	        chart.setSize(689, 435);
	        chart.setBarWidth(40);
	        chart.setSpaceWithinGroupsOfBars(7);
	        chart.setDataStacked(true);
	        chart.setTitle("Registrazioni al mese nel 2014", BLACK, 16);
	        chart.setGrid(100, 10, 3, 2);
	        chart.setBackgroundFill(Fills.newSolidFill(WHITESMOKE));
	      
	        model.addAttribute("url", chart.toURLString());      
	        return "showStatisticsAdmin";
		}
		return "error";
    }
	
	@RequestMapping("/ShowStatistics")
    public String LiftByCityChart(Model model, HttpSession session) {
		if(session.getAttribute("admin")!=null){
//			RegisteredUserMapper rm=new RegisteredUserMapper();
//			HashMap<String, Integer> result=rm.findMemberByMonth();
	        MapChart chart = GCharts.newMapChart(GeographicalArea.valueOf("IT"));

	        PoliticalBoundary it = new Country(Code.IT, 10);
	        PoliticalBoundary es = new Country(Code.ES, 20);
	        PoliticalBoundary fr = new Country(Code.FR, 30);

	        chart.addPoliticalBoundaries(it,es,fr);
	        chart.setColorGradient(WHITE, RED, BLUE);
	        chart.setBackgroundFill(Fills.newSolidFill(ALICEBLUE));
	        String url = chart.toURLString();
	        
	        model.addAttribute("url", url);      
	        return "showStatisticsAdmin";
		}
		return "error";
    }
}
