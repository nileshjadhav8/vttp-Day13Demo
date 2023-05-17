package sg.nus.iss.vttp.day13demo.utility;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class Utility {

    public boolean isUniqueEmail(String email){
///     call service to check in database to check duplicate email id
    return false;
}

public static void createDir(String path) {
    File dir = new File(path);
    boolean isDirCreated = dir.mkdirs();
    System.out.println("dir created: " +isDirCreated);
}   
}
