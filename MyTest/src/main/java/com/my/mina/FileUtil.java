package com.my.mina;

import com.my.mina.bean.FileTask;
import com.my.mina.bean.FilePartMsg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtil {

    public static FileTask getFileTask(String filePath) {
        File file = new File(filePath);
        FileTask task = new FileTask();
        if (file.exists()) {
            task.fileName = file.getName();
            task.filePath = filePath;
            task.length = file.length();
            task.fileSegmentSize = MinaConstans.FILE_SEGMENT_SIZE;
            task.partNum = (int) (task.length / task.fileSegmentSize);
            task.lastSegmentSize = (int) (task.length % (task.partNum * task.fileSegmentSize));
        }
        return task;
    }

    /***
     * 读取指定位置的文件分段
     * @param fileTask
     * @param pardIdLocal
     */
    public static byte[] randowFileRead(FileTask fileTask, int pardIdLocal) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(
                    fileTask.filePath, "rw");
            int buffSize = 0;
            if (pardIdLocal < fileTask.partNum) {
                buffSize = fileTask.fileSegmentSize;
            } else {
                buffSize = fileTask.lastSegmentSize;
            }
            byte[] buffer = new byte[buffSize];
            int availableSize;
            randomAccessFile.seek(pardIdLocal * fileTask.fileSegmentSize);
            availableSize = randomAccessFile.read(buffer);
            randomAccessFile.close();
            return buffer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 文件分段写入指定位置
     * @param fileTask
     * @param filePart
     */
    public static void randowFileWrite(FileTask fileTask, FilePartMsg filePart) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(
                    fileTask.filePath, "rw");
            long beginIndex = fileTask.fileSegmentSize * filePart.getPartId();
            randomAccessFile.seek(beginIndex);
            // System.out.println("file length = "+randomAccessFile.length()+" , beginIndex = "+beginIndex);
            randomAccessFile.write(filePart.getData());
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
