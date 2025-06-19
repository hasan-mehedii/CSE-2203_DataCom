import java.io.*;
import java.net.*;
import java.util.*;

// Roll 32 and 22

public class Client {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Random rand = new Random();

            String d = sc.nextLine();

            String serverAddress = "10.33.2.101";
            int serverPort = 5000;

            Socket socket = new Socket(serverAddress, serverPort);
            int clientPort = socket.getLocalPort();

            System.out.println("Client connected to the server on Handshaking port " + serverPort);
            System.out.println("Client’s Communication Port: " + clientPort);
            System.out.println("Client is Connected");
            System.out.println("File Content: " + d);

            String bin = stringToBinary(d);
            System.out.println("Converted Binary Data: " + bin);

            Scanner polyScanner = new Scanner(System.in);
            String generatorPolynomial = polyScanner.nextLine();
            int k = generatorPolynomial.length();

            String paddedData = bin + "0".repeat(k - 1);
            System.out.println("After Appending zeros Data to Divide: " + paddedData);

            String Remainder = CRC(paddedData, generatorPolynomial);
            System.out.println("CRC Remainder: " + Remainder);

            String codeword = bin + Remainder;
            System.out.println("Transmitted Codeword to Server: " + codeword);

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String code1 = "100000001";

            String code2 = "100100001";

            String Polynomial = "1101";

            /*
             * int random = rand.nextInt(5,9);
             * 
             * System.out.println(random);
             * 
             * 
             * if(random>5)
             * {
             * StringBuilder sb = new StringBuilder(codeword);
             * 
             * String str = sb.toString();
             * 
             * 
             * dos.writeUTF(str);
             * 
             * 
             * 
             * }
             * else
             * {
             * StringBuilder sb = new StringBuilder(codeword);
             * 
             * char temp = sb.charAt(0);
             * sb.setCharAt(0, sb.charAt(1));
             * sb.setCharAt(1, temp);
             * 
             * String str = sb.toString();
             * 
             * dos.writeUTF(str);
             * }
             */

            dos.writeUTF(codeword);
            dos.writeUTF(Polynomial);

            dos.close();
            socket.close();

        }

        catch (Exception e)

        {
            e.printStackTrace();
        }
    }

    private static String stringToBinary(String str) {
        StringBuilder binary = new StringBuilder();
        for (char c : str.toCharArray()) {
            String bin = Integer.toBinaryString(c);

            while (bin.length() < 8) {
                bin = "0" + bin;
            }

            binary.append(bin);
        }
        return binary.toString();
    }

    private static String CRC(String data, String generator) {
        int dataLength = data.length();
        int genLength = generator.length();
        char[] dataArr = data.toCharArray();
        char[] genArr = generator.toCharArray();
        for (int i = 0; i <= dataLength - genLength; i++) {
            if (dataArr[i] == '1') {
                for (int j = 0; j < genLength; j++) {
                    dataArr[i + j] = (dataArr[i + j] == genArr[j]) ? '0' : '1';
                }
            }
        }

        StringBuilder remainder = new StringBuilder();

        for (int i = dataLength - genLength + 1; i < dataLength; i++) {
            remainder.append(dataArr[i]);
        }
        return remainder.toString();
    }
}