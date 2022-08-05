package com.example.equation.data.response

sealed class DataResponse<T> constructor(val resultsModelDataType: ResultsModelDataType) {
    class DataThreeSolutions<T>(val data:T):DataResponse<T>(ResultsModelDataType.THREE_SOLUTIONS)
    class DataTwoSolutions<T>(val data:T):DataResponse<T>(ResultsModelDataType.TWO_SOLUTIONS)
    class DataOneSolutions<T>(val data:T):DataResponse<T>(ResultsModelDataType.ONE_SOLUTIONS)
    class DataInfiniteSolutions<T>():DataResponse<T>(ResultsModelDataType.INFINITE_SOlUTIONS)
    class DataNoSolutions<T>():DataResponse<T>(ResultsModelDataType.NO_SOLUTIONS)
}