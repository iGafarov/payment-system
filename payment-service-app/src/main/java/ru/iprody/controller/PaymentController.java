package ru.iprody.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.iprody.mapper.PaymentResponseMapper;
import ru.iprody.model.PaymentDto;
import ru.iprody.model.request.PaymentFilterRequest;
import ru.iprody.model.response.PaymentResponse;
import ru.iprody.service.PaymentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentResponseMapper responseMapper;

    private final static String PAGE_NUMBER = "0";
    private final static String PAGE_SIZE = "25";
    private final static String SORT_BY = "createdAt";
    private final static String DIRECTION = "desc";

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getPayments() {
        return ResponseEntity.ok(paymentService.findAll().stream()
                .map(responseMapper::toResponse)
                .toList());
    }

    @GetMapping("{paymentId}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable UUID paymentId) {
        final PaymentDto dto = paymentService.findByPaymentId(paymentId);
        final PaymentResponse response = responseMapper.toResponse(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public Page<PaymentResponse> searchPayments(
        @ModelAttribute PaymentFilterRequest filter,
        @RequestParam(defaultValue = PAGE_NUMBER) int page,
        @RequestParam(defaultValue = PAGE_SIZE) int size,
        @RequestParam(defaultValue = SORT_BY) String sortBy,
        @RequestParam(defaultValue = DIRECTION) String direction
    ) {
        final Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

        final Pageable pageable = PageRequest.of(page, size, sort);
        return paymentService.searchPaged(filter, pageable)
                .map(responseMapper::toResponse);
    }
}
