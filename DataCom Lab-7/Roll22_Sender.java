import java.io.File;
import java.io.*;
import java.net.*;

public class Roll22_Sender {
    public static void main(String[] args) throws IOException {
        String chipCodeStr = "101";
        int[] chipCode = new int[chipCodeStr.length()];
        for (int i = 0; i < chipCodeStr.length(); i++) {
            chipCode[i] = chipCodeStr.charAt(i) - '0';
        }

        String address = "localhost";
        int port = 5000;
        Socket socket = new Socket(address, port);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        File inputFile = new File("input1.txt");
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        File outputFile = new File("spread_input1.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

        int ch;
        StringBuilder sentData = new StringBuilder();
        while ((ch = br.read()) != -1) {
            String binary = String.format("%8s", Integer.toBinaryString(ch)).replace(' ', '0');
            for (int i = 0; i < binary.length(); i++) {
                int bit = binary.charAt(i) - '0';
                for (int j = 0; j < chipCode.length; j++) {
                    int spreadBit = bit ^ chipCode[j];
                    bw.write(spreadBit + " ");
                    sentData.append(spreadBit).append(" ");
                }
            }
        }

        bw.close();
        br.close();

        dos.writeBytes(sentData.toString());
        System.out.println("Sent to server: " + sentData.toString());

        dos.flush();
        dos.close();
        socket.close();
    }
}