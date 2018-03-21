import java.util.*;
import java.io.*;

public class SD_SkillSets {

	HashMap<Integer, String> sk = new HashMap<Integer, String>();

	public HashMap<Integer, String> skillsets(int choice) {

		

		if (choice == 1) {

			sk.put(0,"C");
			sk.put(1, "C++");
			sk.put(2, "Objective C");
			sk.put(3, "Haskell");
			sk.put(4, "Lisp");
			sk.put(5, "Python");
			sk.put(6, "Lua");
		}

		else if (choice == 2) {

			sk.put(0, "Java");
			sk.put(1, "Scala");
			sk.put(2, "Ruby");
			sk.put(3, "JavaScript");
			sk.put(4, "Perl");
			sk.put(5, "Clojure");
			sk.put(6, "Android");
		}

		else if (choice == 3) {

			
			sk.put(0, "Python");
			sk.put(1, "JavaScript");
			sk.put(2, "XML");
			sk.put(3, "Lua");
			sk.put(4, "C/C++");
			sk.put(5, "Ruby");
			sk.put(6, "R");
		}

		return sk;
	}

	public int n_skills(int choice) {

		if (choice == 1) {

			return 7;
		}

		else if (choice == 2) {

			return 7;
		}

		else if (choice == 3) {

			return 7;
		}

		else {

			return 1;
		}
	}
}