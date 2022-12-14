package com.example.battleship.web;

import com.example.battleship.model.dto.PlacementDto;
import com.example.battleship.service.PlacementService;
import com.example.battleship.utils.Converters;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlacementController {
    private final PlacementService placementService;

    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    @GetMapping("/api/placement/random")
    public ResponseEntity<int[][]> placeRandom() {
        return ResponseEntity.ok(placementService.placeRandom());
    }

    @GetMapping("/api/placement/shores")
    public ResponseEntity<int[][]> placeShores() {
        return ResponseEntity.ok(placementService.placeShores());
    }

    @GetMapping("/api/placement/halfField")
    public ResponseEntity<int[][]> placeHalfField() {
        return ResponseEntity.ok(placementService.placeHalfField());
    }

    @GetMapping("/api/placement/{userId}")
    public ResponseEntity<List<PlacementDto>> getPlacements(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(Converters.convertToPlacementDtos(placementService.getPlacementsByUserId(userId)));
    }

    @PostMapping("/api/placement/{userId}")
    public ResponseEntity<PlacementDto> getPlacement(
        @PathVariable("userId") Long userId,
        @RequestBody String placementName
    ) {
        return ResponseEntity.ok(Converters.convertToPlacementDTO(placementService.getPlacement(userId, placementName)));
    }

    @PostMapping("/api/placement")
    public ResponseEntity<Boolean> savePlacement(
        @RequestParam(value = "isOverwrite", required = false, defaultValue = "false") boolean isOverwrite,
        @RequestBody PlacementDto placementDTO
    ) {
        return ResponseEntity.ok(placementService.savePlacement(Converters.convertToPlacement(placementDTO), isOverwrite));
    }

    @DeleteMapping("/api/placement/{userId}")
    public ResponseEntity<Void> deletePlacement(
        @PathVariable("userId") Long userId,
        @RequestParam("placementName") String placementName
    ) {
        placementService.deletePlacement(userId, placementName);
        return ResponseEntity.noContent().build();
    }

}
