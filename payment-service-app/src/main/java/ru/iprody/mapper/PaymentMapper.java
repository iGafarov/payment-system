package ru.iprody.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.iprody.model.PaymentDto;
import ru.iprody.persistence.entity.Payment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PaymentMapper {

    PaymentDto toDto(Payment payment);

    Payment toEntity(PaymentDto paymentDto);
}