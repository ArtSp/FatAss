package com.asp.fatass.core.domain.util.error

class AppException(val error: AppError): Exception(error)