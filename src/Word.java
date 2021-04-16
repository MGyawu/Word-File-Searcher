import java.util.ArrayList;

public class Word implements Comparable<Word>{
	
	private String text;
	private ArrayList<String> fileList;
	
	public void setText(String text) {
		//sets the value of text
		this.text = text;
	}
	
	public String getText() {
		//returns the value of text
		return this.text;
	}
	
	public void setFileList() {
		//sets the value of fileList to an empty arraylist
		this.fileList = new ArrayList<String>();
	}
	
	public void add(String f) {
		//Adds f to fileList
		fileList.add(f);
	}
	
	public void printFileList() {
		//setFileList();
		for(int i = 0; i < fileList.size();i++) {
			int index = fileList.get(i).length() - 1;
			while(fileList.get(i).charAt(index) != 92) {
				index--;
			}
			System.out.print(fileList.get(i).substring(index+1) + " ");
		}
		System.out.println();
	}

	@Override
	public int compareTo(Word o) {
		// compares text to o.text
		return this.getText().compareTo(o.getText());
	}
	
	public String toString() {
		//allow the word to be printed
		return this.text;
	}

}
