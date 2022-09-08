package Cliente;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String [] args) throws IOException {

        Socket socket = new Socket("localhost",1234);
        System.out.println("Conectado ao Servidor!!");

        JFrame jFrame = new JFrame("Cliente");
        jFrame.setSize(400,400);
        // usado para quando sairmos do programa ja fechar automatico com essa linha de codigo
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Ygor Kwan\\Downloads\\Imagem.png");

        // usado para abrir uma string ou imagem curta
        JLabel jLabelPicture = new JLabel(imageIcon);

        // usado para cria um botao de rotulo para quando for acionado fazer uma ação desejada.
        // para o caso do exemplo foi usado para enviar imagem para o servidor
        JButton jButton = new JButton("Enviar imagem para o servidor.");

        // adicionado para o layout criado no jframe
        // criação do container
        jFrame.add(jLabelPicture);
        jFrame.add(jButton);

        // usado para aparecer o jframe na hora que rodar o codigo
        jFrame.setVisible(true);

        // metodo que serve como notificação que retorna para informar da ação tomada dentro do programa
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eevent) {
                try{
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedOutputStream bufferedOutputStream= new BufferedOutputStream(outputStream);

                    Image image = imageIcon.getImage();

                    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight(null), BufferedImage.TYPE_INT_RGB);

                    Graphics graphics = bufferedImage.createGraphics();
                    graphics.drawImage(image,0,0,null);
                    graphics.dispose();

                    // grava a imagem
                    ImageIO.write(bufferedImage,"png",bufferedOutputStream);

                    bufferedOutputStream.close();
                    socket.close();

                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        });

    }
}
