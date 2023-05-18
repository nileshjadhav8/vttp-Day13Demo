package sg.nus.iss.vttp.day13demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.nus.iss.vttp.day13demo.model.Contact;
import sg.nus.iss.vttp.day13demo.service.Contacts;
import sg.nus.iss.vttp.day13demo.utility.Utility;


@Controller
@RequestMapping(path = "/")
public class AddressBookController {


    @Autowired
    Utility utility;

    @Autowired
    Contacts service;
    
    @Value("${data.dir}")
    private String dataDir;

//request method to load landing page
@GetMapping
public String showAddressBook(Model model){
    model.addAttribute("contact", new Contact());
    return "addressBook";
}


///to save the contact information
@PostMapping( consumes ="application/x-www-form-urlencoded", path=  "/contact")
public String saveAddressBook(@Valid Contact contact, BindingResult bindingResult,Model model){
    
    if(bindingResult.hasErrors()){
       return "addressBook";  

        }

     //custom data validation
       /*  if(!utility.isUniqueEmail(contact.getEmail())){
            ObjectError err = new ObjectError("globalError","%s is not available".formatted(contact.getEmail())); 
            bindingResult.addError(err);
        }*/


        service.save(contact, model, dataDir);
        model.addAttribute("successMessage", "Contact saved successfully, with status code: " +HttpStatus.CREATED +".");
    return "showContact";
}



   @GetMapping("/contact/{contactId}")
    public String getContactById(Model model, @PathVariable String contactId) {
        
       Contact contact =  new Contact();
       
       contact = service.getContactById(contactId, dataDir);
        if (contact == null) {
            model.addAttribute("errorMessage", "Contact not found");
            return "error";
        }
        model.addAttribute("contact", contact);
        return "showContact";
    }

    @GetMapping(path = "/list")
    public String getAllContacts(Model model) {
        service.getAllContactInURI(model, dataDir);
        return "contacts";
    }
    
}
