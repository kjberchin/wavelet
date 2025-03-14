import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> stringList = new ArrayList<String>();

    public String handleRequest(URI url) {
        String[] stringparameters = url.getQuery().split("=");
        if (url.getPath().equals("/")) {
            return String.format("Please Input A Path");
        } else if (url.getPath().equals("/add")) {
            stringList.add(stringparameters[1]);
            for (String i: stringparameters){
                System.out.println(i);
            }
            //return String.format("Number incremented!");
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                for (String i: stringList){
                    if (i.contains(parameters[1])){
                        System.out.println(i);
                    }
                }
                // if (parameters[0].equals("count")) {
                //     num += Integer.parseInt(parameters[1]);
                //     return String.format("Number increased by %s! It's now %d", parameters[1], num);
                }
            }
            return "404 Not Found!";
        }
    }

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
