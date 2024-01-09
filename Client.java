import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Buat BufferedReader untuk membaca input dari pengguna
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Meminta pengguna untuk memasukkan alamat IP dan port
            System.out.print("Masukkan alamat IP server: ");
            
            //membaca baris input dari pengguna dan menyimpannya dalam variabel serverIP
            String serverIP = userInput.readLine();
            System.out.print("Masukkan port server: ");

            //membaca baris input dari pengguna dan menyimpannya dalam variabel serverPort
            int serverPort = Integer.parseInt(userInput.readLine());

            // Buat socket untuk terhubung ke server dengan alamat IP dan port yang ditentukan pengguna
            Socket socket = new Socket(serverIP, serverPort);
            
            // Buat input dan output stream untuk berkomunikasi dengan server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Membuat objek PrintWriter untuk menulis data ke soket dengan opsi autoflush diaktifkan
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String command;
            do {
                // Baca input dari pengguna dan kirim ke server
                System.out.print("Masukkan perintah untuk dijalankan di server (exit untuk keluar): ");

                //Membaca input dari pengguna dan menyimpannya dalam variabel command
                command = userInput.readLine();

                // Kirim perintah ke server
                out.println(command);
                
                // Baca dan tampilkan output dari server
                String serverResponse;

                //Gunakan loop while untuk membaca dan mencetak respon dari server dengan menggunakan objek
                //BufferedReader (in), hingga menerima pesan "Server: Eksekusi perintah selesai.".
                
                while (!(serverResponse = in.readLine()).equals("Server: Eksekusi perintah selesai.")) {
                    System.out.println(serverResponse);
                }
            } 
                //Lakukan selama perintah bukan exit
                while (!command.equalsIgnoreCase("exit"));
            // Tutup koneksi dan resources
           //tutup aliran masukan (input stream)
            in.close();

            //tutup aliran keluaran (output stream). 
            out.close();

            //tutup soket
            socket.close();
        } 
        //menangkap dan mencetak jika terjadi kesalahan input/output (IOException)
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

