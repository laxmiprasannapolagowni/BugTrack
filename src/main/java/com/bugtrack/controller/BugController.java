package com.bugtrack.controller;

import com.bugtrack.model.Bug;
import com.bugtrack.service.BugService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/bugs")
public class BugController {

    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
    }

    @GetMapping("/{id}")
    public Bug getBugById(@PathVariable Long id) {
        return bugService.getBugById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bug createBug(@RequestBody Bug bug) {
        return bugService.createBug(bug);
    }

    @PutMapping("/{id}")
    public Bug updateBug(@PathVariable Long id, @RequestBody Bug bug) {
        return bugService.updateBug(id, bug);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
    }
}
