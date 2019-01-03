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

public class Planificare {
	static class Task {
		public final String Input_file = "planificare.in";
		public final String Output_file = "planificare.out";

		int P;
		int D;
		int B;
		int []durate = new int [1000];
		int suma = 0;
		int concursuri = 0;

		private void readInput() {
			try {
				FileReader file = new FileReader(Input_file);
				BufferedReader buffer = new BufferedReader(file);
				String line;
				StringTokenizer st;

				line = buffer.readLine();
				st = new StringTokenizer(line," ");
				P = parseInt(st.nextToken());
				D = parseInt(st.nextToken());
				B = parseInt(st.nextToken());

				for (int i = 0;i < P;i++) {
					line = buffer.readLine();
					st = new StringTokenizer(line," ");
					durate[i] = parseInt(st.nextToken());
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

		private void getResult() {

			/*daca suma dintre oricare 2 probe consecutive si pauza dintre ele este 
			mai mare decat durata unui concurs, atunci niciun concurs nu va putea 
			sa contina mai mult de o proba*/
			for (int i = 0;i < P - 1;i++) {
				if ((durate[i] + B + durate[i + 1]) > D) {
					suma = suma + (D - durate[i]) * (D - durate[i]) * (D - durate[i]);
					concursuri++;
				}
			}
			/*termenul ultimului concurs este 0^3, deci nu il mai adaugam la suma*/
			concursuri++;
		}

		public void solve() {
			readInput();
			getResult();
			writeOutput(suma,concursuri);
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
