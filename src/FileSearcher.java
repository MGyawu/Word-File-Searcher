/*Mead Gyawu
 * HW 5
 * I have neither given not received unauthorized aid on this assignment
 * */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FileSearcher {
	
	public static BinarySearchTree BST = new BinarySearchTree();
	public static ArrayList<Word> Wordlist = new ArrayList<Word>();

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String filename = args[0];
		
		File dir = new File(filename);
		
		scanfiles(dir);
		
		Scanner scan = new Scanner(System.in);
		String answer = "";
		System.out.println("a:Shows all of the words, and their respective files within  a given directory");
		System.out.println("s:Show the files containing a word you are looking for");
		System.out.println("q:quit");
		while(!answer.equals("q")) {
			System.out.println("Please enter a command (a, s, or q)>> ");
			answer = scan.nextLine();
			if(answer.equals("a"))
				a();
			if(answer.equals("s")) {
				System.out.println("Word to find>> ");
				s(scan.nextLine());
			}
		}
	}
	
	public static void s(String word) {
		//prompts the user to pic a letter
		Word newWord = new Word();
		newWord.setText(word);
		if(BST.contains(newWord)) {
			System.out.print("Files containing the word "+word+": ");
			newWord = (Word)BST.find(newWord);
			newWord.printFileList();
		}else {
			System.out.println(word+" is not found");
		}
	}
	
	public static void a() {
		//prints each word and the files they are in
		for(int i = 0; i < Wordlist.size();i++) {
			System.out.print("Files containing the word "+Wordlist.get(i).getText()+": ");
			Wordlist.get(i).printFileList();
		}
		System.out.println();
	}
	
	public static void scanfiles(File folder) throws FileNotFoundException{
		//scans the files and adds each word to BST
		File[] list = folder.listFiles();
		for(int i = 0; i < list.length;i++) {
			if(!list[i].isHidden()&& list[i].getName().charAt(0)!= '.') {
				if(list[i].isDirectory())
					scanfiles(list[i]);
				else {
					Scanner scan = new Scanner(list[i]);
					while(scan.hasNextLine()) {
						String[] words = scan.nextLine().split(" ");
						for(int a = 0; a < words.length; a++) {
							if(words[a].charAt(words[a].length()-1) < 65 ) {
								words[a] = words[a].substring(0,words[a].length()-1);
							}
							Word w = new Word();
							w.setText(words[a]);
							if(BST.contains(w)) {
								Word w_InTree = (Word) BST.find(w);
								w_InTree.add(list[i].toString());
							}else {
								w.setFileList();
								w.add(""+list[i]);
								BST.insert(w);
								Wordlist.add(w);
							}
						}
					}
				}
			}	
		}
	}

	
}
