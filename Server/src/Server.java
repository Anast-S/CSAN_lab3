import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static String DOMAIN_NAME="";
    public static int PORT_NUMBER=0;
    public static int DOMAIN_SIZE=0;

    public static void main(String[] args) throws IOException {
        //initialization
        Scanner scanner = new Scanner(System.in);
        Socket socket=null;
        ServerSocket serverSocket=null;
        OutputStream outputStream=null;
        InputStream inputStream=null;
        JSONParser parser = new JSONParser();

        System.out.print("enter the port : ");
        PORT_NUMBER = scanner.nextInt();

        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            while (true) {
                System.out.print("Waiting...");
                try {
                    socket = serverSocket.accept();
                    System.out.println("Accepted connection : " + socket);
                    outputStream = socket.getOutputStream();
                    inputStream = socket.getInputStream();

                    //receiving domain size and name
                    DOMAIN_SIZE = inputStream.read();
                    byte[] arrDomain = new byte[DOMAIN_SIZE];
                    inputStream.read(arrDomain);
                    DOMAIN_NAME = new String(arrDomain);

                    Object obj = parser.parse(new FileReader("C:/Users/selev/Desktop/lab3/Server/Whois.json"));
                    JSONObject jsonObject = (JSONObject) obj;

                    try {
                        //sending a domain info
                        JSONObject list = (JSONObject) jsonObject.get(DOMAIN_NAME);
                        byte[] arrInfo = list.toString().getBytes();
                        outputStream.write(arrInfo, 0, arrInfo.length);

                    } catch (NullPointerException e) {
                        System.out.print("The work was stopped : " + e.getMessage());
                    } finally {
                        outputStream.flush();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    if (socket != null) socket.close();
                    if (outputStream != null) outputStream.close();
                    if (inputStream != null) inputStream.close();
                    scanner.close();
                }
            }
        }finally {
            if (serverSocket != null) serverSocket.close();
        }
    }
}
