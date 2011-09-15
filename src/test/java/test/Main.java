package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		final HashMap<Integer, Ville> map = new HashMap<Integer, Ville>();
		final Set<String> cantons = new TreeSet<String>();

		FileReader fr = null;
		try {
			fr = new FileReader(new File("d:/ville.txt"));
			final BufferedReader buf = new BufferedReader(fr);
			String line = "";
			String canton = "";
			final int cantonId = 1;
			while ((line = buf.readLine()) != null) {
				int begin = line.indexOf("\t");
				begin = line.indexOf("\t", begin + 1);
				final Integer npa = Integer.valueOf(line.substring(begin + 1, begin + 5));

				begin = line.indexOf("\t", begin + 1);
				begin = line.indexOf("\t", begin + 1);
				begin = line.indexOf("\t", begin + 1);
				final int end = line.indexOf("\t", begin + 1);
				final String commune = line.substring(begin + 1, end);
				// System.out.println(commune);
				begin = line.indexOf("\t", begin + 1);
				// begin = line.indexOf("\t", begin + 1);
				canton = line.substring(begin, line.length());
				cantons.add(canton);
				if (map.containsKey(npa)) {

				} else {
					final Ville ville = new Ville(canton, npa, commune);
					map.put(npa, ville);
				}

			}
			final HashMap<String, Integer> cantonsMap = new HashMap<String, Integer>();
			int idCanton = 1;

			for (final String currentCanton : cantons) {
				if (!cantonsMap.containsKey(currentCanton)) {
					cantonsMap.put(currentCanton, Integer.valueOf(idCanton++));
				}
			}
			final Set<Entry<String, Integer>> entrySet2 = cantonsMap.entrySet();
			for (final Entry<String, Integer> entry : entrySet2) {
				System.out.println("insert into canton (idCanton, cdCanton, idPays) values(" + entry.getValue() + ", '" + entry.getKey() + "',1);");
			}

			// sysout
			final Set<Entry<Integer, Ville>> entrySet = map.entrySet();
			for (final Entry<Integer, Ville> entry : entrySet) {
				final Ville ville = entry.getValue();
				System.out.println("insert into ville (nbNpa,txVille,idCanton,idPays) values(" + entry.getKey() + ", \"" + ville.ville + "\","
				    + cantonsMap.get(ville.canton) + ", 1);");
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	static class Ville {
		String canton;
		Integer npa;
		String ville;

		public Ville(String canton, Integer npa, String ville) {
			this.npa = npa;
			this.ville = ville;
			this.canton = canton;
		}

		@Override
		public String toString() {
			return " ville=" + ville + " canton=" + canton;
		}

	}
}
