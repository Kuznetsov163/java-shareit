package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RequestServiceImpl {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final UserRepository userRepository;

    @Transactional
    public ItemRequestDto create(RequestDto requestDto, Long userId) {
        checkUser(userId);
        Request request = requestMapper.toRequest(requestDto);
        request.setUserId(userId);
        Request newRequest = requestRepository.save(request);
        return requestMapper.request4ResponseDto(newRequest);
    }

    public List<ItemRequestDto> findAll(Long userId) {
        checkUser(userId);
        Sort sort = Sort.by("created").descending();
        List<Request> requests = requestRepository.findAllByUserId(userId, sort);
        return requestMapper.toListRequest4ResponseDto(requests);
    }

    public List<ItemRequestDto> findAll() {
        return requestMapper.toListRequest4ResponseDto(requestRepository.findAllByOrderByCreatedDesc());
    }

    public ItemRequestDto findById(long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Request not found with id " + requestId));
        return requestMapper.request4ResponseDto(request);
    }

    private void checkUser(long userId) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id=" + userId));
    }
}
