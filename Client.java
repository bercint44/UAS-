import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Buat BufferedReader untuk membaca input dari pengguna
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Meminta pengguna untuk memasukkan alamat IP dan port
            System.out.print("Masukkan alamat IP server: ");
            String serverIP = userInput.readLine();
            System.out.print("Masukkan port server: ");
            int serverPort = Integer.parseInt(userInput.readLine());

            // Buat socket untuk terhubung ke server dengan alamat IP dan port yang ditentukan pengguna
            Socket socket = new Socket(serverIP, serverPort);
            // Buat input dan output stream untuk berkomunikasi dengan server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String command;
            do {
                // Baca input dari pengguna dan kirim ke server
                System.out.print("Masukkan perintah untuk dijalankan di server (exit untuk keluar): ");
                command = userInput.readLine();

                // Kirim perintah ke server
                out.println(command);

