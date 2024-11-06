package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.RequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestMapper {
    private final ItemMapper itemMapper;

    public Request toRequest(RequestDto requestDto) {
        Request request = new Request();
        request.setDescription(requestDto.getDescription());
        request.setCreated(requestDto.getCreated());
        return request;
    }

    public RequestDto toRequestDto(Request request) {
        return new RequestDto(request.getDescription(), request.getCreated());
    }

    public ItemRequestDto request4ResponseDto(Request request) {
        ItemRequestDto requestForResponseDto = new ItemRequestDto();
        requestForResponseDto.setId(request.getId());
        requestForResponseDto.setDescription(request.getDescription());
        requestForResponseDto.setCreated(request.getCreated());
        requestForResponseDto.setItems(itemMapper.toItemDtoList(request.getItems()));
        return requestForResponseDto;
    }

    public List<ItemRequestDto> toListRequest4ResponseDto(List<Request> requests) {
        return requests.stream()
                .map(request -> request4ResponseDto(request))
                .collect(Collectors.toList());
    }
}
