package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//System.out.println(model.getNercList());
		///System.out.println(model.getOutageByNerc("MAAC"));
		
		System.out.println(model.calcola(model.getOutageByNerc("MAAC"), 200, 4, new ArrayList<Outage>(), 0).toString());
		
	}

}
