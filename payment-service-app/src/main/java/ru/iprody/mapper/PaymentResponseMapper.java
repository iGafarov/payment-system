package ru.iprody.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.iprody.model.PaymentDto;
import ru.iprody.model.response.PaymentResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PaymentResponseMapper {

    PaymentResponse toResponse(PaymentDto paymentDto);

    PaymentDto toDto(PaymentResponse paymentResponse);
}