
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import sockets.ClientSocket;
import classes.*;
import exceptions.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */
public class ClientImplementation implements LoginLogout {

    private ClientSocket clientSocket = new ClientSocket();

    @Override
    public User logIn(User user) throws IncorrectLoginException, ServerException, UnknownTypeException, MaxUserException {

        Message message = null;

        try {
            //Creo un mensaje y establezco valores 
            message = new Message();
            message.setUser(user);
            message.setCallType(Type.LOGIN_REQUEST);
            message.setSignIn(new SignIn(new Timestamp(System.currentTimeMillis()), user.getId()));

            Message returnMessage = clientSocket.sendRecieve(message);
            //Analizar el mensaje de resupuesta
            switch (returnMessage.getCallType()) {
                case SERVER_ERROR_RESPONSE:
                    throw new ServerException("Error at reaching the server");

                case INCORRECT_LOGIN_RESPONSE:
                    throw new IncorrectLoginException("User or password is incorrect or user does not exist");

                case MAX_USERS_EXCEPTION:
                    throw new MaxUserException("Server can not handle more users");

                case OKAY_RESPONSE:
                    user = returnMessage.getUser();
                    break;

                default:
                    throw new UnknownTypeException("Unknown type of message");
            }

        } catch (IOException ex) {
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    @Override
    public User signUp(User user) throws ServerException, UserAlreadyExistExpection, UnknownTypeException, MaxUserException {

        Message message = null;

        try {
            //Creo un mensaje y establezco valores 
            message = new Message();
            message.setUser(user);
            message.setCallType(Type.SIGNUP_REQUEST);
            message.setSignIn(new SignIn(new Timestamp(System.currentTimeMillis()), user.getId()));

            Message returnMessage = clientSocket.sendRecieve(message);
            //Analizar el mensaje de resupuesta
            switch (returnMessage.getCallType()) {
                case SERVER_ERROR_RESPONSE:
                    throw new ServerException("Error at reaching the server");

                case USER_ALREADY_EXIST_RESPONE:
                    throw new UserAlreadyExistExpection("User already exists");

                case MAX_USERS_EXCEPTION:
                    throw new MaxUserException("Server can not handle more users");

                case OKAY_RESPONSE:
                    user = returnMessage.getUser();
                    break;

                default:
                    throw new UnknownTypeException("Unknown type of message");
            }

        } catch (IOException ex) {
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

}
