/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ftp;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import com.mysql.cj.util.Util;

import util.Utils;
/**
 * A utility class that provides functionality for uploading files to a FTP
 * server.
 *
 * @author www.codejava.net
 *
 */
public class FTPUtility {
 
    private String host;
    private int port;
    private String username;
    private String password;
    String protocol = "TLS"; 
    private FTPSClient ftpClient = new FTPSClient(protocol);
    private int replyCode;
    
    private OutputStream outputStream;
     
    public FTPUtility(String host, int port, String user, String pass) {
        this.host = host;
        this.port = port;
        this.username = user;
        this.password = pass;
    }
 
    /**
     * Connect and login to the server.
     *
     * @throws FTPException
     */
    public void connect() throws FTPException {
        try {
            ftpClient.connect(host, port);
            replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
            	Utils.escribirLog("FTP serve refused connection.");
                throw new FTPException("FTP serve refused connection.");
                
            }
            
 
            boolean logged = ftpClient.login(username, password);
            if (!logged) {
                // failed to login
                ftpClient.disconnect();
                Utils.escribirLog("Could not login to the server.");
                throw new FTPException("Could not login to the server.");
            }
 
            ftpClient.enterLocalPassiveMode();
 
        } catch (IOException ex) {
        	Utils.escribirLog("I/O error: " + ex.getMessage());
            throw new FTPException("I/O error: " + ex.getMessage());
        }
    }
 
    /**
     * Start uploading a file to the server
     * @param uploadFile the file to be uploaded
     * @param destDir destination directory on the server
     * where the file is stored
     * @throws FTPException if client-server communication error occurred
     */
    public void uploadFile(File uploadFile, String destDir) throws FTPException {
        try {
            boolean success = ftpClient.changeWorkingDirectory(destDir);
            if (!success) {
            	Utils.escribirLog("Could not change working directory to "
                        + destDir + ". The directory may not exist.");
                throw new FTPException("Could not change working directory to "
                        + destDir + ". The directory may not exist.");
            }
             
            success = ftpClient.setFileType(FTP.BINARY_FILE_TYPE);         
            if (!success) {
            	Utils.escribirLog("Could not set binary file type.");
                throw new FTPException("Could not set binary file type.");
            }
             
            outputStream = ftpClient.storeFileStream(uploadFile.getName());
             
        } catch (IOException ex) {
        	Utils.escribirLog("Error uploading file: " + ex.getMessage());
            throw new FTPException("Error uploading file: " + ex.getMessage());
        }
    }
 
    /**
     * Write an array of bytes to the output stream.
     */
    public void writeFileBytes(byte[] bytes, int offset, int length)
            throws IOException {
        outputStream.write(bytes, offset, length);
    }
     
    /**
     * Complete the upload operation.
     */
    public void finish() throws IOException {
        outputStream.close();
        ftpClient.completePendingCommand();
    }
     
    /**
     * Log out and disconnect from the server
     */
    public void disconnect() throws FTPException {
        if (ftpClient.isConnected()) {
            try {
                if (!ftpClient.logout()) {
                	Utils.escribirLog("Could not log out from the server");
                    throw new FTPException("Could not log out from the server");
                }
                ftpClient.disconnect();
            } catch (IOException ex) {
            	Utils.escribirLog("Error disconnect from the server: "
                        + ex.getMessage());
                throw new FTPException("Error disconnect from the server: "
                        + ex.getMessage());
            }
        }
    }
}
