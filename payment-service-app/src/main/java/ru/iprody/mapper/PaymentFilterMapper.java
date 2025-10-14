package ru.iprody.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.iprody.model.PaymentFilter;
import ru.iprody.model.request.PaymentFilterRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PaymentFilterMapper {

    PaymentFilter toFilter(PaymentFilterRequest paymentFilterRequest);

    PaymentFilterRequest toRequest(PaymentFilter paymentFilter);
}