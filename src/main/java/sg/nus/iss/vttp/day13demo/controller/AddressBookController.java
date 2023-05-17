package sg.nus.iss.vttp.day13demo.controller;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.nus.iss.vttp.day13demo.model.Contact;

@Controller
@RequestMapping(path = "/")
public class AddressBookController {

//request method to load landing page
@GetMapping
public String showAddressBook(Model model){
    model.addAttribute("contact", new Contact());
    return "addressBook";
}


///to save the contact information
@PostMapping( consumes ="application/x-www-form-urlencoded", path=  "/contact")
public String saveAddressBook(@Valid Contact contact, BindingResult result ,Model model){
    
    System.out.println("Name: "+contact.getName());

    System.out.println("Email: "+contact.getEmail());

    System.out.println("Phone Number: "+contact.getPhoneNumber());

 if(result.hasErrors()){
    System.out.println("erro count -->" +result.getErrorCount());
 return "addressBook";  

 }
  //String name = form.getFirst("name"); 
  //String email = form.getFirst("email"); 
  //String phone = form.getFirst("phoneNumber");

  //System.out.println("using MultiValueMap:" +name);
  //System.out.println(email);
  //System.out.println(phone);
    return "addressBook";
}


}
