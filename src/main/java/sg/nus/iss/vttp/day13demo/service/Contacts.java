package sg.nus.iss.vttp.day13demo.service;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import sg.nus.iss.vttp.day13demo.model.Contact;

@Service
public class Contacts {

    public void save(Contact contact, Model model, String dataDir) {
    String fileName = contact.getId();
    PrintWriter printWriter = null;    
    try {
        FileWriter fileWriter = new FileWriter(dataDir+"/"+fileName+".txt");

        printWriter = new PrintWriter(fileWriter);

        printWriter.println(contact.getName());
        printWriter.println(contact.getEmail());
        printWriter.println(contact.getPhoneNumber());
        printWriter.println(contact.getDateOfBirth());


       model.addAttribute("contact", new Contact(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhoneNumber(),contact.getDateOfBirth())); 

    } catch (IOException e) {
        e.printStackTrace();
    }finally{
        printWriter.close();
}

    }
    
}
