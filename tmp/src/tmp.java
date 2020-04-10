
//import org.json.JSONObject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

import java.io.FileReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Scanner;

public class tmp {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("C:/Users/selev/Desktop/lab3/Server/Whois.json"));
            JSONObject jsonObject;
            jsonObject = (JSONObject) obj;
            JSONObject list=(JSONObject) jsonObject.get("vk.com");
            System.out.print(list);
        } catch (ParseException e) {
            e.printStackTrace();
        }
            scanner.close();
    }
}
