package com.bugtrack.service;

import com.bugtrack.model.Bug;
import com.bugtrack.repository.BugRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BugService {

    private final BugRepository bugRepository;

    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    public Bug getBugById(Long id) {
        return bugRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bug not found"));
    }

    public Bug createBug(Bug bug) {
        validate(bug);
        bug.setId(null);
        bug.setCreatedAt(null);
        return bugRepository.save(bug);
    }

    public Bug updateBug(Long id, Bug updatedBug) {
        validate(updatedBug);
        Bug existingBug = getBugById(id);
        existingBug.setTitle(updatedBug.getTitle());
        existingBug.setDescription(updatedBug.getDescription());
        existingBug.setStatus(updatedBug.getStatus());
        return bugRepository.save(existingBug);
    }

    public void deleteBug(Long id) {
        Bug bug = getBugById(id);
        bugRepository.delete(bug);
    }

    private void validate(Bug bug) {
        if (bug == null || bug.getTitle() == null
                || bug.getTitle().isBlank() || bug.getTitle().length() > 200) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Title is required and must be at most 200 characters");
        }
        if (bug.getStatus() == null || bug.getStatus().isBlank() || bug.getStatus().length() > 30) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Status is required and must be at most 30 characters");
        }
    }
}
