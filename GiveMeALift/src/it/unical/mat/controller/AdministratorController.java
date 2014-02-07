package it.unical.mat.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.service.ParseDate;

import javax.servlet.http.HttpSession;

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
    
	@RequestMapping("/ShowStatisticsRegistration")
    public String registrationByMonthChart(@RequestParam(value="year") String yearStat, 
    		@RequestParam(value="month",required=false) String monthStat,
    		Model model, HttpSession session) {
		if(session.getAttribute("admin")!=null){
			RegisteredUserMapper rm=new RegisteredUserMapper();
			if(monthStat!=null && !monthStat.equals("")){
				HashMap<String, Integer> result=rm.findMemberByYear(Integer.parseInt(yearStat));
				BarChart chart = statsByMonthAndYear(yearStat, monthStat, result, "Registrazioni");
		      
		        model.addAttribute("url", chart.toURLString());      
		        return "showStatisticsAdmin";
			}
			else{
				HashMap<String, Integer> result=rm.findMemberByYear(Integer.parseInt(yearStat));
				BarChart chart = statsByYear(yearStat, result, "Registrazioni");
		      
		        model.addAttribute("url", chart.toURLString());      
		        return "showStatisticsAdmin";
			}
			
		}
		return "error";
    }
    
	@RequestMapping("/ShowStatisticsLift")
    public String liftByMonthChart(@RequestParam(value="year") String yearStat, 
    		@RequestParam(value="month",required=false) String monthStat,
    		Model model, HttpSession session) {
		if(session.getAttribute("admin")!=null){
			LiftMapper lm=new LiftMapper();
			if(monthStat!=null && !monthStat.equals("")){
				HashMap<String, Integer> result=lm.findLiftStatsByMonthAndYear(Integer.parseInt(yearStat),Integer.parseInt(monthStat));
				BarChart chart = statsByMonthAndYear(yearStat,monthStat,result, "Registrazioni");
		        model.addAttribute("url", chart.toURLString());      
		        return "showStatisticsLift";
			}
			else{
				HashMap<String, Integer> result=lm.findLiftStatsByYear(Integer.parseInt(yearStat));
				BarChart chart = statsByYear(yearStat, result, "Passaggi");
		        model.addAttribute("url", chart.toURLString());      
		        return "showStatisticsLift";
			}
		}
		return "error";
    }

	private BarChart statsByMonthAndYear(String yearStat,String monthStat, Map<String, Integer> result, String string) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(yearStat));
		calendar.set(Calendar.MONTH, Integer.parseInt(monthStat));
		int numDays = calendar.getActualMaximum(Calendar.DATE);
		List<String> daysLabel=new ArrayList<>();
		List<Integer> data=new ArrayList<>();
		for (int i = 1; i <= numDays; i++) {
			if(result.get(i)!=null)
				data.add(result.get(i));
			else
				data.add((int) ((Math.random())*100));
			daysLabel.add(i+"");
		}
		BarChartPlot barPlot=Plots.newBarChartPlot(Data.newData(data), LIMEGREEN, string);
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
		chart.setTitle(string+" nel mese di "+calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ITALIAN) +" nel "+yearStat, BLACK, 16);
		chart.setGrid(100, 10, 3, 2);
		chart.setBackgroundFill(Fills.newSolidFill(WHITESMOKE));
		return chart;
	}
	
	private BarChart statsByYear(String yearStat, Map<String, Integer> result, String string) {
		List<Integer> data=new ArrayList<Integer>();
		for (String s: ParseDate.months) {
			if(result.get(s)!=null)
				data.add(result.get(s));
			else
				data.add((int) ((Math.random())*100));
		}
		System.out.println(data);
		BarChartPlot barPlot=Plots.newBarChartPlot(Data.newData(data), LIMEGREEN, string);
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
		chart.setTitle(string+" al mese nel "+yearStat, BLACK, 16);
		chart.setGrid(100, 10, 3, 2);
		chart.setBackgroundFill(Fills.newSolidFill(WHITESMOKE));
		return chart;
	}
	
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
        return "showStatisticsLift";
	}
	
	@RequestMapping("ShowStatisticsPassengerDriver")
	public String passengerDriver(Model m, HttpSession session){	
		RegisteredUserMapper rm=new RegisteredUserMapper();
		int pass=rm.findCountOfferingUsers();
		int tot=rm.findCountUsers();
		if(pass==0)
			pass=(int) ((Math.random())*100);
		if(tot==0)
			tot=100;
		System.out.println(pass+"\t"+tot);
		
		Slice s1 = Slice.newSlice(pass, Color.newColor("CACACA"), "Passeggeri", "Passeggeri");
	    Slice s2 = Slice.newSlice(tot-pass, Color.newColor("DF7417"), "Conducenti", "Conducenti");
	
	    PieChart chart = GCharts.newPieChart(s1, s2);
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    chart.setTitle("Distribuzione passeggeri e conducenti al "+dateFormat.format(Calendar.getInstance().getTime()) , BLACK, 16);
	    chart.setSize(500, 200);
	    chart.setThreeD(true);
	    String url = chart.toURLString();
        m.addAttribute("url", url);      
        return "showStatisticsAdmin";
	}
	
}
