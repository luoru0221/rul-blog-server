package com.rul.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileIOUtil.class);

    @Value("${backup.path}")
    private String backupPath;

    /**
     * 将被删除的文章备份到本地磁盘
     *
     * @param userId      用户ID
     * @param articleId   文章ID
     * @param fileContent 文件内容
     * @throws IOException IO异常
     */
    public void output(Integer userId, Integer articleId, String fileContent) throws IOException {
        //备份文件夹,
        File backupFolder = new File(backupPath + userId);
        if (!backupFolder.exists()) {
            backupFolder.mkdirs();
        }
        //备份文件
        File backupFile = new File(backupFolder, articleId + "");

        //创建文件缓冲输出流
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(backupFile));
        bufferedWriter.write(fileContent);

        bufferedWriter.flush();
        bufferedWriter.close();

        LOGGER.info("write the file-{} successful",backupFile.getPath());
    }

    /**
     * 从本地磁盘读取备份文件
     *
     * @param userId    用户ID
     * @param articleId 文章ID
     * @return 文件内容
     * @throws IOException IO异常
     */
    public String input(Integer userId, Integer articleId) throws IOException {
        //备份文件夹,
        File backupFolder = new File(backupPath + userId);
        if (!backupFolder.exists()) {
            backupFolder.mkdirs();
        }
        //备份文件
        File backupFile = new File(backupFolder, articleId + "");

        //创建文件缓冲输入流
        String line;
        StringBuilder fileContent = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(backupFile));
        while ((line = bufferedReader.readLine()) != null) {
            fileContent.append(line).append("\n");
        }

        bufferedReader.close();
        LOGGER.info("read the file-{} successful",backupFile.getPath());
        return fileContent.toString();
    }

    /**
     * 删除本地备份
     *
     * @param userId    用户ID
     * @param articleId 文章ID
     */
    public void delete(Integer userId, Integer articleId) {
        //备份文件夹,
        File backupFolder = new File(backupPath + userId);
        if (!backupFolder.exists()) {
            return;
        }

        //备份文件
        File backupFile = new File(backupFolder, articleId + "");
        backupFile.delete();
        LOGGER.info("delete the file-{}",backupFile.getPath());
    }
}
