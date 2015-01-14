package paquete;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public enum CochesDao {
	instance;

	private Map<String, Coche> contentProvider = new HashMap<String, Coche>();

	private CochesDao() {

		List<Coche> lc = CocheDB.getCoches();
		
		for(int i = 0; i < lc.size(); i++) {
			contentProvider.put(Integer.toString(i) , lc.get(i));
		}

	}

	public Map<String, Coche> getModel() {
		return contentProvider;
	}
}