package com.example;

import com.google.gson.internal.Primitives;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Serialize Tools
 */
public class SerializeTools {

    private SerializeTools() {
        throw new AssertionError();
    }

    public static <T> T getObj(String fileName, Class<T> classOfT) {
        if (fileName == null || fileName.length() == 0 || null == classOfT) {
            return null;
        }
        File file = new File(fileName);
        Object obj;
        if (file.exists()) {
            obj = deserialization(file.getAbsolutePath());
            T result = Primitives.wrap(classOfT).cast(obj);
            if (null == result) {
                //删除脏数据
                file.delete();
            }
            return result;
        } else {
            return null;
        }
    }

    public static boolean cacheObj(String fileNameKey, Object object) {
        if (null == object || fileNameKey==null || fileNameKey.length() == 0) {
            return false;
        }
        try {
            File file = new File(fileNameKey);
            serialization(file.getAbsolutePath(), object);
            return true;
        } catch (Exception e) {
            System.out.println("serialization:cacheObjError:" + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Deserialization object from file.
     *
     * @param filePath file path
     * @return de-serialized object
     * @throws RuntimeException if an error occurs
     */
    public static Object deserialization(String filePath) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filePath));
            Object o = in.readObject();
            in.close();
            return o;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ClassNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            IOUtils.close(in);
        }
    }

    /**
     * Serialize object to file.
     *
     * @param filePath file path
     * @param obj      object
     * @throws RuntimeException if an error occurs
     */
    public static void serialization(String filePath, Object obj) {
        ObjectOutputStream out = null;
        try {

            checkFileAndCreate(filePath);

            out = new ObjectOutputStream(new FileOutputStream(filePath));
            out.writeObject(obj);
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            System.out.println("err=" + e);
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            IOUtils.close(out);
        }
    }

    //判断文件是否存在
    public static void checkFileAndCreate(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                f.createNewFile();
            }

        } catch (Exception e) {
        }

        return;
    }

    /**
     * 删除文件
     *
     * @param filePath
     */
    public static void deletePath(String filePath) {
        File file = new File(filePath);
        if (file.isFile()) {
           file.delete();
        }
    }

}
