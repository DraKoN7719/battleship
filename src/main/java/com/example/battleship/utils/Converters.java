package com.example.battleship.utils;

import com.example.battleship.model.Placement;
import com.example.battleship.model.dto.PlacementDTO;

import java.util.List;

public class Converters {

    public static PlacementDTO convertToPlacementDTO(Placement placement) {
        return new PlacementDTO(placement.getPlacementName(),
                                placement.getUserId(),
                                PlacementUtils.placementToArray(placement.getPlacement()));
    }

    public static Placement convertToPlacement(PlacementDTO placementDTO) {
        return new Placement(placementDTO.getPlacementName(),
                             placementDTO.getUserId(),
                             PlacementUtils.placementToString(placementDTO.getPlacement()));
    }

    public static List<PlacementDTO> convertToPlacementDTOs(List<Placement> placements){
        return placements.stream().map(Converters::convertToPlacementDTO).toList();
    }
}
