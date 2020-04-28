import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;

@SuppressWarnings("unused")

public class Main {
	
	static String src = "";
	//static for directory name.

	public static void main(String[] args) throws IOException {
		
		FilesOp fileOP = new FilesOp(); 
		//new file operations object.
		
		//...........................................
		//DoubleHashMap<String,Integer> doubleHash = new DoubleHashMap<>(128);
		ProbeHashMap<String,Integer> probeHash = new ProbeHashMap<>(128);
		//...........................................
		
		String directories[]= {"business" , "sport" , "tech","politics","entertainment"};
		//directories names.
		int putco = 0;
		for (int a = 0; a < directories.length; a++) {
			
			String srcDir = directories[a];
			
			File folder = new File(srcDir);
			File[] listOfFiles = folder.listFiles();
	        
			for (int i = 0; i < listOfFiles.length; i ++) {
				src = srcDir+"/"+listOfFiles[i].getName();
				//changing static directory name.
				
				String words[] = fileOP.MultipleFileReader(srcDir+"/"+listOfFiles[i].getName());
				for (int j = 0; j < words.length; j++) {
					if(words[j]!=null) {
						
				//...........................................
				//	doubleHash.put(words[j], doubleHash.Summation(words[j]));
			    //	doubleHash.put(words[j], doubleHash.Polynomial(words[j]));
				 //  probeHash.put(words[j],probeHash.Summation(words[j]));
					probeHash.put(words[j],probeHash.Polynomial(words[j]));
				//...........................................
					
					
					}
					//if
				}
				//for

		     }
			//for
		}
		//for
		
    
		
		
	////////////////////////////////////////////////////////// TOTAL COLLISION PART.................	
	//...........................................
	//	System.out.println("Total collision :"+DoubleHashMap.counter);
        System.out.println("Total collision :"+ProbeHashMap.counter);
    //...........................................
        System.out.println("Unique words count : " +ProbeHashMap.uniqueCounter);
      // System.out.println("Unique words count : " +DoubleHashMap.uniqueCounter);
        
        
		
     ////////
        ///////
        	//////
        		 //           SEARCHING PART WITH TIMESTAMP..............
        	/////
        //////
    ///////
        
		double sumSearchTime = 0;
		double minSearchTime = Integer.MAX_VALUE;
		double maxSearchTime = 0;
		double diff=0;
		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
		String str;
		for (int i = 0; i < fileOP.getWords1000().length; i++) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			
			//...........................................
			//doubleHash.searchingWord(fileOP.getWords1000()[i]);
			probeHash.searchingWord(fileOP.getWords1000()[i]);
			//...........................................
			
			
			
			Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
			diff = (double) (timestamp2.getTime()-timestamp.getTime());
			sumSearchTime += timestamp2.getTime()-timestamp.getTime();
			if(diff < minSearchTime)
				minSearchTime = diff;
			if(diff > maxSearchTime)
				maxSearchTime = diff;
		//	str=obj.readLine();
		}
		sumSearchTime = sumSearchTime/1000;
		System.out.println("Minimum search time :" + minSearchTime);
		System.out.println("Maximum search time :" + maxSearchTime);
		System.out.println("Average search time :" + sumSearchTime);
		
		//-------------------------------------------------------------------------------------------
		 System.out.println("Count of words which are not found in hash table :"+ProbeHashMap.notfound);
		//System.out.println("Count of words which are not found in hash table :"+DoubleHashMap.notfound);
		//------------------------------------------------------------------------------------------
		
		
		
	

	}
}


