import java.util.ArrayList;
import java.util.HashMap;

public class Mapa {

	HashMap<String, ArrayList<String>> mapa;

	public Mapa() {
		mapa = new HashMap<>();
		agregar("S", new String[] { "A", "B" });
		agregar("A", new String[] { "C", "S", "E" });
		agregar("B", new String[] { "C", "S", "D" });
		agregar("C", new String[] { "E", "D", "B", "A" });
		agregar("D", new String[] { "F", "C", "B" });
		agregar("E", new String[] { "F", "A", "C" });
		agregar("F", new String[] { "D", "E" });
		System.out.println(mapa.toString());

	}

	private void agregar(String key, String[] values) {
		if (!mapa.containsKey(key)) {
			mapa.put(key, new ArrayList<>());
			for (String d : values)
				mapa.get(key).add(d);
		}
	}

	public static void main(String[] args) {
		Mapa m = new Mapa();
		System.out.println(m.busqB("S", "F"));

	}

	public ArrayList<String> busqB(String ini, String fin) {
		ArrayList<String> res = new ArrayList<>();
		ArrayList<String> openF = new ArrayList<>();
		ArrayList<String> openB = new ArrayList<>();
		openF.add(ini);
		openB.add(fin);
		buscar(openF, new ArrayList<>(), new ArrayList<>(), openB, res, true);
		return res;
	}

	private boolean buscar(ArrayList<String> openB, ArrayList<String> closedB, ArrayList<String> closedF,
			ArrayList<String> openF, ArrayList<String> res, boolean front) {
		boolean find = false;
		if (!openB.isEmpty()) {
			String n = openB.remove(0);
			closedB.add(n);
			for (String suc : mapa.get(n)) {
				if (!closedF.contains(suc))
					if (!openB.contains(suc))
						openB.add(suc);
				if (openF.contains(suc)) {
					res.add(suc);
					find = true;
					break;
				}
			}
			front = !front;
			if (!find)
				if (front)
					find = buscar(openF, closedF, closedB, openB, res, front);
				else
					find = buscar(openB, closedB, closedF, openF, res, front);
			if (find)
				if (!res.contains(n))
					res.add(n);
		}
		return find;
	}
}
