
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
 * @author ZuluyBonilla
 */
public class ClientImplementation implements LoginLogout {

    private ClientSocket clientSocket = new ClientSocket();

    /**
     * This method checks if the user is found in the database.
     * If there were any errors, it would be caught and a message would be sent
     * depending on the given error.
     * @param user User who wants to log in
     * @return user who wants to log in
     * @throws IncorrectLoginException if the user or the password does not exist
     * @throws ServerException if the server does not response
     * @throws UnknownTypeException all the others excepcions 
     * @throws MaxUserException if server can not handle more users
     */
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

    /**
     * This method checks if the user when registering is in the database,
     * if it were, it would give you an error. It also catches other types of errors.
     * @param user User who wants to sign up
     * @return user who wants to sign up
     * @throws ServerException if the server does not response
     * @throws UserAlreadyExistExpection if the user already exist
     * @throws UnknownTypeException all the others excepcions 
     * @throws MaxUserException if server can not handle more users
     */
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
