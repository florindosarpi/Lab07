package it.polito.tdp.poweroutages.model;

import java.util.Date;

public class Outage {
	
	public String nercValue;
	public Date date_event_began;
	public Date date_event_finished;
	public int customers_affected;
	public int oreOutage;
	
	public Outage(String nercValue, Date date_event_began, Date date_event_finished, int customers_affected) {
		super();
		this.nercValue = nercValue;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		this.customers_affected = customers_affected;
		this.oreOutage = (int) ((date_event_finished.getTime() - date_event_began.getTime())/3600000);
	}

	public String getNercValue() {
		return nercValue;
	}

	public void setNercValue(String nercValue) {
		this.nercValue = nercValue;
	}

	public Date getDate_event_began() {
		return date_event_began;
	}

	public void setDate_event_began(Date date_event_began) {
		this.date_event_began = date_event_began;
	}

	public Date getDate_event_finished() {
		return date_event_finished;
	}

	public void setDate_event_finished(Date date_event_finished) {
		this.date_event_finished = date_event_finished;
	}

	public int getCustomers_affected() {
		return customers_affected;
	}

	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}
	

	public int getOreOutage() {
		return oreOutage;
	}

	@Override
	public String toString() {
		return "Outage [nercValue=" + nercValue + ", date_event_began=" + date_event_began + ", date_event_finished="
				+ date_event_finished + ", customers_affected=" + customers_affected + ", oreOutage=" + oreOutage + "\n" + "]";
	}

	
	
	
	

}
