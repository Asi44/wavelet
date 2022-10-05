import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler  {

    public String handleRequest(URI url) {

        ArrayList<String> strs = new ArrayList<String>();
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                strs.add(parameters[1]);
                return (parameters[1] + " added to list");
            }
        }
        else if (url.getPath().contains("/search")){
            String[] parameters = url.getQuery().split("=");
            String foundStrs = "";
            if (parameters[0].equals("s")) {
                for (int i =0; i <strs.size(); i++) {
                    if (strs.get(i).contains(parameters[1])){
                        foundStrs = foundStrs + strs.get(i) + " ";
                    }
                }
                if (foundStrs.equals("")) {
                    return ("String not found");
                }
                else {
                    return foundStrs;
                }
            }
        }

        return "404 Not Found";
    }

}
class SearchEngine {
        public static void main(String[] args) throws IOException {
            if(args.length == 0){
                System.out.println("Missing port number! Try any number between 1024 to 49151");
                return;
            }
    
            int port = Integer.parseInt(args[0]);
    
            Server.start(port, new Handler());
        }
    }
