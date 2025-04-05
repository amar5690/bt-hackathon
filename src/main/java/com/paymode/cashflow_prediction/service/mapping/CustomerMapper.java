package com.paymode.cashflow_prediction.service.mapping;

import com.paymode.cashflow_prediction.dto.CustomerDto;
import com.paymode.cashflow_prediction.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "offsetToLocalDateTime")
    @Mapping(target = "modifiedDate", source = "modifiedDate", qualifiedByName = "offsetToLocalDateTime")
    Customer toCustomer(CustomerDto customerDto);

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "localToOffsetDateTime")
    @Mapping(target = "modifiedDate", source = "modifiedDate", qualifiedByName = "localToOffsetDateTime")
    CustomerDto toCustomerDto(Customer customer);

    default List<Customer> toCustomerList(List<CustomerDto> customerDtoList) {
        return customerDtoList.stream().map(this::toCustomer).collect(Collectors.toList());
    }

    default List<CustomerDto> toCustomerDtoList(List<Customer> customerList) {
        return customerList.stream().map(this::toCustomerDto).collect(Collectors.toList());
    }

    @Named("localToOffsetDateTime")
    static OffsetDateTime localToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
    }

    @Named("offsetToLocalDateTime")
    static LocalDateTime offsetToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ? null : offsetDateTime.toLocalDateTime();
    }
}
