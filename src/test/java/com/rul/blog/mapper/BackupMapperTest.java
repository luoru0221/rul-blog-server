package com.rul.blog.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BackupMapperTest {

    @Autowired
    private BackupMapper backupMapper;
    @Test
    void findBackupTest(){
        System.out.println(backupMapper.findBackup(14));
    }
}
