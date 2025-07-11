import java.io.*;
import java.net.*;

public class Roll32_Receiver {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output1.txt"));

        StringBuilder receivedData = new StringBuilder();
        int ch;
        while ((ch = br.read()) != -1) {
            receivedData.append((char) ch);
        }

        String chipCodeStr = "101";
        int[] chipCode = new int[chipCodeStr.length()];
        for (int i = 0; i < chipCodeStr.length(); i++) {
            chipCode[i] = chipCodeStr.charAt(i) - '0';
        }

        String[] spreadBits = receivedData.toString().trim().split("\\s+");
        StringBuilder binaryBuilder = new StringBuilder();

        for (int i = 0; i < spreadBits.length; i += chipCode.length) {
            int bit = 0;
            for (int j = 0; j < chipCode.length && (i + j) < spreadBits.length; j++) {
                int spreadBit = Integer.parseInt(spreadBits[i + j]);
                bit ^= chipCode[j] ^ spreadBit;
            }
            binaryBuilder.append(bit);
            if (binaryBuilder.length() == 8) {
                int ascii = Integer.parseInt(binaryBuilder.toString(), 2);
                bw.write((char) ascii);
                binaryBuilder.setLength(0);
            }
        }

        bw.close();
        br.close();
        socket.close();
        serverSocket.close();
        System.out.println("Data received and saved to output1.txt");
    }
}