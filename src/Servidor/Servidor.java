package Servidor;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[]args) throws IOException {
        JFrame jFrame = new JFrame("Servidor");
        jFrame.setSize(400,400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jLabelText = new JLabel("Aguardando imagem do cliente.....");

        // adicionado no contaienr para ser exibido perante ação / o segundo argumento vai indicar que estara na parte inferior do jframe
        jFrame.add(jLabelText,BorderLayout.SOUTH);

        // ativando visibilidade do programa
        jFrame.setVisible(true);

        ServerSocket serverSocket = new ServerSocket(1234);

        //criacao do objeto socket que sera um metodo aceept de espera que ao passar por essa linha de codigo em sua
        //execução seja retornado um socket para que possamos conectar o cliente porque o servidor e cliente se
        // comunicam lendo e escrevendo ou seja escrevem em um fluxo de saida e leem em um fluxo de entrada
        Socket socket = serverSocket.accept();

        // vai ler as informações do cliente
        InputStream inputStream = socket.getInputStream();
        // como o cliente teve um buffer para saida da imagem no servidor ele tera um buffer para entrada da imagem
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);

        // fechando o fluxo de entrada
        bufferedInputStream.close();
        // fechando o socket
        socket.close();

        JLabel jLabelPicture = new JLabel(new ImageIcon(bufferedImage));
        jLabelText.setText("Imagem Recebida");
        jFrame.add(jLabelPicture,BorderLayout.CENTER);

    }

}
