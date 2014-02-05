package it.unical.mat.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.service.ParseDate;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.ComparatorUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Slice;

import static com.googlecode.charts4j.Color.*;

@Controller
public class AdministratorController {

	@RequestMapping("/ShowStatisticsRegistrationYear")
    public String registrationByYearChart(@RequestParam(value="year") String yearStat, Model model, HttpSession session) {
		if(session.getAttribute("admin")!=null){
			RegisteredUserMapper rm=new RegisteredUserMapper();
			HashMap<String, Integer> result=rm.findMemberByYear(Integer.parseInt(yearStat));
			BarChart chart = statsByYear(yearStat, result);
	      
	        model.addAttribute("url", chart.toURLString());      
	        return "showStatisticsAdmin";
		}
		return "error";
    }

    
	@RequestMapping("/ShowStatisticsRegistrationMonth")
    public String registrationByMonthChart(@RequestParam(value="year") String yearStat, 
    		@RequestParam(value="month") String monthStat,
    		Model model, HttpSession session) {
		if(session.getAttribute("admin")!=null){
			RegisteredUserMapper rm=new RegisteredUserMapper();
			HashMap<String, Integer> result=rm.findMemberByYear(Integer.parseInt(yearStat));
			BarChart chart = statsByMonthAndYear(yearStat, monthStat, result);
	      
	        model.addAttribute("url", chart.toURLString());      
	        return "showStatisticsAdmin";
		}
		return "error";
    }
	
	@RequestMapping("/ShowStatisticsLiftYear")
    public String liftByYearChart(@RequestParam(value="year") String yearStat, Model model, HttpSession session) {
		if(session.getAttribute("admin")!=null){
			LiftMapper lm=new LiftMapper();
			HashMap<String, Integer> result=lm.findLiftStatsByYear(Integer.parseInt(yearStat));
			BarChart chart = statsByYear(yearStat, result);
	        model.addAttribute("url", chart.toURLString());      
	        return "showStatisticsAdmin";
		}
		return "error";
    }

    
	@RequestMapping("/ShowStatisticsLiftMonth")
    public String liftByMonthChart(@RequestParam(value="year") String yearStat, 
    		@RequestParam(value="month") String monthStat,
    		Model model, HttpSession session) {
		if(session.getAttribute("admin")!=null){
			LiftMapper lm=new LiftMapper();
			HashMap<String, Integer> result=lm.findLiftStatsByMonthAndYear(Integer.parseInt(yearStat),Integer.parseInt(monthStat));
			BarChart chart = statsByMonthAndYear(yearStat,monthStat,result);
	        model.addAttribute("url", chart.toURLString());      
	        return "showStatisticsAdmin";
		}
		return "error";
    }

	private BarChart statsByMonthAndYear(String yearStat,String monthStat, Map<String, Integer> result) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(yearStat));
		calendar.set(Calendar.MONTH, Integer.parseInt(monthStat));
		int numDays = calendar.getActualMaximum(Calendar.DATE);
		List<String> daysLabel=new ArrayList<>();
		List<Integer> data=new ArrayList<>();
		for (int i = 1; i <= numDays; i++) {
			if(result.get(i)!=null)
				data.add(result.get(i));
			else //Giusto per prova FIXME va eliminato
				data.add((int) ((Math.random())*100));
			daysLabel.add(i+"");
		}
		BarChartPlot barPlot=Plots.newBarChartPlot(Data.newData(data), LIMEGREEN, "Registrazioni");
		BarChart chart = GCharts.newBarChart(barPlot);
		
		AxisStyle axisStyle = AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER);
		AxisLabels year = AxisLabelsFactory.newAxisLabels("Giorno", 50.0);
		year.setAxisStyle(axisStyle);
		
		chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(daysLabel));
		chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 100));
		chart.addXAxisLabels(year);

		chart.setSize(689, 435);
		chart.setBarWidth(15);
		chart.setSpaceWithinGroupsOfBars(3);
		chart.setDataStacked(true);
		chart.setTitle("Registrazioni nel mese di "+calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ITALIAN) +" nel "+yearStat, BLACK, 16);
		chart.setGrid(100, 10, 3, 2);
		chart.setBackgroundFill(Fills.newSolidFill(WHITESMOKE));
		return chart;
	}
	
	private BarChart statsByYear(String yearStat, Map<String, Integer> result) {
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
		chart.setTitle("Registrazioni al mese nel "+yearStat, BLACK, 16);
		chart.setGrid(100, 10, 3, 2);
		chart.setBackgroundFill(Fills.newSolidFill(WHITESMOKE));
		return chart;
	}

	//	@RequestMapping("/ShowStatistics")
//    public String LiftByCityChart(Model model, HttpSession session) {
//		if(session.getAttribute("admin")!=null){
////			RegisteredUserMapper rm=new RegisteredUserMapper();
////			HashMap<String, Integer> result=rm.findMemberByMonth();
//	        MapChart chart = GCharts.newMapChart(GeographicalArea.valueOf("IT"));
//
//	        PoliticalBoundary it = new Country(Code.IT, 10);
//	        PoliticalBoundary es = new Country(Code.ES, 20);
//	        PoliticalBoundary fr = new Country(Code.FR, 30);
//
//	        chart.addPoliticalBoundaries(it,es,fr);
//	        chart.setColorGradient(WHITE, RED, BLUE);
//	        chart.setBackgroundFill(Fills.newSolidFill(ALICEBLUE));
//	        String url = chart.toURLString();
//	        
//	        model.addAttribute("url", url);      
//	        return "showStatisticsAdmin";
//		}
//		return "error";
//    }
	
	@RequestMapping("ShowStatisticsLiftSeat")
	public String liftNSeatStats(Model m, HttpSession session){	
		LiftMapper lm=new LiftMapper();
		int seat0=lm.findCountSeatOldLift(new Integer(0),Compare.EQ);
		int seat1=lm.findCountSeatOldLift(new Integer(1),Compare.EQ);
		int seat2=lm.findCountSeatOldLift(new Integer(2),Compare.EQ);
		int seat3=lm.findCountSeatOldLift(new Integer(3),Compare.GQ);
		if(seat0==0)
			seat0=(int) ((Math.random())*100);
		if(seat1==0)
			seat1=100-seat1;
//		lm.findCountSeatOldLift(3);
		Slice s1 = Slice.newSlice(seat0, Color.newColor("CACACA"), "Posti esauriti", "Posti esauriti");
	    Slice s2 = Slice.newSlice(seat1, Color.newColor("DF7417"), "1 posto", "Un posto disponibile");
	    Slice s3 = Slice.newSlice(seat2, Color.newColor("951800"), "2 posti", "Due posti disponibili");
	    Slice s4 = Slice.newSlice(seat3, Color.newColor("01A1DB"), "3 posti", "Tre posti disponibili");
	
	    PieChart chart = GCharts.newPieChart(s1, s2, s3, s4);
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    chart.setTitle("Posti rimasti nei passaggi precendenti al "+dateFormat.format(Calendar.getInstance().getTime()) , BLACK, 16);
	    chart.setSize(500, 200);
	    chart.setThreeD(true);
	    String url = chart.toURLString();
        m.addAttribute("url", url);      
        return "showStatisticsAdmin";
	}
	
}
