package com.example.demo.bootstrap;

import com.example.demo.model.Category;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Category> categories = new ArrayList<>();

    @PostConstruct
    public void init() {
        categories.add(new Category("Books", "Books category"));
        categories.add(new Category("Movies", "Movies category"));
    }

}
