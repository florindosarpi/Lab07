package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<Outage> soluzioneOttima;
	Integer massimo;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Outage> getOutageByNerc(String nercValue){
		return podao.getOutageByNerc(nercValue);
	}

	
	public List<Outage> calcola(List<Outage> iniziale, Integer oreMassime, Integer anniMassimi, List<Outage> parziale, Integer massimo) {
		
		if(calcolaMassimo(parziale)>massimo) {
			this.massimo = calcolaMassimo(parziale);
			soluzioneOttima= new ArrayList<Outage>(parziale);
			}
		
		for(Outage o : iniziale) {
			
			if(!parziale.contains(o))
				parziale.add(o);
			if(isValida(parziale, oreMassime, anniMassimi)) {
				Integer nuovoMassimo = calcolaMassimo(parziale);
				calcola(iniziale, oreMassime, anniMassimi, parziale, nuovoMassimo);
			}
			parziale.remove(parziale.size()-1);
			
		}
		
		return soluzioneOttima;
		
		
	}
	
	
	boolean isValida(List<Outage> listaGuasti, Integer oreMassime, Integer anniMassimi) {
		boolean result = false;
		Integer count = 0;
		for (Outage o : listaGuasti) {
			count += o.getOreOutage();
		}
		if(count<=oreMassime && 
				(listaGuasti.get(listaGuasti.size()-1).getDate_event_began().getYear() -  listaGuasti.get(0).getDate_event_began().getYear() +1 ) <=anniMassimi ) {
			result = true;
		}
		return result;
	}
	
	Integer calcolaMassimo (List<Outage> guasti) {
		Integer result = 0;
		for (Outage o : guasti) {
			result += o.getCustomers_affected();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	

}
