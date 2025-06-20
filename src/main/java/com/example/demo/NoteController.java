package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepo;

    @PostMapping
    public ResponseEntity<Notes> createNote(@RequestBody Notes note) {
        Notes savedNote = noteRepo.save(note);
        return ResponseEntity.ok(savedNote);
    }

    @GetMapping
    public List<Notes> getAllNotes() {
        return noteRepo.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notes> updateNote(@PathVariable Long id, @RequestBody Notes updatedNote) {
        return noteRepo.findById(id)
                .map(note -> {
                    note.setTitle(updatedNote.getTitle());
                    note.setContent(updatedNote.getContent());

                    Notes saved = noteRepo.save(note);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        if (noteRepo.existsById(id)) {
            noteRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
