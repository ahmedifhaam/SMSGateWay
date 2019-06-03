package com.xelvias.imsms.utils;

import com.opencsv.CSVWriter;
import com.xelvias.imsms.Models.Phonenumber;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public interface CSVwritable{
        String[] toStringArray();
    }

    public static File getFile(MultipartFile multipartFile) throws Exception{
        File convertedFile = new File(multipartFile.getOriginalFilename());
        convertedFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convertedFile;
    }

    public static String WritetoFile(List<? extends CSVwritable> objects,String prefix) throws IOException{
        String filename = prefix +"-"+ DateUtil.getCurrentDateTime()+".csv";
        File file = new File(filename);


            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            List<String[]> data = new ArrayList<String[]>();

            for(CSVwritable csVwritable:objects){
                data.add(csVwritable.toStringArray());
            }

//            data.add(new String[] { "Name", "Class", "Marks" });
//            data.add(new String[] { "Aman", "10", "620" });
//            data.add(new String[] { "Suraj", "10", "630" });
            writer.writeAll(data);

            // closing writer connection
            writer.close();

        return filename;
    }

    public static String sampleFile() throws IOException{
        List<Phonenumber> objects = new ArrayList<>();
        objects.add(new Phonenumber());
        objects.add(new Phonenumber());
        objects.add(new Phonenumber());
        objects.add(new Phonenumber());
        objects.add(new Phonenumber());
        String filename = "sample"+".csv";
        File file = new File(filename);


            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            List<String[]> data = new ArrayList<String[]>();

            for(CSVwritable csVwritable:objects){
                data.add(csVwritable.toStringArray());
            }

//            data.add(new String[] { "Name", "Class", "Marks" });
//            data.add(new String[] { "Aman", "10", "620" });
//            data.add(new String[] { "Suraj", "10", "630" });
            writer.writeAll(data);

            // closing writer connection
            writer.close();

        return filename;
    }



}
