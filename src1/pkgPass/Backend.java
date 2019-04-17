package pkgPass;

import java.util.Scanner;
import java.io.File;

public class Backend {

    private final int SUB_DIR_COUNT = 3;

    private String basePath;
    
    public Backend(String basePath) {
        this.basePath = basePath;
    }

    public Backend() {
        this.basePath = "passwords/full_counted/output/";
    }

    /**
     * Exact match
     * @param password
     * @return
     * @throws Exception
     */
    public int search(String password) throws Exception {

        String path = basePath;
        try{
            // Make path to file
            for(int i=0; i<SUB_DIR_COUNT; ++i) {
                char c = password.charAt(i);
                if(Character.isDigit(c) || Character.isLetter(c)) {
                    path += c + "/";
                }
                else {
                    path += "symbols/";
                }
            }  

            // Remove the last '/'
            path = path.substring(0, path.length()-1);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new Exception();
        }

        Scanner scan = null;
        try{

            // Open file to search
            scan = new Scanner(new File(path));
        }
        catch(Exception e) {
            throw new Exception();
        }

        String temp = null;
        
        // Linear search for password
        while(scan.hasNextLine()) {

            // Check if this line is password being searched for
            temp = scan.nextLine();
            int index = -1;
            if(temp.matches(password + ":\\d*")) {
                    
                // Get index of start of count value
                for(int i = temp.length()-1; i >= 0; --i) {
                    if(temp.charAt(i) == ':') {
                        index = i+1;
                        break;
                    }
                }

                // Read out count value
                if(index != -1) {
                	scan.close();
                    return Integer.parseInt(temp.substring(index));
                }
            }
        }

        // Password not found
        scan.close();
        return -1;
    }

    
    /**
     * Contains
     * @param password
     * @return
     * @throws Exception
     */
    public int contains(String password) throws Exception {

    	Scanner scan = null;
        String path = basePath;//folder containing all the Passwords
        int numFound = 0; //Number of passwords fount
        
        // Make path to file
        for(int i=48; i<=123; ++i) {
        	if (i <= 57 || 97 <= i && i <= 123) {//char is 0-9 or a-z or a symbol
        		for(int j=48; j<=123; ++j) { 
        			if (j <= 57 || 97 <= j && j <= 123) {//char is 0-9 or a-z or a symbol
        				for(int k=48; k<=123; ++k) {
        					if (k <= 57 || 97 <= k && k <= 123){
        				        try{
        				            path = getPath(i,j,k,basePath);
        				            System.out.println(path);
        				         }catch(ArrayIndexOutOfBoundsException e) {
        				              throw new Exception();
        				         }
        				     // Open file to search
        			            scan = new Scanner(new File(path));
        				      //Search File for password
        				        String temp = null;
        				        
        				        // Linear search for password
        				        while(scan.hasNextLine()) {

        				            // Check if this line is password being searched for
        				            temp = scan.nextLine();
        				            int index = -1;
        				            if(temp.contains(password)) {
        				                    
        				                // Get index of start of count value
        				                for(int z = temp.length()-1; z >= 0; --z) {
        				                    if(temp.charAt(z) == ':') {
        				                        index = z+1;
        				                        break;
        				                    }
        				                }
        				                // Read out count value
        				                if(index != -1) {
        				                    numFound += Integer.parseInt(temp.substring(index));
        				                }
        				            }
        				        }
        					}
        					else
        						k=96;
        				}
    				}
          			else
        				j = 96;	
        		}
        	}
    		else
        		i = 96; //i increases to 97 after loop
        }   
    return numFound;
    }



private String getPath(int x,int y,int z,String bpath){
	char ic =(char)x;
	char jc =(char)y;
	char kc =(char)z;
	String str = ""+ic+jc+kc;
	
	 // Make path to file
	 for(int i=0; i<SUB_DIR_COUNT; ++i) {
	     char c = str.charAt(i);
	     if(Character.isDigit(c) || Character.isLetter(c)) {
	         bpath += c + "/";
	     }
	     else {
	         bpath += "symbols/";
	     }
	 }  

	 // Remove the last '/'
	 bpath = bpath.substring(0, bpath.length()-1);
		
	 return bpath;
	
	}
}