import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is connected at port no: 5000");
            System.out.println("Server is connecting");
            System.out.println("Waiting for the client");
            Socket socket = serverSocket.accept();
            System.out.println("Client request is accepted at port no: " + socket.getPort());
            System.out.println("Server’s Communication Port: " + serverSocket.getLocalPort());

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            String rececode = inputStream.readUTF();
            System.out.println("Received Codeword: " + rececode);

            String kkey = "1101";

            String remainder = CRC(rececode, kkey);
            System.out.println("Calculated Remainder: " + remainder);

            boolean errdet = false;
            for (char c : remainder.toCharArray()) {
                if (c != '0') {
                    errdet = true;
                    break;
                }
            }

            if (errdet) {
                System.out.println("Error detected in transmission!");
            } else {
                System.out.println("No error detected in transmission.");
            }

            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String CRC(String data, String key) {
        int keylen = key.length();

        String padata = data + "0".repeat(keylen - 1);

        String divd = padata.substring(0, keylen);
        StringBuilder remin = new StringBuilder(divd);

        for (int i = keylen; i < padata.length(); i++) {
            if (remin.charAt(0) == '1') {
                remin = new StringBuilder(xorS(remin.toString(), key));
            } else {
                remin = new StringBuilder(xorS(remin.toString(), "0".repeat(keylen)));
            }

            remin.deleteCharAt(0);
            remin.append(padata.charAt(i));
        }

        if (remin.charAt(0) == '1') {
            remin = new StringBuilder(xorS(remin.toString(), key));
        } else {
            remin = new StringBuilder(xorS(remin.toString(), "0".repeat(keylen)));
        }

        return remin.substring(1);
    }

    public static String xorS(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }
}
