package com.rul.blog.mapper;

import com.rul.blog.pojo.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TagMapperTest {
    @Autowired
    private TagMapper tagMapper;

    @Test
    void addTagsByNameTest() {
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("计算机网络"));
        tags.add(new Tag("Spring"));

        tagMapper.addTagsByName(tags);
        System.out.println(tags);
    }

    @Test
    void findTagsByNameTest(){
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("计算机网络"));
        tags.add(new Tag("MyBatis"));

        List<Tag> tagsByName = tagMapper.findTagsByName(tags);
        System.out.println(tagsByName);
    }

    @Test
    void addArticleIdsTest(){
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1,"Java"));
        tags.add(new Tag(6,"Spring"));

        tagMapper.addArticleTags(1,tags);
    }

}
