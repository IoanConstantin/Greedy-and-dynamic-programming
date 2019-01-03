import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Frati {
	static class Task {
		public final String Input_file = "frati.in";
		public final String Output_file = "frati.out";

		int N;
		int []jocuri = new int [1000000];
		int []benzi = new int [1000000];
		int sumajocuri = 0;
		int sumabenzi = 0;

		private void readInput() {
			try {
				FileReader file = new FileReader(Input_file);
				BufferedReader buffer = new BufferedReader(file);
				String line;
				StringTokenizer st;

				line = buffer.readLine();
				st = new StringTokenizer(line," ");
				N = parseInt(st.nextToken());

				for (int i = 0;i < N;i++) {
					line = buffer.readLine();
					st = new StringTokenizer(line," ");
					jocuri[i] = parseInt(st.nextToken());
					benzi[i] = parseInt(st.nextToken());
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result1,int result2) {
			try {
				FileOutputStream stream = new FileOutputStream(Output_file);
				OutputStreamWriter outstream = new OutputStreamWriter(stream);
				BufferedWriter writer = new BufferedWriter(outstream);
				
				writer.write(Integer.toString(result1) + " " + Integer.toString(result2));
				writer.close();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/*compara 2 concursuri dupa numarul de jocuri*/
		static class ComparareJocuri implements Comparator<Concurs> {
			public int compare(Concurs concurs1,Concurs concurs2) {
				return concurs1.getGames() - concurs2.getGames();
			}
		}

		/*compara 2 concursuri dupa numarul de benzi*/
		static class ComparareBenzi implements Comparator<Concurs> {
			public int compare(Concurs concurs1,Concurs concurs2) {
				return concurs1.getComics() - concurs2.getComics();
			}
		}

		/*compara 2 concursuri dupa suma dintre jocuri si benzi*/
		static class ComparareAdunare implements Comparator<Proba> {
			public int compare(Proba proba1,Proba proba2) {
				return proba1.getAdunare() - proba2.getAdunare();
			}
		}

		private void getResult() {
			/*pentru fiecare concurs retin in probe tripleta(jocuri,benzi,jocuri+benzi)*/ 
			Vector<Proba> probe = new Vector<Proba>();
			
			for (int i = 0;i < N;i++) {
				probe.add(new Proba(jocuri[i],benzi[i],jocuri[i] + benzi[i]));
			}
			
			/*sortez concursurile in functie de suma dintre jocuri si benzi*/
			Collections.sort(probe,new ComparareAdunare());

			int mutare = 0; /*daca va fi para muta Jon, altfel muta Sam*/
			int j = probe.size() - 1;

			while (j >= 1) {
				/*daca nu exista un concurs cu aceeasi suma ca si concursul j, 
				il adaug in sumajocuri sau sumabenzi, in functie cine muta*/
				if (probe.get(j).getAdunare() != probe.get(j - 1).getAdunare()) {
					if (mutare % 2 == 0) {	
						sumajocuri = sumajocuri + probe.get(j).getJoc();	
						mutare++;
					} else {
						sumabenzi = sumabenzi + probe.get(j).getBanda();
						mutare++;
					}  
					j--;
					/*daca sunt la ultimul este suficient sa vad 
					cine muta si sa il adaug in suma respectiva*/
					if (j == 0) {
						if (mutare % 2 == 0) {
							sumajocuri = sumajocuri + probe.get(0).getJoc();
							mutare++;
						} else {
							sumabenzi = sumabenzi + probe.get(0).getBanda();
							mutare++;
						}
					}  
				} else {
					/*daca exista mai multe concursuri cu aceeasi suma ca al lui j,
					pentru fiecare retinem jocurile si benzile aferente*/	
					Vector<Concurs> concursuri = new Vector<Concurs>();
				
					int t = j;

					while (probe.get(j).getAdunare() == probe.get(t).getAdunare()) {
						concursuri.add(new Concurs(probe.get(t).getJoc(),
							probe.get(t).getBanda(),t));
						t--;
					}
					/*pana cand adaugam fiecare concurs intr-una dintre sume, 
					sortam in functie de cine se afla la mutare dupa jocuri, respectiv 
					benzi, adaugam cel mai mare element din vectorul sortat la suma
					respectiva si stergem intregul concurs(jocuri si benzi) din concursuri*/	
					int contor = t + 1;
					while (contor <= j) {
						if (mutare % 2 == 0) {
							Collections.sort(concursuri,new ComparareJocuri());
							sumajocuri = sumajocuri 
								+ concursuri.get(concursuri.size() - 1).getGames();
							concursuri.remove(concursuri.size() - 1);
							mutare++;
						} else {	
							Collections.sort(concursuri,new ComparareBenzi());
							sumabenzi = sumabenzi 
								+ concursuri.get(concursuri.size() - 1).getComics();
							concursuri.remove(concursuri.size() - 1);
							mutare++;
						}
						contor++;
					}
					j = t;
				
					if (j == 0) {
						if (mutare % 2 == 0) {
							sumajocuri = sumajocuri + probe.get(0).getJoc();
							mutare++;
						} else {
							sumabenzi = sumabenzi + probe.get(0).getBanda();
							mutare++;
						}
					}
				}   
			}
		}

		public void solve() {
			readInput();
			getResult();
			writeOutput(sumajocuri,sumabenzi);
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
