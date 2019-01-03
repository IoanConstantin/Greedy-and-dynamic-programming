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

public class Ursi {
	static class Task {
		public final String Input_file = "ursi.in";
		public final String Output_file = "ursi.out";

		String s;

		private void readInput() {
			try {
				FileReader file = new FileReader(Input_file);
				BufferedReader buffer = new BufferedReader(file);
				String line;
				StringTokenizer st;

				line = buffer.readLine();
				st = new StringTokenizer(line," ");
				s = st.nextToken();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				FileOutputStream stream = new FileOutputStream(Output_file);
				OutputStreamWriter outstream = new OutputStreamWriter(stream);
				BufferedWriter writer = new BufferedWriter(outstream);
				
				writer.write(Integer.toString(result));
				writer.close();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int getResult() {
			int contor = 0;/*numarul de caractere '^' din mesaj*/
			char [] mesaj = s.toCharArray();
			for (int i = 0;i < mesaj.length;i++) {
				if (mesaj[i] == '^') {
					contor++;
				}
			}
			/*conform cerintei, daca numarul de '^' din mesaj este impar returnam 0*/
			if (contor % 2 == 1) {
				return 0; 
			} else {
				/*aplicam recurenta T(n) = T(n - 2) * (n - 1), cu T(0) = 1*/
				if (contor >= mesaj.length - 1) {	
					int produs = 1;
					for (int k = 2;k <= contor;k = k + 2) {
						produs = produs * (k - 1);
					}
					return produs; 
				} else {
					return -1;
				}
			}
		}

		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
