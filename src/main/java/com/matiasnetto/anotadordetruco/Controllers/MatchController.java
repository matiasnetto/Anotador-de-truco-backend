package com.matiasnetto.anotadordetruco.Controllers;

import com.matiasnetto.anotadordetruco.Dto.Requests.CreateNewMatchDTORequest;
import com.matiasnetto.anotadordetruco.Dto.Responses.CreateNewMatchDTOResponse;
import com.matiasnetto.anotadordetruco.Models.MatchModel;
import com.matiasnetto.anotadordetruco.Services.TrucoMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("match")
public class MatchController {


    @Autowired
    private TrucoMatchService trucoMatchService;

    @PostMapping()
    public ResponseEntity<CreateNewMatchDTOResponse> createNewMatch(@RequestBody CreateNewMatchDTORequest body) {
        MatchModel match = trucoMatchService.createNewMatch(body.matchOwner);
        var responseDTO = new CreateNewMatchDTOResponse(match.getRoomId(), match.getAdminKey().toString());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<MatchModel> getMatchByRoomId(@PathVariable(name = "roomId") String roomId) {
        return ResponseEntity.ok(trucoMatchService.getMatchByRoomId(roomId));
    }
}
