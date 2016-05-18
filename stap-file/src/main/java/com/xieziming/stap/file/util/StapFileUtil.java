package com.xieziming.stap.file.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

/**
 * Created by Suny on 5/18/16.
 */
public class StapFileUtil {
    public static byte[] blobToBytes(Blob blob) {
        InputStream is = null;
        byte[] b = null;
        try {
            is = blob.getBinaryStream();
            b = new byte[(int) blob.length()];
            is.read(b);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }
}
