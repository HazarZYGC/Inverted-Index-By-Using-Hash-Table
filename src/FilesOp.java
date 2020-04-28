import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;

public class FilesOp {
	
	private String DELIMITERS= "[-+=" +" " + "\r\n " + "1234567890" + "â€™'\"" + "(){}<>\\[\\]" + ":" + "," + "â€’â€“â€”â€•" + "â€¦" + "!" + "." + "Â«Â»" + 
	"-â€�" + "?" + "â€˜â€™â€œâ€�" + ";" +  "/" +  "â�„" + "â� " + "Â·" +  "&" + "@" + "*" + "\\" + "â€¢" + "^" + "Â¤Â¢$â‚¬Â£Â¥â‚©â‚ª" + "â€ â€¡" + "Â°" +  "Â¡" + "Â¿" + "Â¬" +  
	"#" +"â„–" + "%â€°â€±" + "Â¶" + "â€²" + "Â§" +"~" + "Â¨" +  "_" + "|Â¦" + "â�‚" + "â˜�" + "âˆ´" + "â€½" + "â€»" + "]";
	
	//delimeters.
	
	private String stopWords[];
	
	@SuppressWarnings("unused")
	private String words1000[];
	//for search op.
	
	public FilesOp() {
		
		stopWords = StopSearchWordsReader("stop_words_en.txt");
		words1000 = StopSearchWordsReader("1000.txt");
	}
	//constructor.
	
	//controlling if stop words array contain the word. 
	protected boolean contain(String stop[],String word) {
		boolean flag = true;
		for (int i = 0; i < stop.length; i++) {
			if(stop[i].equalsIgnoreCase(word)) {
				flag = false;
				break;
				
			}
		}
		return flag;
	}
	//contain function.
	
	//
	//
	protected String[] StopSearchWordsReader(String directory) {
		BufferedReader br = null;
		String text = "";
		try {
		    try {
		        br = new BufferedReader(new FileReader(directory));
		        String line;
		        while ((line = br.readLine()) != null) {
		        	text += line + " ";
		        	
		        	
		        }

		    } finally {
		        br.close();
		    }
		} catch (Exception e) {
		    throw new RuntimeException("Error while reading");
		}
		String Words[]= text.split(" ");
		return Words;
	}
	// words reader.
	
	
	protected String[] MultipleFileReader(String srcDir) {
		
		BufferedReader br = null;
		String text = "";
		br = null;
		try {
		    try {
		    	br = new BufferedReader( new FileReader(srcDir));

		        String line;
		        while ((line = br.readLine()) != null) {
		        	text=text +line +" " ;
		        	
		        }

		    } finally {
		        br.close();
		    }
		} catch (Exception e) {
		    throw new RuntimeException("Error while reading");
		}
		
		
		//removing delimeters from words.....
		String splitted[] = text.split(DELIMITERS);	
		String words[] = new String[splitted.length];
		int b =0;
		//removing stop words from words..
		for (int j = 0; j < splitted.length; j++) {
			if(splitted[j].length()>0 && contain(stopWords,splitted[j])) {
				words[b] = splitted[j].toLowerCase(Locale.ENGLISH);
				b++;
			}
			
		}
		return words;
	}
	//multiple......
	
	
	
	
	//GET - SET FUNCTIONS......

	public String[] getStopWords() {
		return stopWords;
	}

	public void setStopWords(String[] stopWords) {
		this.stopWords = stopWords;
	}

	public String[] getWords1000() {
		return words1000;
	}

	public void setWords1000(String[] words1000) {
		this.words1000 = words1000;
	}
		
	
	
	
	

}
		
