package com.io.test;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestIO {
    @Test
    public void ioTest() throws IOException {
        String path="D:\\testIOFile.txt";
        File file = new File(path);
        OutputStreamWriter out = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file)));
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println("123456999999999999999");
        printWriter.println("15359789");
        printWriter.close();
    }

    @Test
    public void testMap() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();

        stringStringHashMap.put(null, null);
        Set<Map.Entry<String, String>> entries = stringStringHashMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("entry:"+entry);
        }
        if(stringStringHashMap==null){
            System.out.println("11");
        }else {
            System.out.println("22");
        }
    }
}
