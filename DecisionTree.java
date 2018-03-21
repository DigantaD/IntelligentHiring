import java.io.*;
import java.util.*;
import java.math.*;


	/*-----------------------------*/
	/*							   */
	/*			  FIELDS		   */
	/*							   */
	/*-----------------------------*/

	/*	NESTED CLASS  */

public class DecisionTree {


	public class BinTree {

		public int nodeID;
		public String skill = null;
		public BinTree yesBranch = null;
		public BinTree noBranch = null;

		/*	CONSTRUCTOR	  */

		public BinTree(int newNodeID, String newSkill) {

			nodeID = newNodeID;
			skill = newSkill;
		}
	}

	/*	OTHER FIELDS  */

	BinTree rootNode = null;

	/*----------------------*/
	/*						*/
	/*	   CONSTRUCTORS		*/
	/*						*/
	/*----------------------*/

	/*  Default Constructor  */

	public DecisionTree() {

	}

	/*----------------------*/
	/*						*/
	/*TREE BUILDING METHODS */
	/*						*/
	/*----------------------*/

	/*  CREATE ROOT NODE  */

	public void createRoot(int newNodeID, String newSkill) {

		rootNode = new BinTree(newNodeID, newSkill);
	}

	/*  ADD YES NODE  */

	public void addYesNode(int existingNodeID, int newNodeID, String newSkill) {

		// If no root, do nothing

		if (rootNode == null) {

			return;
		}

		// Search tree

		searchTreeAndAddYesNode(rootNode, existingNodeID, newNodeID, newSkill); 
	}

	/* SEARCH TREE AND ADD YES NODE */

	private boolean searchTreeAndAddYesNode(BinTree currentNode, int existingNodeID, int newNodeID, String newSkill) {

		if (currentNode.nodeID == existingNodeID) {

			// Found node

			if (currentNode.yesBranch == null) {

				currentNode.yesBranch = new BinTree(newNodeID, newSkill);
			
			} else {

				currentNode.yesBranch = new BinTree(newNodeID, newSkill);		
			}

			return(true);
		
		} else {

			// Try yes branch if it exists

			if (currentNode.yesBranch != null) {

				return(searchTreeAndAddYesNode(currentNode.noBranch, existingNodeID, newNodeID, newSkill));
			
			} else {

				// Try no branch if it exists

				if (currentNode.noBranch != null) {

					return(searchTreeAndAddYesNode(currentNode.noBranch, existingNodeID, newNodeID, newSkill));
				
				} else {

					return(false);	// Not found here
				}
			}

			// Not found here
		}
 
	}

	/* ADD NO NODE */

	public void addNoNode(int existingNodeID, int newNodeID, String newSkill) {

		// If no root node, do nothing

		if (rootNode == null) {

			return;
		}

		// Search tree

		searchTreeAndAddNoNode(rootNode, existingNodeID, newNodeID, newSkill);
	}

	/* SEARCH TREE AND ADD NO NODE */

	private boolean searchTreeAndAddNoNode(BinTree currentNode, int existingNodeID, int newNodeID, String newSkill) {

		if (currentNode.nodeID == existingNodeID) {

			// Found node

			if (currentNode.noBranch == null) {

				currentNode.noBranch = new BinTree(newNodeID, newSkill);
			
			} else {

				currentNode.noBranch = new BinTree(newNodeID, newSkill);
			}

			return(true);
		
		} else {

			// Try yes branch if it exists

			if (currentNode.yesBranch != null) {

				if (searchTreeAndAddNoNode(currentNode.yesBranch, existingNodeID, newNodeID, newSkill)) {

					return(true);
				
				} else {

					// Try no branch if it exists

					if (currentNode.noBranch != null) {

						return(searchTreeAndAddNoNode(currentNode.noBranch, existingNodeID, newNodeID, newSkill));
					
					} else {

						return(false);	// Not found here
					}
				}
			
			} else {

				return(false);	// Not found here
			} 
		}
	}


	/*-----------------------------------------------*/
	/*												 */
	/*				TREE QUERY METHODS				 */
	/*												 */
	/*-----------------------------------------------*/

	public static DecisionTree newTree = new DecisionTree();


	public int queryBinTree(String word, HashMap<Integer, String> map, int n_nodes) throws IOException {
	

		int n=0;

		if (rootNode.skill.equalsIgnoreCase(word) == true) {

			return 1;
		}

		n++;

		BinTree currentNode, currentNode1;

		for (int i=n; i<=(n_nodes/2); i++) {

			newTree.createRoot(1, map.get(i));

			for (int j=(i*2); j<= (i*2)+1; j++) {

				if (j%2==0) {

					newTree.addYesNode(i,j, map.get(j-1));
				}

				else {

					newTree.addNoNode(i,j, map.get(j-1));
				}
				
			}

			currentNode = rootNode.yesBranch;
			currentNode1 = rootNode.noBranch;

			if (currentNode.skill.equalsIgnoreCase(word) == true) {

				return 1;
			}

			else if (currentNode1.skill.equalsIgnoreCase(word) == true) {

				return 1;
			}
		}

		return 0;

	}

}