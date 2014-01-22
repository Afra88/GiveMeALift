package it.unical.mat.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.domain.Lift;

public class LiftToViewConverterFacade {

	public List<Object> convert(Lift l) {
		Time t=l.getDepartureTime();
		String goingTimeH="";
		if(t.getHours()<=9)
			goingTimeH="0"+t.getHours();
		else
			goingTimeH=""+t.getHours();
		String goingTimeM="";
		if(t.getMinutes()<=9)
			goingTimeM="0"+t.getMinutes();
		else
			goingTimeM=""+t.getMinutes();
		int luggage=l.getLiftPreferences().getLuggageSize();
		String luggageSize = null;
		if(luggage == 1)
			luggageSize = "Piccolo";
		else if(luggage == 2)
			luggageSize = "Medio";
		else if(luggage == 3)
			luggageSize = "Grande";	
		boolean pinkTrip=l.getLiftPreferences().getPinkTrip();
		String pink;
		if(pinkTrip)
			pink = "Viaggio con uomini e donne";
		else
			pink = "Viaggio con solo donne";
		String roadType=l.getLiftPreferences().getRoadType();
		String roadTypeString="";
		if(roadType.equals("freeway"))
			roadTypeString="Autostrada";
		if(roadType.equals("noFreeway"))
			roadTypeString="Evito l'autostrada";
		String delay=l.getLiftPreferences().getScheduleFlexibility();
		String delayString = "";
		if(delay.equals("strict"))
			delayString="Puntuale";
		if(delay.equals("15min"))
			delayString="+/- 15 minuti";
		if(delay.equals("30min"))
			delayString="+/- 30 minuti";
		if(delay.equals("1h"))
			delayString="+/- un'ora";
		if(delay.equals("2h"))
			delayString="+/- due ore";
		String deviation=l.getLiftPreferences().getScheduleFlexibility();
		String deviationString="";
		if(deviation.equals("nothing"))
			deviationString="Nessuna deviazione";
		if(deviation.equals("15min"))
			deviationString="15 minuti al massimo";
		if(deviation.equals("30min"))
			deviationString="30 minuti al massimo";
		if(deviation.equals("any"))
			deviationString="Qualsiasi deviazione";
		List<Object> converted=new ArrayList<Object>();
		converted.add(l);
		converted.add(goingTimeH);
		converted.add(goingTimeM);
		converted.add(delayString);
		converted.add(deviationString);
		converted.add(pink);
		converted.add(roadTypeString);
		converted.add(luggageSize);

		return converted;
	}

}
