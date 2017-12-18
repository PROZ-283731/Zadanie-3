package atj.ws;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class WebServiceAttachmentClient {
  public static void main(String[] args) {
    Attachment attachment = (new AttachmentService()).getAttachmentPort();
    
    System.out.println("Menu:");
    System.out.println("1. Upload");
    System.out.println("2. Download");
  
    Scanner reader = new Scanner(System.in);  // Reading from System.in
    System.out.println("Enter a number: ");
    int n = reader.nextInt();
    System.out.println("Enter a filename: ");
    String fn = reader.nextLine();
    byte[] data;
    
    try {
      switch (n) {
        case 1:
          data = Files.readAllBytes(new File(fn).toPath());
          attachment.uploadFile(fn, data);
          break;
        case 2:
          data = attachment.downloadFile(fn);
          FileOutputStream out = new FileOutputStream(fn);
          out.write(data);
          out.close();
          break;
        default:
          break;
      }
    }
    catch (IOException ie)
    {
      System.out.println("Operation failed: " + ie);
    }
    catch (atj.ws.IOException_Exception ie)
    {
      System.out.println("Operation failed: " + ie);
    }

    
    reader.close();
  }
}
