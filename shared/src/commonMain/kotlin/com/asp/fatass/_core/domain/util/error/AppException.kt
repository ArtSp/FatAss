package com.asp.fatass._core.domain.util.error

class AppException(val error: AppError): Exception(error)