import java.util.*;
import java.io.*;

public class ResumeParser {

	HashMap<Integer, String> stopwords = new HashMap<Integer, String>(152);
	HashMap<Integer, String> stemwords = new HashMap<Integer, String>(6);

	String substring1 = "", substring2 = "", substring3 = "";

	Scanner sc = new Scanner(System.in);

	public String[] resume_parser() throws FileNotFoundException {

		System.out.println("Enter file path: ");

		String path = sc.next();

		File folder = new File(path);

		String[] files = folder.list();

		String[] text = folder.list();

		int i=0;

		for (String file : files) {

			String filepath = path + file;

			if (new Scanner(new File(filepath)).useDelimiter("\\A").hasNext()) {

				text[i++] = new Scanner(new File(filepath)).useDelimiter("\\A").next();

			}

			
		}

		String[] n_text = new String[text.length];

		n_text = word_remover(text);

		return n_text;

	}

	public String[] word_remover(String[] text) {

		stopwords.put(0, "a");
		stopwords.put(1, "about");
		stopwords.put(2, "above");
		stopwords.put(3, "after");
		stopwords.put(4, "again");
		stopwords.put(5, "against");
		stopwords.put(6, "all");
		stopwords.put(7, "am");
		stopwords.put(8, "an");
		stopwords.put(9, "and");
		stopwords.put(10, "any");
		stopwords.put(11, "are");
		stopwords.put(12, "as");
		stopwords.put(13, "at");
		stopwords.put(14, "be");
		stopwords.put(15, "because");
		stopwords.put(16, "been");
		stopwords.put(17, "before");
		stopwords.put(18, "being");
		stopwords.put(19, "below");
		stopwords.put(20, "between");
		stopwords.put(21, "both");
		stopwords.put(22, "but");
		stopwords.put(23, "by");
		stopwords.put(24, "could");
		stopwords.put(25, "did");
		stopwords.put(26, "do");
		stopwords.put(27, "does");
		stopwords.put(28, "doing");
		stopwords.put(29, "down");
		stopwords.put(30, "during");
		stopwords.put(31, "each");
		stopwords.put(32, "few");
		stopwords.put(33, "for");
		stopwords.put(34, "from");
		stopwords.put(35, "further");
		stopwords.put(36, "had");
		stopwords.put(37, "has");
		stopwords.put(38, "have");
		stopwords.put(39, "having");
		stopwords.put(40, "he");
		stopwords.put(41, "he'd");
		stopwords.put(42, "he's");
		stopwords.put(43, "he'll");
		stopwords.put(44, "her");
		stopwords.put(45, "here");
		stopwords.put(46, "here's");
		stopwords.put(47, "hers");
		stopwords.put(48, "herself");
		stopwords.put(49, "him");
		stopwords.put(50, "himself");
		stopwords.put(51, "his");
		stopwords.put(52, "how");
		stopwords.put(53, "how's");
		stopwords.put(54, "I");
		stopwords.put(55, "I'd");
		stopwords.put(56, "I'll");
		stopwords.put(57, "I'm");
		stopwords.put(58, "I've");
		stopwords.put(59, "if");
		stopwords.put(60, "in");
		stopwords.put(61, "into");
		stopwords.put(62, "is");
		stopwords.put(63, "it's");
		stopwords.put(64, "its");
		stopwords.put(65, "itself");
		stopwords.put(66, "let's");
		stopwords.put(67, "me");
		stopwords.put(68, "more");
		stopwords.put(69, "most");
		stopwords.put(70, "my");
		stopwords.put(71, "myself");
		stopwords.put(72, "nor");
		stopwords.put(73, "of");
		stopwords.put(74, "on");
		stopwords.put(75, "once");
		stopwords.put(76, "only");
		stopwords.put(77, "or");
		stopwords.put(78, "other");
		stopwords.put(79, "ought");
		stopwords.put(80, "our");
		stopwords.put(81, "ours");
		stopwords.put(82, "ourselves");
		stopwords.put(83, "out");
		stopwords.put(84, "over");
		stopwords.put(85, "own");
		stopwords.put(86, "same");
		stopwords.put(87, "she");
		stopwords.put(88, "she'd");
		stopwords.put(89, "she'll");
		stopwords.put(90, "she's");
		stopwords.put(91, "should");
		stopwords.put(92, "so");
		stopwords.put(93, "some");
		stopwords.put(94, "such");
		stopwords.put(95, "than");
		stopwords.put(96, "that");
		stopwords.put(97, "that's");
		stopwords.put(98, "the");
		stopwords.put(99, "their");
		stopwords.put(100, "theirs");
		stopwords.put(101, "them");
		stopwords.put(102, "themselves");
		stopwords.put(103, "then");
		stopwords.put(104, "there");
		stopwords.put(105, "there's");
		stopwords.put(106, "these");
		stopwords.put(107, "they");
		stopwords.put(108, "they'd");
		stopwords.put(109, "they'll");
		stopwords.put(110, "they're");
		stopwords.put(111, "they've");
		stopwords.put(112, "this");
		stopwords.put(113, "those");
		stopwords.put(114, "through");
		stopwords.put(115, "to");
		stopwords.put(116, "too");
		stopwords.put(117, "under");
		stopwords.put(118, "until");
		stopwords.put(119, "up");
		stopwords.put(120, "very");
		stopwords.put(121, "was");
		stopwords.put(122, "we");
		stopwords.put(123, "we'd");
		stopwords.put(124, "we'll");
		stopwords.put(125, "we're");
		stopwords.put(126, "we've");
		stopwords.put(127, "were");
		stopwords.put(128, "what");
		stopwords.put(129, "what's");
		stopwords.put(130, "when");
		stopwords.put(131, "when's");
		stopwords.put(132, "where");
		stopwords.put(133, "where's");
		stopwords.put(134, "which");
		stopwords.put(135, "while");
		stopwords.put(136, "who");
		stopwords.put(137, "who's");
		stopwords.put(138, "whom");
		stopwords.put(139, "why");
		stopwords.put(140, "why's");
		stopwords.put(141, "with");
		stopwords.put(142, "would");
		stopwords.put(143, "you");
		stopwords.put(144, "you'd");
		stopwords.put(145, "you'll");
		stopwords.put(146, "you're");
		stopwords.put(147, "you've");
		stopwords.put(148, "your");
		stopwords.put(149, "yours");
		stopwords.put(150, "yourself");
		stopwords.put(151, "yourselves");

		stemwords.put(0, "ing");
		stemwords.put(1, "ly");
		stemwords.put(2, "er");
		stemwords.put(3, "ed");
		stemwords.put(4, "ingly");
		stemwords.put(5, "'nt");

		char letter;
		String word = "", n_string = "";
		int counter = 0, k = 0;
		String[] n_text = new String[text.length];

		for (int i=0; i<((text.length)-1); i++) {

			for (int j=0; j<text[i].length(); j++) {

				letter = text[i].charAt(j);

				if (letter != ' ' && letter != ',' && letter != '.' && letter != ';' && letter != ':' && letter != '-') {

					word = word + letter;
				
				} else {

					Iterator it = stopwords.entrySet().iterator();

					while (it.hasNext()) {

						Map.Entry pair = (Map.Entry)it.next();

						String value = pair.getValue().toString();

						if (value.equalsIgnoreCase(word) == true) {

							counter++;
						}
					}
				

					if (counter == 0) {

						int l = word.length();

						int flag=0;

						if (l>3 && l<5) {
						
							substring1 = word.substring((l-3));
							flag = 1;
						}
					
						else if (l>4 && l<6) {
						
							substring1 = word.substring((l-3));
							substring2 = word.substring((l-4));
							flag = 2 ;
						}
					
						else if (l>6) {
						
							substring1 = word.substring((l-3));
							substring2 = word.substring((l-4));
							substring3 = word.substring((l-6));
							flag = 3;
						}

						Iterator st = stemwords.entrySet().iterator();


						if (flag == 1) {

						
							while (st.hasNext()) {

								Map.Entry pair1 = (Map.Entry)st.next();

								String value1 = pair1.getValue().toString();

								if (value1.equalsIgnoreCase(substring1) == true) {

									counter++;
								}
							}
						}

						else if (flag == 2) {

							while (st.hasNext()) {

								Map.Entry pair1 = (Map.Entry)st.next();

								String value1 = pair1.getValue().toString();

								if (value1.equalsIgnoreCase(substring1) == true || value1.equalsIgnoreCase(substring2) == true) {

									counter++;
								}
							}
						}

						else if (flag == 3) {

							while (st.hasNext()) {

								Map.Entry pair1 = (Map.Entry)st.next();

								String value1 = pair1.getValue().toString();

								if (value1.equalsIgnoreCase(substring1) == true || value1.equalsIgnoreCase(substring2) == true || value1.equalsIgnoreCase(substring3) == true) {

									counter++;
								}
							}
						}
					
					}

					if (counter == 0) {

						n_string = n_string + word + " ";
					}

					n_string = n_string;
					
				

					counter = 0;
					word = "";

				}
			}

			n_text[k++] = n_string;
			n_string = "";

		}

		return n_text;
	}
}