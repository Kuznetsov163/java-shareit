package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.logging.Logging;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import static ru.practicum.shareit.constant.Request.HEADER4USER_ID;

import java.util.List;

@Logging
@RestController
@RequestMapping(path = "/requests")
@Slf4j
@RequiredArgsConstructor

public class RequestController {

    private final RequestServiceImpl requestService;

    @PostMapping
    public ItemRequestDto createRequest(@RequestHeader(HEADER4USER_ID) Long userId,
                                             @RequestBody RequestDto requestDto) {
        return requestService.create(requestDto, userId);
    }

    @GetMapping
    public List<ItemRequestDto> findAll4User(@RequestHeader(HEADER4USER_ID) Long userId) {
        return requestService.findAll(userId);
    }

    @GetMapping("/all")
    public List<ItemRequestDto> findAll() {
        return requestService.findAll();

    }

    @GetMapping("/{requestId}")
    public ItemRequestDto findById(@PathVariable("requestId") long requestId) {
        return requestService.findById(requestId);
    }


}
