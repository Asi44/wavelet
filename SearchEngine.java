import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler  {
    ArrayList<String> strs = new ArrayList<String>();

    public String handleRequest(URI url) {

        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                strs.add(parameters[1]);
                return ("current list: " + strs);
            }

        }

        if (url.getPath().contains("/search")) {

            String[] parameters = url.getQuery().split("=");
            ArrayList<String> foundStrs = new ArrayList<>();
            String currStr;

            if (parameters[0].equals("s")) {
                for (int i = 0; i < strs.size(); i++) {
                    currStr = strs.get(i);
                    if (currStr.contains(parameters[1])) {
                        foundStrs.add(currStr);
                    }
                }
                return ("found strings: " + foundStrs);
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
