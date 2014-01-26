package it.unical.mat.controller;

import java.util.List;

import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Administrator;

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
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plots;

import static com.googlecode.charts4j.Color.*;



@Controller
public class AdministratorController {

	@RequestMapping("/ShowStatistics")
    public String indexHandler(Model model, HttpSession session) {
		if(session.getAttribute("user")!=null && (Administrator)(session.getAttribute("user"))!=null){
			RegisteredUserMapper rm=new RegisteredUserMapper();
			List<?> l=rm.findMemberByMonth();
			for (Object object : l) {
				System.out.println(object.toString());
			}
			// EXAMPLE CODE START
	        // Defining data plots.
	        BarChartPlot team1 = Plots.newBarChartPlot(Data.newData(25, 43, 12, 30), BLUEVIOLET, "Team A");
	        BarChartPlot team2 = Plots.newBarChartPlot(Data.newData(8, 35, 11, 5), ORANGERED, "Team B");
	        BarChartPlot team3 = Plots.newBarChartPlot(Data.newData(10, 20, 30, 30), LIMEGREEN, "Team C");

	        // Instantiating chart.
	        BarChart chart = GCharts.newBarChart(team1, team2, team3);

	        // Defining axis info and styles
	        AxisStyle axisStyle = AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER);
	        AxisLabels score = AxisLabelsFactory.newAxisLabels("Score", 50.0);
	        score.setAxisStyle(axisStyle);
	        AxisLabels year = AxisLabelsFactory.newAxisLabels("Year", 50.0);
	        year.setAxisStyle(axisStyle);

	        // Adding axis info to chart.
	        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("2002", "2003", "2004", "2005"));
	        chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 100));
	        chart.addYAxisLabels(score);
	        chart.addXAxisLabels(year);

	        chart.setSize(600, 450);
	        chart.setBarWidth(100);
	        chart.setSpaceWithinGroupsOfBars(20);
	        chart.setDataStacked(true);
	        chart.setTitle("Team Scores", BLACK, 16);
	        chart.setGrid(100, 10, 3, 2);
	        chart.setBackgroundFill(Fills.newSolidFill(ALICEBLUE));
	        LinearGradientFill fill = Fills.newLinearGradientFill(0, LAVENDER, 100);
	        fill.addColorAndOffset(WHITE, 0);
	        chart.setAreaFill(fill);
	      
	        model.addAttribute("url", chart.toURLString());      
	        return "showStatisticsAdmin";
		}
		return "error";
    }
}
