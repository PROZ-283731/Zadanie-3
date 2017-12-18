package atj.ws;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Implementation of Attachment support as webservice using SOAP
 * @author Michał Dziewulski (M.Dziewulski@stud.elka.pw.edu.pl)
 * @version 1.0
 */
@WebService()
public class Attachment {
  
  /**
   * Service method used to upload file to server
   * @param filename name of file as String
   * @param file file handler as DataHandler (byte[] in client)
   * @throws IOException standard exception that may occur during file I/O operations
   */
  @WebMethod
  public void uploadFile(String filename, DataHandler file) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    file.writeTo(output);
    byte data[] = output.toByteArray();
    FileOutputStream out = new FileOutputStream(filename);
    out.write(data);
    out.close();
  }
  
  /**
   * Service method used to download file from server
   * Warning! Method doesn't check if file exist
   * @param filename name of a file available on server
   * @return DataHandler object of that file
   */
  @WebMethod
  public DataHandler downloadFile(String filename) {
    DataSource fds = new FileDataSource(filename);
    return new DataHandler(fds);
  }
}