package ftp;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import interfaz.CrearBlog;
import util.Utils;
 
/**
 * Executes the file upload in a background thread and updates progress to
 * listeners that implement the java.beans.PropertyChangeListener interface.
 *
 */
public class UploadTask extends SwingWorker<Void, Void> {
    private static final int BUFFER_SIZE = 4096;
     
    private String host;
    private int port;
    private String username;
    private String password;
    private ArrayList<String> rutasParaSubir;
    private ArrayList<File> imagenesParaSubir;
    private long fileSize;
     
    public UploadTask(String host, int port, String username, String password,
            ArrayList<String> rutasParaSubir, ArrayList<File> imagenesParaSubir, long tamanoArchivos) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.rutasParaSubir = rutasParaSubir;
        this.imagenesParaSubir = imagenesParaSubir;
        this.fileSize = tamanoArchivos;
    }
 
    /**
     * Executed in background thread
     */
    @Override
    protected Void doInBackground() throws Exception {
        FTPUtility util = new FTPUtility(host, port, username, password);
        
        try {
        	
            for(int i = 0; i<imagenesParaSubir.size();i++) {
            	util.connect();
            	util.uploadFile(imagenesParaSubir.get(i), rutasParaSubir.get(i));
            	
	            
	             
	            FileInputStream inputStream = new FileInputStream(imagenesParaSubir.get(i));
	            byte[] buffer = new byte[BUFFER_SIZE];
	            int bytesRead = -1;
	            long totalBytesRead = 0;
	            int percentCompleted = 0;
	            
	 
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                util.writeFileBytes(buffer, 0, bytesRead);
	                totalBytesRead += bytesRead;
	                percentCompleted = (int) (totalBytesRead * 100 / fileSize);
	                setProgress(percentCompleted);
	            }
	 
	            inputStream.close();
	            imagenesParaSubir.get(i).delete();
	            util.finish();
	            
            }
            setProgress(100);
        } catch (FTPException ex) {
        	Utils.escribirLog("Error en la subida del fichero: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en la subida del fichero",
                    "Error", JOptionPane.ERROR_MESSAGE); 
            
            ex.printStackTrace();
            
            cancel(true);          
        } finally {
            util.disconnect();
            
        }
         
        return null;
    }
 
    /**
     * Executed in Swing's event dispatching thread
     */
    @Override
    protected void done() {
        if (!isCancelled()) {
        	CrearBlog.devolver();
        	Utils.escribirLog("Despues de la ejecucion: " + !isCancelled());
        	
        	
        }
    }  
}