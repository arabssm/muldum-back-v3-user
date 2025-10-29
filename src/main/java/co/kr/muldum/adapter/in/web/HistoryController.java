package co.kr.muldum.adapter.in.web;

import co.kr.muldum.adapter.in.web.dto.HistoryResponse;
import co.kr.muldum.application.port.in.GetHistoryUseCase;
import co.kr.muldum.application.port.in.response.HistoryListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Histories", description = "전공동아리 역사 관리 API")
@RestController
@RequestMapping("/ara/history")
public class HistoryController {

    private final GetHistoryUseCase getHistoryUseCase;

    public HistoryController(GetHistoryUseCase getHistoryUseCase) {
        this.getHistoryUseCase = getHistoryUseCase;
    }

    @Operation(summary = "전공동아리 역사 목록 조회", description = "특정 세대의 전공동아리 역사를 조회합니다. generation 파라미터가 없으면 최신 세대를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = HistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (0 이하의 세대 번호 또는 잘못된 파라미터 타입)",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "해당 세대의 전공동아리 정보를 찾을 수 없음",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<HistoryResponse>> getHistoryList(
            @Parameter(description = "조회할 세대 번호 (양의 정수, 미입력 시 최신 세대)")
            @RequestParam(required = false) Integer generation) {
        HistoryListResponse historyListResponse = getHistoryUseCase.getHistoryList(generation);

        List<HistoryResponse> responses = historyListResponse.getHistories().stream()
                .map(HistoryResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}