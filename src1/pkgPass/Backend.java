package pkgPass;

import java.util.Scanner;
import java.io.File;

public class Backend {

    private String basePath;
    
    public Backend(String basePath) {
        this.basePath = basePath;
    }

    public Backend() {
        this.basePath = "passwords/full_counted/output/";
    }

    public int search(String password) throws Exception {

        String lowerPw = password.toLowerCase();
        String path = null;
        try{
            // Make path to file
            path = basePath + lowerPw.charAt(0) + "/" + lowerPw.charAt(1) + "/" + lowerPw.charAt(2);

            // TODO: account for symbols in first 3 chars
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }

        Scanner scan = null;
        try{

            // Open file to search
            scan = new Scanner(new File(path));
        }
        catch(Exception e) {
            System.out.println("here: " + path+"\n"+e);
            throw new Exception();
        }

        String temp = null;
        
        // Linear search for password
        while(scan.hasNextLine()) {

            // Check if this line is password being searched for
            temp = scan.nextLine();
            int index = -1;
            if(temp.matches(password + ":\\d+")) {
                    
                // Get index of start of count value
                for(int i = temp.length()-1; i >= 0; --i) {
                    if(temp.charAt(i) == ':') {
                        index = i+1;
                        break;
                    }
                }

                // Read out count value
                if(index != -1) {
                    return Integer.parseInt(temp.substring(index));
                }
            }
        }

        // Password not found
        return -1;
    }

//    public static void main(String[] args) {
//        Backend b = new Backend();
//        try {
//            System.out.println("Result: " + b.search(args[0]));
//        }
//        catch(Exception e) {
//            System.out.println("Oops");
//        }
//    }

}
