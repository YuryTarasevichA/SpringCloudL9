package com.example.api;

import com.example.BookProvider;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("api/issue")
public class IssueController {

    private final Faker faker;
    private final List<Issue> issues;

    public IssueController(BookProvider bookProvider) {
        this.faker = new Faker();
        final List<Issue> issues = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Issue issue = new Issue();
            issue.setId(UUID.randomUUID());

            Date between = faker.date().between(startOfYear(), endOfYear());
            issue.setIssuedAt(between.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            issue.setBookId(UUID.randomUUID());
            issue.setReaderId(UUID.randomUUID());

            issues.add(issue);

        }
        this.issues = List.copyOf(issues);

    }

    private Date startOfYear() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2024);
        instance.set(Calendar.MONTH, Calendar.JANUARY);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        return instance.getTime();
    }

    private Date endOfYear() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2024);
        instance.set(Calendar.MONTH, Calendar.DECEMBER);
        instance.set(Calendar.DAY_OF_MONTH, 31);
        return instance.getTime();
    }


    @GetMapping
    public List<Issue> getAll() {
        return issues;
    }


}
