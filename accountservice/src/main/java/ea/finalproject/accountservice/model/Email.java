package ea.finalproject.accountservice.model;

import lombok.Data;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

@Data
public class Email {
    private List<String> receivers;
    private String subject;
    private String message;
    private String filePath;

    public Email(){
        receivers = new ArrayList<>();
    }

    public void addReceiver(String receiver){
        this.receivers.add(receiver);
    }

    public InternetAddress[] getReceivers(){
        String adds = "";
        for(String s:receivers){
            adds = adds+","+s;
        }
        adds = adds.substring(1).trim();
        System.out.println(adds);
        try {
            return InternetAddress.parse(adds);
        } catch (AddressException e) {
            System.out.println("Address exception");
            e.printStackTrace();
        }
        return null;
    }
}