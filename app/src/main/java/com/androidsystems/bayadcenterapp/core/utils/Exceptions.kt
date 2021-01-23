package com.androidsystems.bayadcenterapp.core.utils

import java.io.EOFException
import java.io.IOException

class NoConnectivityException : IOException()
class ResponseBodyException : EOFException()