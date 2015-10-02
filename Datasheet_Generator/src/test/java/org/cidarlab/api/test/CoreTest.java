/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cidarlab.api.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.cidarlab.datasheet.LatexCreator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.cidarlab.javaapi.Owl;

/**
 *
 * @author zach_chapasko
 */
public class CoreTest {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    // Helper method for getting filepath independent of system
    protected String getFilepath() {
        String filepath;
        filepath = Owl.class.getClassLoader().getResource(".").getPath();
        filepath = filepath.substring(0,filepath.indexOf("/target/"));
        //System.out.println("\nFILEPATH: " + filepath + "\n");
        return filepath;
    }
    
    // Helper method for writing File objects to local filesystem
    protected void writePDFs(ArrayList<File> PDFs) {
        String path = getFilepath() + "/src/main/resources/OwlTestFiles/PDFs/";
        byte[] bytes;
        FileOutputStream out;
        
        for (File temp : PDFs){
            try {
                bytes = FileUtils.readFileToByteArray(temp);
                out = new FileOutputStream(new File(path + temp.getName()));
                IOUtils.write(bytes, out);
            } catch (IOException ex) {
                Logger.getLogger(CoreTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Helper method to obtain strings from a text file
    protected String reader(File latexMap) throws FileNotFoundException, IOException{
        String everything = "";
        BufferedReader br = new BufferedReader(new FileReader(latexMap));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }
        
        return everything;
    }
    
    @Test
    public void fileAPITest() throws IOException, InterruptedException {
        String path = getFilepath() + "/src/main/resources/OwlTestFiles/";
        
        // Text files
        ArrayList<File> textFilesJSON = new ArrayList<>();
        
        textFilesJSON.add(new File(path + "BBa_B0015.txt"));
        textFilesJSON.add(new File(path + "BBa_J23100.txt"));
        textFilesJSON.add(new File(path + "BBa_K1114107.txt"));
        textFilesJSON.add(new File(path + "BBa_K1114211.txt"));
        textFilesJSON.add(new File(path + "BBa_K1114400.txt"));
        textFilesJSON.add(new File(path + "BBa_K1179002.txt"));
        textFilesJSON.add(new File(path + "BBa_K678001.txt"));
        textFilesJSON.add(new File(path + "BBa_K783067.txt"));
        textFilesJSON.add(new File(path + "BBa_pSB1K3.txt"));
        textFilesJSON.add(new File(path + "CoxRG_AF.txt"));
        
        // Images        
        ArrayList<File> images = new ArrayList<>();
        
        images.add(new File(path + "BBa_B0015_pigeon.png"));
        images.add(new File(path + "BBa_B0015_plasmid_map.png"));
        
        images.add(new File(path + "BBa_J23100_pigeon.png"));
        images.add(new File(path + "BBa_J23100_plasmid_map.png"));
        
        images.add(new File(path + "BBa_K1114107_pigeon.png"));
        images.add(new File(path + "BBa_K1114107_plasmid_map.png"));
        
        images.add(new File(path + "BBa_K1114211_pigeon.png"));
        images.add(new File(path + "BBa_K1114211_plasmid_map.png"));
        
        images.add(new File(path + "BBa_K1114400_pigeon.png"));
        images.add(new File(path + "BBa_K1114400_plasmid_map.png"));
        
        images.add(new File(path + "BBa_K1179002_pigeon.png"));
        images.add(new File(path + "BBa_K1179002_plasmid_map.png"));
        images.add(new File(path + "BBa_K1179002_transfer_curve.png"));
        
        images.add(new File(path + "BBa_K678001_growth_curve.png"));
        images.add(new File(path + "BBa_K678001_pigeon.png"));
        images.add(new File(path + "BBa_K678001_plasmid_map.png"));
        images.add(new File(path + "BBa_K678001_transfer_curve.png"));
        
        images.add(new File(path + "BBa_K783067_pigeon.png"));
        images.add(new File(path + "BBa_K783067_transfer_curve.png"));
        
        images.add(new File(path + "BBa_pSB1K3_pigeon.png"));
        images.add(new File(path + "BBa_pSB1K3_plasmid_map.png"));
        
        images.add(new File(path + "CoxRG_AF_pigeon.png"));

        // API call
        ArrayList<File> PDFs = Owl.filesToPDF(textFilesJSON, images);
        
        // Write resulting files to local system
        writePDFs(PDFs);
    }
    
    @Test
    public void stringAPITest() throws IOException, InterruptedException {
        String path = getFilepath() + "/src/main/resources/OwlTestFiles/";
        
        // Text files
        ArrayList<File> textFilesJSON = new ArrayList<>();
        
        textFilesJSON.add(new File(path + "BBa_B0015.txt"));
        textFilesJSON.add(new File(path + "BBa_J23100.txt"));
        textFilesJSON.add(new File(path + "BBa_K1114107.txt"));
        textFilesJSON.add(new File(path + "BBa_K1114211.txt"));
        textFilesJSON.add(new File(path + "BBa_K1114400.txt"));
        textFilesJSON.add(new File(path + "BBa_K1179002.txt"));
        textFilesJSON.add(new File(path + "BBa_K678001.txt"));
        textFilesJSON.add(new File(path + "BBa_K783067.txt"));
        textFilesJSON.add(new File(path + "BBa_pSB1K3.txt"));
        textFilesJSON.add(new File(path + "CoxRG_AF.txt"));
        
        // Images        
        ArrayList<File> images = new ArrayList<>();
        
        images.add(new File(path + "BBa_B0015_pigeon.png"));
        images.add(new File(path + "BBa_B0015_plasmid_map.png"));
        
        images.add(new File(path + "BBa_J23100_pigeon.png"));
        images.add(new File(path + "BBa_J23100_plasmid_map.png"));
        
        images.add(new File(path + "BBa_K1114107_pigeon.png"));
        images.add(new File(path + "BBa_K1114107_plasmid_map.png"));
        
        images.add(new File(path + "BBa_K1114211_pigeon.png"));
        images.add(new File(path + "BBa_K1114211_plasmid_map.png"));
        
        images.add(new File(path + "BBa_K1114400_pigeon.png"));
        images.add(new File(path + "BBa_K1114400_plasmid_map.png"));
        
        images.add(new File(path + "BBa_K1179002_pigeon.png"));
        images.add(new File(path + "BBa_K1179002_plasmid_map.png"));
        images.add(new File(path + "BBa_K1179002_transfer_curve.png"));
        
        images.add(new File(path + "BBa_K678001_growth_curve.png"));
        images.add(new File(path + "BBa_K678001_pigeon.png"));
        images.add(new File(path + "BBa_K678001_plasmid_map.png"));
        images.add(new File(path + "BBa_K678001_transfer_curve.png"));
        
        images.add(new File(path + "BBa_K783067_pigeon.png"));
        images.add(new File(path + "BBa_K783067_transfer_curve.png"));
        
        images.add(new File(path + "BBa_pSB1K3_pigeon.png"));
        images.add(new File(path + "BBa_pSB1K3_plasmid_map.png"));
        
        images.add(new File(path + "CoxRG_AF_pigeon.png"));
        
        // Convert text files to strings
        ArrayList<String> latexMapStrings = new ArrayList<>();
        for(File tempFile : textFilesJSON){
            latexMapStrings.add(reader(tempFile));
        }

        // API call
        ArrayList<File> PDFs = Owl.jsonStringsToPDF(latexMapStrings, images,path);
        
        // Write resulting files to local system
        writePDFs(PDFs);
    }
}