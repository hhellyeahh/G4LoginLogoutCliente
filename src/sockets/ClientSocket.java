package sockets;

import classes.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ResourceBundle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 2dam
 */
public class ClientSocket {

    private final ResourceBundle configFile = ResourceBundle.getBundle("config.config");
    private final Integer PUERTO = Integer.parseInt(configFile.getString("PORT"));
    private final String HOST = configFile.getString("HOST");
    private Message msgRecibo = new Message();

    public Message sendRecieve(Message mesg) throws IOException {
        try {
            Socket skCliente = new Socket(HOST, PUERTO);

            OutputStream palServer = skCliente.getOutputStream();
            ObjectOutputStream flujo2 = new ObjectOutputStream(palServer);
            flujo2.writeObject(mesg);

            //RECIBO
            InputStream aux = skCliente.getInputStream();
            ObjectInputStream flujo = new ObjectInputStream(aux);
            msgRecibo = (Message) flujo.readObject();
            skCliente.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return msgRecibo;
    }

}
