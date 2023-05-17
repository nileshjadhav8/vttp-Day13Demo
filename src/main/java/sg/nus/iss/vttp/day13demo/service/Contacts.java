package sg.nus.iss.vttp.day13demo.service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Path;
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

    public void getAllContactInURI(Model model, String dataDir) {
        
    }
    
    

   
    public Contact getContactById(String contactId, String dataDir) {
        Contact ctc = new Contact();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       // try {
            java.nio.file.Path filePath = new File(dataDir + "/" + contactId+".txt").toPath();
            //if(!filePath.toString().contains(".txt"))
            System.out.println("------>"+filePath);
           Charset charset = Charset.forName("UTF-8");
            List<String> stringList = new ArrayList<String>();
            try {
                stringList = Files.readAllLines(filePath, charset);
           
            ctc.setId(contactId);
            ctc.setName(stringList.get(0));
            ctc.setEmail(stringList.get(1));
            ctc.setPhoneNumber(stringList.get(2));
            LocalDate dob = LocalDate.parse(stringList.get(3), formatter);
            ctc.setDateOfBirth(dob);

            //System.out.println(ctc.getId());
        } catch (IOException e) {
            e.printStackTrace();
            return null; 
        }
            return ctc;
      //  } catch (IOException e) {
          
          
      //  }
    }
    
}
