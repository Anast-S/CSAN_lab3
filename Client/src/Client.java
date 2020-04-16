import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import org.apache.commons.net.whois.WhoisClient;


public class Client {

    public static String DOMAIN_NAME="";
    public static String SERVER_NAME="";
    public static String INFO="";
    public static Scanner scanner=null;
    public final static String DEFAULT_LOCAL="127.0.0.1";

    public static void main(String[] args) throws IOException {
        scanner= new Scanner(System.in);

        System.out.print("enter the domain :");
        DOMAIN_NAME = scanner.nextLine();
        System.out.print("enter the server :");
        SERVER_NAME = scanner.nextLine();

        System.out.println("WAITING...");
        if (SERVER_NAME.equals(DEFAULT_LOCAL)==false)
            globalServer();
        else
            localServer();

        System.out.print(INFO);
        scanner.close();
    }

    private static void localServer()throws IOException{
        Socket socket=null;
        OutputStream outputStream=null;
        InputStream inputStream=null;
        int INFO_SIZE=8022386;

        System.out.print("enter the port :");
        int PORT_NUMBER = scanner.nextInt();

        try {
            socket=new Socket(SERVER_NAME,PORT_NUMBER);
            inputStream=socket.getInputStream();
            outputStream=socket.getOutputStream();

            //sending domain size and name
            byte[] arrDomain= DOMAIN_NAME.getBytes();
            outputStream.write(arrDomain.length);
            outputStream.write(arrDomain);
            outputStream.flush();

            //receiving a domain info
            byte[] arrInfo=new byte[INFO_SIZE];
            INFO_SIZE=inputStream.read(arrInfo,0,arrInfo.length);
            INFO=new String(arrInfo, 0, INFO_SIZE);

        }finally {
            if (socket!=null) socket.close();
            if (inputStream!=null) inputStream.close();
            if (outputStream!=null) outputStream.close();
        }
    }

    private static void globalServer() throws IOException{
        String DEFAULT_SERVER="whois.iana.org";
        int DEFAULT_PORT=43;

        WhoisClient whoisClient = new WhoisClient();
        whoisClient.connect(DEFAULT_SERVER, DEFAULT_PORT);
        INFO = whoisClient.query(DOMAIN_NAME);
    }
}

