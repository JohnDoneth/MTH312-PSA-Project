public class Backend {

    private String basePath;
    

    public Backend(String basePath) {
        this.basePath = basePath;
    }

    /**
     * Search and get instance count for a password.
     *
     * @param password - password being searched for.
     * @return number if instances. If not found -1 is returned.
     */
    public int search(String password) {

        // Make path to file
        String path = basePath + "/" + password.charAt(0) + "/" + password.charAt(1) + "/" + password.charAt(2);

        // Open file
        Scanner scan = new Scanner(path);
        
        // Linear search for password
        while(scan.hasNextLine()) {
            

            // Check if this line is password being searched for
            String temp = scan.nextLine();
            if(temp.matches(password + ":\\d+")) {
                    
                // Get index of start of count value
                int startIndex = 0;
                for(int i = temp.length()-1; i >= 0; --i) {
                    if(temp.charAt(i) == ':') {
                        startIndex = i+1;
                        break;
                    }
                }
            }
        }

        // Password not found
        return -1;
    }

}
