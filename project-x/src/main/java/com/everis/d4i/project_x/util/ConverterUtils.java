package com.everis.d4i.project_x.util;

import static com.everis.d4i.project_x.util.constant.ExceptionConstantsUtils.NOT_FOUND_GENERIC;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.exception.SalesNotFoundException;
import com.everis.d4i.project_x.exception.error.ErrorDto;
import com.everis.d4i.project_x.persistence.entity.CommonAuditEntity;


public final class ConverterUtils {
  private static final ModelMapper MODEL_MAPPER = new ModelMapper();

  private ConverterUtils() {

  }

  public static final <T, V extends CommonAuditEntity> V toEntity(final T rest,
      final Class<V> entityType) throws SalesException {
    if (rest == null) {
      throw new SalesNotFoundException(new ErrorDto(NOT_FOUND_GENERIC));
    }
    return MODEL_MAPPER.map(rest, entityType);
  }

  public static final <T, V extends CommonAuditEntity> T toRest(final Optional<V> entity,
      final Class<T> restType) throws SalesException {
    if (!entity.isPresent()) {
      throw new SalesNotFoundException(new ErrorDto(NOT_FOUND_GENERIC));
    }
    return MODEL_MAPPER.map(entity.get(), restType);
  }

  public static final <T, V extends CommonAuditEntity> T toRest(final V entity,
      final Class<T> restType) throws SalesException {
    if (entity == null) {
      throw new SalesNotFoundException(new ErrorDto(NOT_FOUND_GENERIC));
    }
    return MODEL_MAPPER.map(entity, restType);
  }

  public static <T, V extends CommonAuditEntity> List<V> toEntityList(final List<T> restList,
      final Class<V> entityType) throws SalesException {
    if (restList == null || restList.isEmpty()) {
      throw new SalesNotFoundException(new ErrorDto(NOT_FOUND_GENERIC));
    }
    return restList.stream().map(rest -> MODEL_MAPPER.map(rest, entityType))
        .collect(Collectors.toList());
  }

  public static <T, V extends CommonAuditEntity> List<T> toRestList(final List<V> entityList,
      final Class<T> restType) throws SalesException {
    if (entityList == null || entityList.isEmpty()) {
      throw new SalesNotFoundException(new ErrorDto(NOT_FOUND_GENERIC));
    }
    return entityList.stream().map(entity -> MODEL_MAPPER.map(entity, restType))
        .collect(Collectors.toList());
  }

  public static final <T extends Serializable, V extends Serializable> T mapRest(final V sourceRest,
      final Class<T> targetRestClass) throws SalesException {
    if (sourceRest == null) {
      throw new SalesNotFoundException(new ErrorDto(NOT_FOUND_GENERIC));
    }
    return MODEL_MAPPER.map(sourceRest, targetRestClass);
  }

  public static final <T extends Serializable, V extends Serializable> List<T> mapRestList(
      final List<V> sourceRestList, final Class<T> targetRestClass) throws SalesException {
    if (sourceRestList == null || sourceRestList.isEmpty()) {
      throw new SalesNotFoundException(new ErrorDto(NOT_FOUND_GENERIC));
    }
    return sourceRestList.stream().map(rest -> MODEL_MAPPER.map(rest, targetRestClass))
        .collect(Collectors.toList());
  }

}
