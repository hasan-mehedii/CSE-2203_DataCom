import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String Address = "10.33.2.99";
        int port = 5000;

        Socket socket = new Socket(Address, port);

        System.out.println("Client connected to the server on Handshaking port " + socket.getPort());
        System.out.println("Client’s Communication Port: " + socket.getLocalPort());
        System.out.println("Client is Connected");
        System.out.println("Sending data to the server...");

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        StringBuilder data1 = readFile("input1.txt");
        StringBuilder data2 = readFile("input2.txt");
        StringBuilder data3 = readFile("input3.txt");

        int len1 = data1.length();
        int len2 = data2.length();
        int len3 = data3.length();

        int T = 2;

        int idx1 = 0, idx2 = 0, idx3 = 0;
        int round = 1;

        while (idx1 < len1 || idx2 < len2 || idx3 < len3) {
            byte[] buffer1 = new byte[T];
            byte[] buffer2 = new byte[T];
            byte[] buffer3 = new byte[T];



            int bytes1 = Math.min(T, len1 - idx1);
            if (bytes1 == 2) 
            {
                data1.toString().getBytes(idx1, idx1 + 2, buffer1, 0);
            } 
            else if (bytes1 == 1) {
                data1.toString().getBytes(idx1, idx1 + 1, buffer1, 0);
                buffer1[1] = '#';
            } 
            else {
                buffer1[0] = '#';
                buffer1[1] = '#';
            }
            idx1 += bytes1;


            int bytes2 = Math.min(T, len2 - idx2);
            if (bytes2 == 2) {
                data2.toString().getBytes(idx2, idx2 + 2, buffer2, 0);
            } else if (bytes2 == 1) {
                data2.toString().getBytes(idx2, idx2 + 1, buffer2, 0);
                buffer2[1] = '#';
            } else {
                buffer2[0] = '#';
                buffer2[1] = '#';
            }
            idx2 += bytes2;

            int bytes3 = Math.min(T, len3 - idx3);
            if (bytes3 == 2) {
                data3.toString().getBytes(idx3, idx3 + 2, buffer3, 0);
            } else if (bytes3 == 1) {
                data3.toString().getBytes(idx3, idx3 + 1, buffer3, 0);
                buffer3[1] = '#';
            } else {
                buffer3[0] = '#';
                buffer3[1] = '#';
            }
            idx3 += bytes3;

            byte[] packet = new byte[T * 3];
            System.arraycopy(buffer1, 0, packet, 0, T);
            System.arraycopy(buffer2, 0, packet, T, T);
            System.arraycopy(buffer3, 0, packet, 2 * T, T);



            String packetStr = new String(packet);
            System.out.println("Round " + round + ": " + packetStr);



            output.write(packet);
            round++;
        }

        output.close();
        socket.close();
    }
    
    static StringBuilder readFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = read.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}