import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

	public static DecisionTree newTree;

	public static HashMap<Integer, String> map = new HashMap<Integer, String>();

	public static String n_text[] = new String[100];

	public static void main(String[] args) throws IOException {

		int positions = 3;

		Scanner sc = new Scanner(System.in);
		
		SD_SkillSets sdk = new SD_SkillSets();

		ResumeParser rp = new ResumeParser();

		try {

			n_text = rp.resume_parser();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		
		}

		int n_nodes = 1;

		System.out.println("Enter choice: \n1. C Developer\n2. JAVA Developer\n3. Python Developer");

		int choice = sc.nextInt();

		n_nodes = sdk.n_skills(choice);

		map = sdk.skillsets(choice);

		newTree = new DecisionTree();

		double rating[] = new double[n_text.length];

		char ch;

		String word = "";

		double entropy, total = 0.0, final_entropy, gain = 0.0;

		gain = (double) 0.845;

		for (int i=0; i<n_text.length-1; i++) {

			for (int j=0; j<n_text[i].length(); j++) {

				ch = n_text[i].charAt(j);

				if (ch != ' ' && ch != ',' && ch != '.' && ch != ';' && ch != '/') {

					word = word + ch;
				}

				else {

					generateTree(n_nodes);

					entropy = queryTree(word, map, gain, n_nodes);

					word = "";

					total = total + entropy;
				}
			}

			final_entropy = total;

			total = 0.0;

			rating[i] = ((final_entropy)/70)*1000;
		
		}

		for (int k=0; k<(n_text.length-1); k++) {

			rating[k] = (rating[k]/gain);

			System.out.println("Resume " + (k+1) + ": " + rating[k]);
		}

	}

	public static void generateTree(int n_nodes) {

		newTree.createRoot(1, map.get(0));

		for (int i=1; i<=(n_nodes/2); i++) {

			for (int j=(i*2); j<=(i*2)+1; j++) {

				if (j%2 == 0) {

					newTree.addYesNode(i,j, map.get(j-1));
				}

				else {

					newTree.addNoNode(i,j, map.get(j-1));
				}
			}
		}
	}


	public static double queryTree(String word, HashMap<Integer, String> map, double gain, int n_nodes) throws IOException {

		double entropy = 0.0;

		int flag = 0;
		
		flag = newTree.queryBinTree(word, map, n_nodes);

		if (flag == 1) {

			entropy = (gain/n_nodes);
		}

		return entropy;
	}
}