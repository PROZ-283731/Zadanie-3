package atj.ws;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@WebService()
public class Attachment {
  
  @WebMethod
  public void uploadFile(String filename, DataHandler file) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    file.writeTo(output);
    byte data[] = output.toByteArray();
    FileOutputStream out = new FileOutputStream(filename);
    out.write(data);
    out.close();
  }
  
  @WebMethod
  public DataHandler downloadFile(String filename) {
    DataSource fds = new FileDataSource(filename);
    return new DataHandler(fds);
  }
}