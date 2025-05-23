import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {

        String[] files = {"out1.txt", "out2.txt", "out3.txt"};

        ServerSocket handshaking_socket = new ServerSocket(5000);
        System.out.println("Server is connected at port no: 5000");
        System.out.println("Waiting for client...");

        Socket plain_socket = handshaking_socket.accept();
        System.out.println("Client connected from port " + plain_socket.getPort());

        DataInputStream inputdata = new DataInputStream(plain_socket.getInputStream());
        BufferedWriter[] outfiles = new BufferedWriter[3];

        for (int i = 0; i < 3; i++) {
            outfiles[i] = new BufferedWriter(new FileWriter(files[i]));
        }
        while (true) {
            byte[] buffer = new byte[6];
            int bit = inputdata.read(buffer);

            if (bit == -1) {
                break;
            }

            boolean isend = true;

            for (int i = 0; i < 3; i++) {
                byte byte1 = buffer[i * 2];
                byte byte2 = buffer[i * 2 + 1];

                if (byte1 != '#') {
                    outfiles[i].write((char) byte1);
                    System.out.print((char) byte1 + " ");
                    isend = false;
                }
                System.out.println();
                if (byte2 != '#') {
                    outfiles[i].write((char) byte2);
                    System.out.print((char) byte2 + " ");
                    isend = false;
                }
            }
            if (isend) {
                break;
            }
        }

        for (BufferedWriter writer : outfiles) {
            writer.close();
        }
        inputdata.close();
        plain_socket.close();
        handshaking_socket.close();
    }
}
