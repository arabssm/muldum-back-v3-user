package co.kr.muldum.adapter.in.web;

import co.kr.muldum.adapter.in.web.dto.LogContentsDto;
import co.kr.muldum.adapter.in.web.dto.UserLogResponseDto;
import co.kr.muldum.application.port.in.GetUserLogsUseCase;
import co.kr.muldum.application.port.in.command.GetUserLogsCommand;
import co.kr.muldum.domain.model.UserLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth/sup")
public class SuperAdminLogController {

    private final GetUserLogsUseCase getUserLogsUseCase;

    public SuperAdminLogController(GetUserLogsUseCase getUserLogsUseCase) {
        this.getUserLogsUseCase = getUserLogsUseCase;
    }

    @GetMapping("/user/logs")
    public ResponseEntity<List<UserLogResponseDto>> getUserLogs(
            @RequestParam(required = false) String type) {
        GetUserLogsCommand command = GetUserLogsCommand.of(type);
        List<UserLog> logs = getUserLogsUseCase.getLogs(command);

        List<UserLogResponseDto> response = logs.stream()
                .map(log -> UserLogResponseDto.of(
                        log.getStatus(),
                        log.getLoggedBy().toString(),
                        log.getLoggedAt(),
                        LogContentsDto.of(log.getTitle(), log.getContent())
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}