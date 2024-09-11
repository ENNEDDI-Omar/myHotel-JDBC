package Service;

import Entities.User;
import Repository.UserRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {
    private UserRepository userRepository;

    public UserService(){
        this.userRepository = new UserRepository();
    }

    public void signUp(String name, String email , String password){
        String hashedPassword = hashPassword(password);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userRepository.insertUser(user);
    }
    public User signIn(String email , String password){
        String hashedPassword = hashPassword(password);
        return userRepository.getUserByEmailAndPassword(email, hashedPassword);
    }

    private String hashPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for(byte b : hash){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
