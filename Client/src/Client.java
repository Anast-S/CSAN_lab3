import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

public class Client {

    public static String DOMAIN_NAME="";
    public static String SERVER_NAME="";
    public static int PORT_NUMBER=0;
    public static int INFO_SIZE=6022386;
    public static String INFO="";

    public static void main(String[] args) throws IOException{
        //initialization
        Scanner scanner = new Scanner(System.in);
        Socket socket=null;
        OutputStream outputStream=null;
        InputStream inputStream=null;

        System.out.print("enter the domain :");
        DOMAIN_NAME = scanner.nextLine();
        System.out.print("enter the server : ");
        SERVER_NAME = scanner.nextLine();
        System.out.print("enter the port : ");
        PORT_NUMBER = scanner.nextInt();

        socket=new Socket(SERVER_NAME,PORT_NUMBER);

        try {
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
            INFO = new String(arrInfo, 0, INFO_SIZE);
            System.out.print(INFO);

        }finally {
            if (socket!=null) socket.close();
            if (inputStream!=null) inputStream.close();
            if (outputStream!=null) outputStream.close();
            scanner.close();
        }
    }
}
