package com.example.battleship.utils;

import com.example.battleship.model.HistoryGame;
import com.example.battleship.model.Placement;
import com.example.battleship.model.SavedGame;
import com.example.battleship.model.dto.HistoryGameDto;
import com.example.battleship.model.dto.PlacementDto;
import com.example.battleship.model.dto.SavedGameDto;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.battleship.utils.PlacementUtils.maskField;
import static com.example.battleship.utils.PlacementUtils.maskUserField;
import static com.example.battleship.utils.PlacementUtils.placementToArray;
import static com.example.battleship.utils.PlacementUtils.placementToString;

public class Converters {

    public static PlacementDto convertToPlacementDTO(Placement placement) {
        return new PlacementDto(placement.getPlacementName(),
                                placement.getUserId(),
                                placementToArray(placement.getPlacement()));
    }

    public static Placement convertToPlacement(PlacementDto placementDTO) {
        return new Placement(placementDTO.getPlacementName(),
                             placementDTO.getUserId(),
                             placementToString(placementDTO.getPlacement()));
    }

    public static List<PlacementDto> convertToPlacementDtos(List<Placement> placements) {
        return placements.stream().map(Converters::convertToPlacementDTO).toList();
    }

    public static SavedGameDto convertToSavedGameDto(SavedGame savedGame) {
        return new SavedGameDto(savedGame.getId(),
                                savedGame.getNameGame(),
                                savedGame.getUserId(),
                                savedGame.getBotId(),
                                placementToArray(savedGame.getUserField()),
                                maskField(placementToArray(savedGame.getBotField())),
                                savedGame.getTurn());
    }

    public static SavedGame convertToSavedGame(SavedGameDto savedGameDto) {
        return new SavedGame(savedGameDto.getId(),
                             savedGameDto.getGameName(),
                             savedGameDto.getUserId(),
                             savedGameDto.getBotId(),
                             placementToString(savedGameDto.getUserField()),
                             placementToString(savedGameDto.getBotField()),
                             savedGameDto.getTurn());
    }

    public static List<SavedGameDto> convertToSavedGameDtos(List<SavedGame> savedGames) {
        return savedGames.stream().map(Converters::convertToSavedGameDto).toList();
    }

    public static HistoryGame convertToHistoryGame(HistoryGameDto historyGameDto) {
        return new HistoryGame(UUID.fromString(historyGameDto.getId()),
                               historyGameDto.getPlayer1(),
                               historyGameDto.getPlayer2(),
                               historyGameDto.getResult(),
                               Instant.now());
    }
}
