package uasz.sn.microservice_utilisateur.exception;

public class ResourceAlreadyExistException extends  RuntimeException{
    public ResourceAlreadyExistException(String message){
        super(message);
    }
}
