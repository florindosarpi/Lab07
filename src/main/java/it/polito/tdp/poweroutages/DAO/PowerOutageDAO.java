package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Outage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Outage> getOutageByNerc (String nercValue) {
		
		final String sql = "SELECT p.date_event_began, p.date_event_finished, p.customers_affected "
				+ "FROM nerc n, poweroutages p "
				+ "WHERE n.value = ? AND n.id = p.nerc_id ORDER BY p.date_event_began";
		List<Outage> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nercValue);
			ResultSet res = st.executeQuery();
			

			while (res.next()) {
				Outage o = new Outage(nercValue, res.getDate("date_event_began"), res.getDate("date_event_finished"), res.getInt("customers_affected"));
				result.add(o);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		
		
		return result;
	}
	

}
