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

public class Numaratoare {
	static class Task {
		public final String Input_file = "numaratoare.in";
		public final String Output_file = "numaratoare.out";

		int s;
		int n;
		int i;
		//int []suma=new int [n];
		//String rezultat;		

		private void readInput() {
			try {
				FileReader file = new FileReader(Input_file);
				BufferedReader buffer = new BufferedReader(file);
				String line;
				StringTokenizer st;

				line = buffer.readLine();
				st = new StringTokenizer(line," ");
				s = parseInt(st.nextToken());
				//s = parseInt(line);

				line = buffer.readLine();
				st = new StringTokenizer(line," ");
				n = parseInt(st.nextToken());
				//n = parseInt(line);

				line = buffer.readLine();
				st = new StringTokenizer(line," ");
				i = parseInt(st.nextToken());
				//i = parseInt(line);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}


		private void writeOutput(String /*int*/ result) {
			try {				
				FileOutputStream stream = new FileOutputStream(Output_file);
				OutputStreamWriter outstream = new OutputStreamWriter(stream);
				BufferedWriter writer = new BufferedWriter(outstream);

				//writer.write(Integer.toString(result));
				writer.write(result);
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private /*int*/ String getResult() {
			if (s < n) {
				return " - ";
			} else {
				return "se poate compune suma";
			}
			/*{
			int []suma=new int [n];
				suma[0]=s-n+1;
				for(int j=1;j<n;j++)
				{
					suma[j]=1;
				}

				//return suma[0];

				//return s-n+1;

			/*char []sumachar=new char [n];
				sumachar[0]=Integer.toString(suma[0]);
			char []sumachar1=new char [1];
				sumachar1[0]=sumachar[0];
				String sumastring=new String(sumachar1);
				return sumastring;*/

			/*char []sumachar=new char [n];
				for(int w=0;w<n;w++)
				{
					sumachar[w]=(char)suma[w];
				}
				
				String sumastring=new String(sumachar);
				return sumastring;*/

				
			/*int k=1;
			int t=0;
				while(t<(n-1))
				{
					if(t>0)
					{
						while((suma[t-1]-suma[t])>=2)
						{
							suma[t-1]=suma[t-1]-1;
							suma[t]=suma[t]+1;
							if(k==i) break;
							k++;
						}
						//if(k==i) break;
					}
					while((suma[t]-suma[t+1])>=2)
					{
						suma[t]=suma[t]-1;
						suma[t+1]=suma[t+1]+1;
						if(k==i) break;
						k++;
					}
					//if(k==i) break;
					t++;
				}

				//return Integer.toString(suma[0]);
				return Integer.toString(suma[0]);
			}
				/*
				char []sumachar=new char [n];
				for(int w=0;w<n;w++)
				{
					sumachar[w]=(char)suma[w];
				}
				
				String sumastring=new String(sumachar);
				return sumastring;*/
			//}
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
